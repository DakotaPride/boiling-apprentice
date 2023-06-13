package net.dakotapride.boilingwitch.common.item;

import com.google.common.collect.ImmutableList;
import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElixirItem extends Item implements ISpellStoring {
    ImmutableList<StatusEffect> getEffects;

    public ElixirItem(Settings settings) {
        super(settings);

        ImmutableList.Builder<StatusEffect> builder = ImmutableList.builder();

        builder.add(StatusEffects.SPEED);
        builder.add(StatusEffects.INVISIBILITY);
        builder.add(StatusEffects.SLOWNESS);
        builder.add(StatusEffects.WEAKNESS);
        builder.add(StatusEffects.BLINDNESS);
        builder.add(StatusEffects.DOLPHINS_GRACE);
        builder.add(StatusEffects.FIRE_RESISTANCE);
        builder.add(StatusEffects.GLOWING);
        builder.add(StatusEffects.HASTE);
        builder.add(StatusEffects.HUNGER);
        builder.add(StatusEffects.JUMP_BOOST);
        builder.add(StatusEffects.LEVITATION);
        builder.add(StatusEffects.MINING_FATIGUE);
        builder.add(StatusEffects.LUCK);
        builder.add(StatusEffects.NAUSEA);
        builder.add(StatusEffects.NIGHT_VISION);
        builder.add(StatusEffects.POISON);
        builder.add(StatusEffects.REGENERATION);
        builder.add(StatusEffects.RESISTANCE);
        builder.add(StatusEffects.SATURATION);
        builder.add(StatusEffects.UNLUCK);
        builder.add(StatusEffects.STRENGTH);

        this.getEffects = builder.build();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt() != null && stack.getNbt().getBoolean("hasEffect")) {
            tooltip.add(Text.translatable("text.effect." + stack.getNbt().getString("effectKey")).formatted(Formatting.GRAY));
        } else if (stack.getNbt() != null && !stack.getNbt().getBoolean("hasEffect") || !stack.hasNbt()) {
            tooltip.add(Text.translatable("text.effect.none").formatted(Formatting.GRAY));
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        if (stack.getNbt() != null && stack.getNbt().getBoolean("hasEffect")) {
            return 32;
        } else {
            return super.getMaxUseTime(stack);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        NbtCompound nbt = user.getStackInHand(hand).getNbt();
        if (nbt != null && nbt.getBoolean("hasEffect")) {
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity player = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        NbtCompound nbt = stack.getNbt();

        if (player instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (nbt != null && nbt.getBoolean("hasEffect")) {
            if (player != null && stack.getNbt() != null) {
                player.addStatusEffect(new StatusEffectInstance(getEffects.get(nbt.getInt("effectValue")), 800));
            }

            if (player != null) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            if (player == null || !player.getAbilities().creativeMode) {
                if (stack.isEmpty()) {
                    return new ItemStack(ItemRegister.ELIXIR);
                }

                if (player != null) {
                    player.getInventory().insertStack(new ItemStack(ItemRegister.ELIXIR));
                }
            }

            user.emitGameEvent(GameEvent.DRINK);
        }
        return stack;
    }
}
