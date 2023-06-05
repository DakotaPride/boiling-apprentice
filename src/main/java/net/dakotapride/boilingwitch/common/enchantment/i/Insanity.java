package net.dakotapride.boilingwitch.common.enchantment.i;

import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.item.ItemStack;

public interface Insanity {
    default boolean isDestabilizer(ItemStack stack) {
        return stack.isOf(ItemRegister.DESTABILIZER);
    }

}
