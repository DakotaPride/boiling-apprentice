package net.dakotapride.boilingwitch.common.enchantment;

import net.dakotapride.boilingwitch.common.enchantment.i.Insanity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class InverseEnchantment extends Enchantment implements Insanity {
    public InverseEnchantment(EquipmentSlot... slotTypes) {
        super(Rarity.VERY_RARE, EnchantmentTarget.BREAKABLE, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return isDestabilizer(stack);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof DementedEnchantment) && super.canAccept(other);
    }
}
