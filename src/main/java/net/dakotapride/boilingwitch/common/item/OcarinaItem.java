package net.dakotapride.boilingwitch.common.item;

import com.google.common.collect.ImmutableList;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.dakotapride.boilingwitch.common.register.content.SoundEventRegister;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OcarinaItem extends Item {
    ImmutableList<StatusEffect> getCurses;

    public OcarinaItem(Settings settings) {
        super(settings);

        ImmutableList.Builder<StatusEffect> builder = ImmutableList.builder();

        builder.add(EffectRegister.AVIAN_CURSE);
        builder.add(EffectRegister.VULTURE_CURSE);
        builder.add(EffectRegister.DEVIANCY_CURSE);
        builder.add(EffectRegister.UNGUARDED_CURSE);
        builder.add(EffectRegister.BOILING_CURSE);
        builder.add(EffectRegister.PHANTOMESQUE_CURSE);
        builder.add(EffectRegister.CONSTRICTING_CURSE);


        this.getCurses = builder.build();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);

        if (itemStack.hasNbt()) {
            world.playSound(user, user.getBlockPos(), SoundEventRegister.CURSE_CURED_FROM_OCARINA,
                    SoundCategory.PLAYERS, 0.8f, 1.0f);

            user.removeStatusEffect(getCurses.get(itemStack.getNbt().getInt("curseEffect")));

            user.getItemCooldownManager().set(this, 120);

            return TypedActionResult.consume(itemStack);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!stack.hasNbt()) {
            tooltip.add(Text.translatable("text.boilingwitch.cure.unavailable").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
        } else {
            tooltip.add(Text.translatable("text.boilingwitch.cures." + stack.getNbt().getString("curseKey")).formatted(Formatting.BLUE));
        }
    }
}
