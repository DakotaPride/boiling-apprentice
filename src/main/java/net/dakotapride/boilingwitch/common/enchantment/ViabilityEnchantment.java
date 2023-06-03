package net.dakotapride.boilingwitch.common.enchantment;

import net.dakotapride.boilingwitch.common.item.ChalkItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ViabilityEnchantment extends Enchantment {
    public ViabilityEnchantment(EquipmentSlot... slotTypes) {
        super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ChalkItem;
    }
}
