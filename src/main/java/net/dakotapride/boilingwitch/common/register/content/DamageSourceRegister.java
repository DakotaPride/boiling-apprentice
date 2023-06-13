package net.dakotapride.boilingwitch.common.register.content;

import net.minecraft.entity.damage.DamageSource;

public class DamageSourceRegister {

    public static final DamageSource BOILING_SEA = (new DamageSource("boilingwitch.boiledWaters")).setBypassesArmor().setFire();
    public static final DamageSource CURSE = (new DamageSource("boilingwitch.curse")).setBypassesArmor().setScaledWithDifficulty().setUsesMagic();
    public static final DamageSource VULTURE_RETRIBUTION = (new DamageSource("boilingwitch.vultureRetribution")).setBypassesArmor().setScaledWithDifficulty().setUsesMagic();

    public static void register() {}

}
