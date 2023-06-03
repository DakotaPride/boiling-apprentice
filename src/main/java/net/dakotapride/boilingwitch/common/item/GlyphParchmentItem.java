package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlyphParchmentItem extends ParchmentItem implements ISpellStoring {
    public GlyphParchmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isInvisibilityScroll(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "invisibility");
        } else if (isSafeHoverScroll(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "safety_hover");
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (isInvisibilityScroll(this.asItem())) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 300, 0));

            // user.getItemCooldownManager().set(this, 320);
        }

        if (isSafeHoverScroll(this.asItem())) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 4));

            // user.getItemCooldownManager().set(this, 60);
        }

        if (user instanceof ServerPlayerEntity && !(user.getAbilities().creativeMode)) {
            stack.decrement(1);
            giveGlyphRemnant(user, 2);
        }

        return super.use(world, user, hand);
    }

}
