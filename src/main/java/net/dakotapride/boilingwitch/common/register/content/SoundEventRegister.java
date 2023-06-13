package net.dakotapride.boilingwitch.common.register.content;

import net.minecraft.sound.SoundEvent;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.*;

public class SoundEventRegister {

    public static SoundEvent CURSE_CURED_FROM_OCARINA = registerSoundEvent("ocarina_curing_curse");
    public static SoundEvent OCARINA_HARMED_FROM_WRONGFUL_USE = registerSoundEvent("ocarina_harmed");

    public static void register() {}

}
