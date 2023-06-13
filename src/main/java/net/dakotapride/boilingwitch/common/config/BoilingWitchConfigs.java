package net.dakotapride.boilingwitch.common.config;

import com.mojang.datafixers.util.Pair;
import net.dakotapride.boilingwitch.common.BoilingWitchMod;

public class BoilingWitchConfigs {

    // public static boolean ALLOW_FOR_CREATIVE_SASH;

    // Ocarina ConfigProvider
    public static SimpleConfig OCARINA_CONFIG;
    private static ConfigProvider ocarinaConfigs;
    // Ocarina Config Options
    public static boolean ALLOW_FOR_IMMORTAL_OCARINA;
    public static int OCARINA_DURABILITY;
    public static double OCARINA_HARMED_INSTRUMENT_VOLUME;
    public static double OCARINA_CURED_CURSE_VOLUME;

    //

    public static void registerConfigs() {
        ocarinaConfigs = new ConfigProvider();
        createConfigs();

        OCARINA_CONFIG = SimpleConfig.of(BoilingWitchMod.MOD_ID + "/ocarina").provider(ocarinaConfigs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        // configs.addKeyValuePair(new Pair<>("modpack.apprentice.sash.creative", false), "boolean");
        ocarinaConfigs.addKeyValuePair(new Pair<>("modpack.apprentice.ocarina.immortal", false), "boolean [Dev Config]");
        ocarinaConfigs.addKeyValuePair(new Pair<>("modpack.apprentice.ocarina.durability", 8), "int [QOL Config]");
        ocarinaConfigs.addKeyValuePair(new Pair<>("modpack.apprentice.ocarina.volume.harmed_instrument", 1.0F), "double [QOL Config]");
        ocarinaConfigs.addKeyValuePair(new Pair<>("modpack.apprentice.ocarina.volume.cured_curse", 1.0F), "double [QOL Config]");
    }

    private static void assignConfigs() {
        // ALLOW_FOR_CREATIVE_SASH = CONFIG.getOrDefault("modpack.apprentice.allow_for_creative_sash", false);
        ALLOW_FOR_IMMORTAL_OCARINA = OCARINA_CONFIG.getOrDefault("modpack.apprentice.ocarina.immortal", false);
        OCARINA_DURABILITY = OCARINA_CONFIG.getOrDefault("modpack.apprentice.ocarina.durability", 8);
        OCARINA_HARMED_INSTRUMENT_VOLUME = OCARINA_CONFIG.getOrDefault("modpack.apprentice.ocarina.volume.harmed_instrument", 1.0F);
        OCARINA_CURED_CURSE_VOLUME = OCARINA_CONFIG.getOrDefault("modpack.apprentice.ocarina.volume.cured_curse", 1.0F);

        System.out.println("[Boiling Apprentice : Ocarina] All " + ocarinaConfigs.getConfigsList().size() + " have been set properly");
    }
}
