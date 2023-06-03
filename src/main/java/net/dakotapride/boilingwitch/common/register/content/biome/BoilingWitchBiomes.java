package net.dakotapride.boilingwitch.common.register.content.biome;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.register.content.feature.PlacedFeatureRegister;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class BoilingWitchBiomes {
    public static final RegistryKey<Biome> WRINKLING_OAK_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "wrinkling_oak_forest"));
    public static final RegistryKey<Biome> WRINKLING_OAK_PLAINS_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "wrinkling_oak_plains"));
    public static final RegistryKey<Biome> ECLIPSE_PLAINS_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "eclipse_plains"));

    public static void register() {
        Registry.register(BuiltinRegistries.BIOME, WRINKLING_OAK_FOREST_KEY.getValue(), wrinklingOakForest());
        Registry.register(BuiltinRegistries.BIOME, WRINKLING_OAK_PLAINS_KEY.getValue(), wrinklingOakPlains());
        Registry.register(BuiltinRegistries.BIOME, ECLIPSE_PLAINS_KEY.getValue(), eclipsePlains());
    }

    private static Biome wrinklingOakPlains() {
        GenerationSettings.Builder g = new GenerationSettings.Builder();

        g.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegister.RARE_WRINKLING_OAK_TREES);

        SpawnSettings s = new SpawnSettings.Builder().build();

        return buildStandardBiomeRegistry(s, g.build());
    }

    private static Biome wrinklingOakForest() {
        GenerationSettings.Builder g = new GenerationSettings.Builder();

        g.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegister.WRINKLING_OAK_TREES);
        g.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegister.PALISTROM_TREES);
        g.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegister.RESTING_NETTLE_PLACED);
        g.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegister.FOOLS_BLOOD_PATCH);

        DefaultBiomeFeatures.addPlainsTallGrass(g);
        DefaultBiomeFeatures.addJungleGrass(g);

        SpawnSettings s = new SpawnSettings.Builder().build();

        return buildStandardBiomeRegistry(s, g.build());
    }

    private static Biome eclipsePlains() {
        GenerationSettings.Builder g = new GenerationSettings.Builder();

        g.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegister.FOOLS_BLOOD_PATCH);

        SpawnSettings s = new SpawnSettings.Builder().build();

        return buildBiome(Biome.Precipitation.RAIN, 1.0F, 0.4F, new BiomeEffects.Builder(), 0x6DB7BF,
                0x8FBABA, 0x94A7BA, 0x96B7B7, 0x809EB7, 0x809EB7, s, g.build());
    }

    private static Biome buildStandardBiomeRegistry(SpawnSettings s, GenerationSettings g) {
        return buildBiome(Biome.Precipitation.RAIN, 1.0F, 0.4F, new BiomeEffects.Builder(), 0x8E6EC1,
                0xAA95BF, 0xB795BC, 0xA76FBA, 0xE06259, 0xE06259, s, g);
    }

    private static Biome buildBiome(Biome.Precipitation precipitation, float downfall, float temperature, BiomeEffects.Builder builder,
                                    int waterColour, int waterFogColour, int skyColour, int fogColour, int grassColour, int foliageColour,
                                    SpawnSettings spawnSettings, GenerationSettings generationSettings) {

        return new Biome.Builder()
                .precipitation(precipitation)
                .downfall(downfall)
                .temperature(temperature)
                .effects(builder
                        .waterColor(waterColour)
                        .waterFogColor(waterFogColour)
                        .skyColor(skyColour)
                        .fogColor(fogColour)
                        .grassColor(grassColour)
                        .foliageColor(foliageColour).build())
                .spawnSettings(spawnSettings)
                .generationSettings(generationSettings).build();
    }
}
