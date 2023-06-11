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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElixirItem extends Item implements ISpellStoring, IWIP {
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

        getWorkInProgressTooltip(tooltip);

        if (stack.hasNbt()) {
            tooltip.add(Text.translatable("text.effect." + stack.getNbt().getString("effectKey")));
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getStackInHand(hand).hasNbt()) {
            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (stack.hasNbt()) {
            if (playerEntity != null) {
                playerEntity.addStatusEffect(new StatusEffectInstance(getEffects.get(stack.getNbt().getInt("effectValue")), 800));
            }

            if (playerEntity != null) {
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!playerEntity.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
                if (stack.isEmpty()) {
                    return new ItemStack(ItemRegister.ELIXIR);
                }

                if (playerEntity != null) {
                    playerEntity.getInventory().insertStack(new ItemStack(ItemRegister.ELIXIR));
                }
            }

            user.emitGameEvent(GameEvent.DRINK);
        }
        return stack;
    }
}
