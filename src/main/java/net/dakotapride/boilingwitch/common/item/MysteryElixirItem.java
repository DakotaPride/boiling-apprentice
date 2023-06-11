package net.dakotapride.boilingwitch.common.item;

import com.google.common.collect.ImmutableList;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysteryElixirItem extends ElixirItem  {
    ImmutableList<StatusEffect> effects;

    public MysteryElixirItem(Settings settings) {
        super(settings);

        ImmutableList.Builder<StatusEffect> builder = ImmutableList.builder();

        builder.add(EffectRegister.AVIAN_CURSE);
        builder.add(EffectRegister.VULTURE_CURSE);
        builder.add(EffectRegister.DEVIANCY_CURSE);
        builder.add(EffectRegister.UNGUARDED_CURSE);
        builder.add(EffectRegister.BOILING_CURSE);
        builder.add(EffectRegister.PHANTOMESQUE_CURSE);
        builder.add(EffectRegister.BINDING_CURSE);


        this.effects = builder.build();
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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("text.mystery_elixir.effect").formatted(Formatting.ITALIC).formatted(Formatting.BLUE));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int getRandomEffectFromInteger = Random.create().nextBetween(1, effects.size());

        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (playerEntity instanceof ServerPlayerEntity) {
            playerEntity.addStatusEffect(new StatusEffectInstance(effects.get(getRandomEffectFromInteger), 200));
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
        return stack;
    }
}
