package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.minecraft.item.Instrument;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class InstrumentRegister {
    public static RegistryKey<Instrument> REINVENTION_OCARINA_MELODY = register("ocarina_reinvention");
    public static RegistryKey<Instrument> HALLUCINOGENIC_OCARINA_MELODY = register("ocarina_hallucinogenic");
    public static RegistryKey<Instrument> WONDROUS_OCARINA_MELODY = register("ocarina_wondrous");
    public static RegistryKey<Instrument> JUNCTURE_OCARINA_MELODY = register("ocarina_juncture");

    private static RegistryKey<Instrument> register(String name) {
        return RegistryKey.of(Registry.INSTRUMENT_KEY, new Identifier(BoilingWitchMod.MOD_ID, name));
    }

    public static void register() {
        Registry.register(Registry.INSTRUMENT, REINVENTION_OCARINA_MELODY,
                new Instrument(SoundEventRegister.OCARINA_MELODY_REINVENTION, 70, 256.0F));
        Registry.register(Registry.INSTRUMENT, HALLUCINOGENIC_OCARINA_MELODY,
                new Instrument(SoundEventRegister.OCARINA_MELODY_HALLUCINOGENIC, 70, 256.0F));
        Registry.register(Registry.INSTRUMENT, WONDROUS_OCARINA_MELODY,
                new Instrument(SoundEventRegister.OCARINA_MELODY_WONDROUS, 70, 256.0F));
        Registry.register(Registry.INSTRUMENT, JUNCTURE_OCARINA_MELODY,
                new Instrument(SoundEventRegister.OCARINA_MELODY_JUNCTURE, 70, 256.0F));
    }
}
