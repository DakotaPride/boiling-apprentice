package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ElixerItem extends Item implements ISpellStoring {
    public ElixerItem(Settings settings) {
        super(settings);
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
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (!world.isClient) {
            if (stack.isOf(ItemRegister.AVIAN_CURSE_ELIXIR)) {
                if (hasAvianCurse(user)) {
                    user.removeStatusEffect(EffectRegister.AVIAN_CURSE);

                    if (!(user.hasStatusEffect(StatusEffects.INVISIBILITY))) {
                        user.setInvisible(false);
                    }
                } else {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 1));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 2));
                }
            }
        }

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(ItemRegister.EMPTY_ELIXIR);
            }

            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(ItemRegister.EMPTY_ELIXIR));
            }
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }
}
