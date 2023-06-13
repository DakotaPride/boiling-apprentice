package net.dakotapride.boilingwitch.common.datagen.tag;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.register.content.InstrumentRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Instrument;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InstrumentTagData extends FabricTagProvider<Instrument> {
    public static TagKey<Instrument> OCARINA_MUSICAL_EFFECTS = TagKey.of(Registry.INSTRUMENT_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "ocarina_effects"));


    public InstrumentTagData(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.INSTRUMENT);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(OCARINA_MUSICAL_EFFECTS)
                .add(InstrumentRegister.REINVENTION_OCARINA_MELODY)
                .add(InstrumentRegister.HALLUCINOGENIC_OCARINA_MELODY)
                .add(InstrumentRegister.WONDROUS_OCARINA_MELODY)
                .add(InstrumentRegister.JUNCTURE_OCARINA_MELODY);
    }
}
