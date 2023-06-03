package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.enchantment.ViabilityEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.registerEnchantment;

public class EnchantmentRegister {

    public static Enchantment VIABILITY = registerEnchantment("viability", new ViabilityEnchantment(EquipmentSlot.MAINHAND));

    public static void register() {}

}
