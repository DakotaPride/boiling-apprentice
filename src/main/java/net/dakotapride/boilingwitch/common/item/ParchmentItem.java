package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ParchmentItem extends Item implements ISpellStoring {
    public ParchmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isAvianParchment(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "avian");
        } else if (isVultureParchment(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "vulture");
        } else if (isUnguardedParchment(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "unguarded");
        } else if (isDeviancyParchment(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "deviancy");
        } else if (isBoilingParchment(stack.getItem())) {
            setSpellTooltip(tooltip, stack, parchmentMedium, "boiling");
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!hasAvianCurse(entity) && stack.isOf(ItemRegister.CURSED_PARCHMENT_AVIAN)) {
            if (user instanceof ServerPlayerEntity && !user.isDescending()) {
                giveTargetAvianCurse(entity, 1800, 0);

                stack.decrement(1);
            }

            curseEntity(entity, hand);
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!hasAvianCurse(user) && stack.isOf(ItemRegister.CURSED_PARCHMENT_AVIAN)) {
            if (user instanceof ServerPlayerEntity) {
                if (user.isDescending()) {
                    giveTargetAvianCurse(user, 1800, 0);

                    stack.decrement(1);
                }
            }
        }

        return super.use(world, user, hand);
    }
}
