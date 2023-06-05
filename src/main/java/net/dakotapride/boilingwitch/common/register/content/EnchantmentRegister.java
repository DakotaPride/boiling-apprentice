package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.enchantment.InverseEnchantment;
import net.dakotapride.boilingwitch.common.enchantment.DementedEnchantment;
import net.dakotapride.boilingwitch.common.enchantment.ViabilityEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.registerEnchantment;

public class EnchantmentRegister {

    public static Enchantment VIABILITY = registerEnchantment("viability", new ViabilityEnchantment(EquipmentSlot.MAINHAND));

    public static Enchantment DEMENTED = registerEnchantment("demented", new DementedEnchantment(EquipmentSlot.OFFHAND));
    public static Enchantment INVERSE = registerEnchantment("inverse", new InverseEnchantment(EquipmentSlot.OFFHAND));

    public static void register() {}

}
