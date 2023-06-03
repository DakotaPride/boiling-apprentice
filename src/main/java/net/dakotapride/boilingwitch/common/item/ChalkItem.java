package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChalkItem extends Item implements ISpellStoring {
    public ChalkItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isFireRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, chalkMedium, fireModifier);
        } else if (isPlantRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, chalkMedium, plantModifier);
        } else if (isIceRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, chalkMedium, iceModifier);
        } else if (isLightRelatedSpell(stack.getItem())) {
            setSpellTooltip(tooltip, stack, chalkMedium, lightModifier);
        }
    }
}
