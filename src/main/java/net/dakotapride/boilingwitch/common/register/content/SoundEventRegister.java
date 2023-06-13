package net.dakotapride.boilingwitch.common.register.content;

import net.minecraft.sound.SoundEvent;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.*;

public class SoundEventRegister {

    public static SoundEvent CURSE_CURED_FROM_OCARINA = registerSoundEvent("ocarina_curing_curse");
    public static SoundEvent OCARINA_HARMED_FROM_WRONGFUL_USE = registerSoundEvent("ocarina_harmed");

    public static SoundEvent OCARINA_MELODY_REINVENTION = registerSoundEvent("ocarina_melody_reinvention");
    public static SoundEvent OCARINA_MELODY_HALLUCINOGENIC = registerSoundEvent("ocarina_melody_hallucinogenic");
    public static SoundEvent OCARINA_MELODY_WONDROUS = registerSoundEvent("ocarina_melody_wondrous");
    public static SoundEvent OCARINA_MELODY_JUNCTURE = registerSoundEvent("ocarina_melody_juncture");

    public static void register() {}

}
