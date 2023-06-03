package net.dakotapride.boilingwitch.common.item.magic;

import net.dakotapride.boilingwitch.common.datagen.tag.BlockTagData;
import net.dakotapride.boilingwitch.common.datagen.tag.ItemTagData;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface IGlyph {

    default boolean isNaturalGlyph(Item item) {
        return item.getDefaultStack().isIn(ItemTagData.IS_NATURAL_GLYPH);
    }

    default boolean isFireGlyph(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.FIRE_GLYPH);
    }

    default boolean isLightGlyph(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.LIGHT_GLYPH);
    }

    default boolean isPlantGlyph(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.PLANT_GLYPH);
    }

    default boolean isIceGlyph(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.ICE_GLYPH);
    }

    default boolean isScrollItem(Item item) {
        return item.getDefaultStack().isIn(ItemTagData.IS_PARCHMENT);
    }

    default void giveGlyphRemnant(PlayerEntity player, int remnantAmount) {
        player.giveItemStack(new ItemStack(ItemRegister.GLYPH_REMNANT, remnantAmount));
    }

    default void drainRemainingGlyph(PlayerEntity player, ItemStack itemStack) {
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
            giveGlyphRemnant(player, 1);

            player.getItemCooldownManager().set(itemStack.getItem(), 60);
        }
    }



    default boolean isInvisibilityScroll(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.PARCHMENT_INVISIBILITY);
    }

    default boolean isSafeHoverScroll(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.PARCHMENT_SAFE_HOVER);
    }



    default void createLineOfFireFromGlyphOrParchment(ItemUsageContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        Hand hand = ctx.getHand();
        ItemStack stack = player.getStackInHand(hand);
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction direction = ctx.getPlayerFacing();


        for (int i = 0; i < 5; i++) {
            if (world.getBlockState(pos.up(1).offset(direction, i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(1).offset(direction, i), Blocks.FIRE.getDefaultState());
                world.setBlockState(pos.offset(direction, i), Blocks.MAGMA_BLOCK.getDefaultState());

                if (i == 1) {
                    drainRemainingGlyph(player, stack);
                }
            }
        }
    }

    default void createRestingNettleFromGlyphOrParchment(ItemUsageContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        PlayerEntity player = ctx.getPlayer();
        ItemStack itemStack = ctx.getStack();
        Direction direction = ctx.getPlayerFacing();

        for (int i = 1; i < 4; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2), Blocks.MOSS_BLOCK.getDefaultState());
            }
        }

        for (int i = 1; i < 2; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2).offset(Direction.WEST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2).offset(Direction.WEST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2).offset(Direction.SOUTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2).offset(Direction.SOUTH, 1), Blocks.MOSS_BLOCK.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2).offset(Direction.EAST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2).offset(Direction.EAST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2).offset(Direction.NORTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2).offset(Direction.NORTH, 1), Blocks.MOSS_BLOCK.getDefaultState());

            }
        }

        for (int i = 3; i < 4; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2).offset(Direction.WEST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2).offset(Direction.WEST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2).offset(Direction.SOUTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2).offset(Direction.SOUTH, 1), Blocks.MOSS_BLOCK.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2).offset(Direction.EAST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2).offset(Direction.EAST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2).offset(Direction.NORTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2).offset(Direction.NORTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
            }
        }

        for (int i = 4; i < 5; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i), Blocks.MOSS_BLOCK.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 1), Blocks.MOSS_BLOCK.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 1), Blocks.MOSS_BLOCK.getDefaultState());
            }
        }

        if (world.getBlockState(pos).isIn(BlockTags.SMALL_FLOWERS)) {
            world.setBlockState(pos, BlockRegister.RESTING_NETTLE.getDefaultState());

            drainRemainingGlyph(player, itemStack);
        }
    }

    default void createWallOfIceFromGlyphOrParchment(ItemUsageContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        PlayerEntity player = ctx.getPlayer();
        ItemStack stack = ctx.getStack();
        Direction direction = ctx.getPlayerFacing();


        for (int i = 1; i < 4; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 3), Blocks.PACKED_ICE.getDefaultState());
            }
        }

        for (int i = 1; i < 3; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 5), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 5), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 5), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 5), Blocks.PACKED_ICE.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 5).offset(Direction.WEST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 5).offset(Direction.EAST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 5).offset(Direction.NORTH, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 5).offset(Direction.SOUTH, 2), Blocks.PACKED_ICE.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 5).offset(Direction.EAST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 5).offset(Direction.WEST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 5).offset(Direction.SOUTH, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 5).offset(Direction.NORTH, 2), Blocks.PACKED_ICE.getDefaultState());
            }
        }

        for (int i = 1; i < 2; i++) {
            if (world.getBlockState(pos.up(i)).isIn(BlockTagData.AIR)) {
                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2), Blocks.PACKED_ICE.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 2).offset(Direction.WEST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 2).offset(Direction.EAST, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 2).offset(Direction.NORTH, 2), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 2).offset(Direction.SOUTH, 2), Blocks.PACKED_ICE.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 3).offset(Direction.WEST, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 3).offset(Direction.EAST, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 3).offset(Direction.NORTH, 3), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 3).offset(Direction.SOUTH, 3), Blocks.PACKED_ICE.getDefaultState());

                world.setBlockState(pos.up(i).offset(Direction.SOUTH, 7), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.NORTH, 7), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.WEST, 7), Blocks.PACKED_ICE.getDefaultState());
                world.setBlockState(pos.up(i).offset(Direction.EAST, 7), Blocks.PACKED_ICE.getDefaultState());
            }
        }

        drainRemainingGlyph(player, stack);

    }

}
