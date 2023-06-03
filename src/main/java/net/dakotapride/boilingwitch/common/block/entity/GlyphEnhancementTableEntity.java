package net.dakotapride.boilingwitch.common.block.entity;

import net.dakotapride.boilingwitch.common.datagen.tag.ItemTagData;
import net.dakotapride.boilingwitch.common.item.magic.IGlyph;
import net.dakotapride.boilingwitch.common.recipe.GlyphEnhancingRecipe;
import net.dakotapride.boilingwitch.common.register.content.BlockEntityRegister;
import net.dakotapride.boilingwitch.common.register.content.EnchantmentRegister;
import net.dakotapride.boilingwitch.common.screen.ImplementedInventory;
import net.dakotapride.boilingwitch.common.screen.handler.GlyphEnhancementScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GlyphEnhancementTableEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, IGlyph {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 64;

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getBlockPos(), 5.5);
    }

    public GlyphEnhancementTableEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.GLYPH_ENHANCEMENT_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return GlyphEnhancementTableEntity.this.progress;
                    case 1: return GlyphEnhancementTableEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0: GlyphEnhancementTableEntity.this.progress = value; break;
                    case 1: GlyphEnhancementTableEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 6;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.boilingwitch.enhancement_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new GlyphEnhancementScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("enhancement.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("enhancement.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, GlyphEnhancementTableEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(GlyphEnhancementTableEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<GlyphEnhancingRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(GlyphEnhancingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);
            entity.removeStack(3, 1);
            if (entity.getStack(4).isIn(ItemTagData.IS_CHALK)
                    && EnchantmentHelper.getLevel(EnchantmentRegister.VIABILITY, entity.getStack(4)) > 0) {
                entity.getStack(4);
            } else {
                entity.removeStack(4, 1);
            }


            entity.setStack(5, new ItemStack(recipe.get().getOutput().getItem(),
                    entity.getStack(5).getCount() + recipe.get().getOutput().getCount()));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(GlyphEnhancementTableEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<GlyphEnhancingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(GlyphEnhancingRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput().getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(5).getItem() == output || inventory.getStack(5).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(5).getMaxCount() > inventory.getStack(5).getCount();
    }
}