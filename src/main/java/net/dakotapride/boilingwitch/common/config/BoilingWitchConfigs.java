package net.dakotapride.boilingwitch.common.config;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;

public class BoilingWitchConfigs {
    public static SimpleConfig CONFIG;
    private static ConfigProvider configs;

    // public static boolean ALLOW_FOR_CREATIVE_SASH;

    public static void registerConfigs() {
        configs = new ConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(BoilingWitchMod.MOD_ID + "/config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        // configs.addKeyValuePair(new Pair<>("modpack.apprentice.allow_for_creative_sash", false), "boolean");
    }

    private static void assignConfigs() {
        // ALLOW_FOR_CREATIVE_SASH = CONFIG.getOrDefault("modpack.apprentice.allow_for_creative_sash", false);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
