package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.IBoilingWitchItemGroup;
import net.dakotapride.boilingwitch.common.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.StewItem;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.registerItem;

public class ItemRegister implements IBoilingWitchItemGroup {
    // Chalk Items
    public static Item CHALK = registerItem("chalk",
            new ChalkItem(new FabricItemSettings().maxCount(1).maxDamage(150).group(BOILING_WITCH_GROUP)));
    public static Item CHALK_LIGHT = registerItem("chalk_light",
            new ChalkItem(new FabricItemSettings().maxCount(1).maxDamage(150).group(BOILING_WITCH_GROUP)));
    public static Item CHALK_FIRE = registerItem("chalk_fire",
            new ChalkItem(new FabricItemSettings().maxCount(1).maxDamage(150).group(BOILING_WITCH_GROUP)));
    public static Item CHALK_PLANT = registerItem("chalk_plant",
            new ChalkItem(new FabricItemSettings().maxCount(1).maxDamage(150).group(BOILING_WITCH_GROUP)));
    public static Item CHALK_ICE = registerItem("chalk_ice",
            new ChalkItem(new FabricItemSettings().maxCount(1).maxDamage(150).group(BOILING_WITCH_GROUP)));

    // Glyph/Parchment Items
    public static Item GLYPH = registerItem("glyph",
            new Item(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));
    public static Item FIRE_GLYPH = registerItem("glyph_fire",
            new GlyphItem(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));
    public static Item PLANT_GLYPH = registerItem("glyph_plant",
            new GlyphItem(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));
    public static Item ICE_GLYPH = registerItem("glyph_ice",
            new GlyphItem(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));
    public static Item LIGHT_GLYPH = registerItem("glyph_light",
            new GlyphItem(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));

    public static Item GLYPH_REMNANT = registerItem("glyph_remnant",
            new Item(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));

    // TODO: Implement more cursed parchments
    //  Concepts List
    //  Vulture = Similar effects to the Avian curse, except the player doesn't go invisible at any period of time.
    //  The player also has a chance to become blind for the duration of the curse
    //  .
    //  Boiling = The effects of boiling water become more deadly and effective in combat scenarios
    //  .
    //  Unguarded = The longer this curse is applied, the worse the damage taken gets
    //  .
    //  Deviancy = The player gains a slight speed boost, but has their mining reach/speed decreased. Alongside this, their
    //  jump height is also decreased.

    public static Item PARCHMENT = registerItem("parchment",
            new Item(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));
    public static Item PARCHMENT_INVISIBILITY = registerItem("parchment_invisibility",
            new GlyphParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item PARCHMENT_SAFE_HOVER = registerItem("parchment_safe_hover",
            new GlyphParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item CURSED_PARCHMENT_AVIAN = registerItem("cursed_parchment_avian",
            new ParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item CURSED_PARCHMENT_VULTURE = registerItem("cursed_parchment_vulture",
            new ParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item CURSED_PARCHMENT_UNGUARDED = registerItem("cursed_parchment_unguarded",
            new ParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item CURSED_PARCHMENT_DEVIANCY = registerItem("cursed_parchment_deviancy",
            new ParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item CURSED_PARCHMENT_BOILING = registerItem("cursed_parchment_boiling",
            new ParchmentItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));

    // Elixir Items
    public static Item AVIAN_CURSE_ELIXIR = registerItem("elixir_avian",
            new ElixerItem(new FabricItemSettings().maxCount(1).group(BOILING_WITCH_GROUP)));
    public static Item EMPTY_ELIXIR = registerItem("elixir_empty",
            new Item(new FabricItemSettings().maxCount(16).group(BOILING_WITCH_GROUP)));

    // Miscellaneous Items
    public static Item RESTING_NETTLE_STEW = registerItem("nettle_stew",
            new StewItem(new FabricItemSettings().maxCount(1).food(FoodComponentRegister.RESTING_NETTLE_STEW_FOOD_COMPONENT).group(BOILING_WITCH_GROUP)));
    public static Item MYSTIC_ESSENCE = registerItem("mystic_essence",
            new Item(new FabricItemSettings().group(BOILING_WITCH_GROUP)));

    public static Item HOIST_CONFIGURATOR = registerItem("hoist_configurator",
            new HoistConfiguratorItem(new FabricItemSettings().group(BOILING_WITCH_GROUP)));

    public static Item DESTABILIZER = registerItem("destabilizer",
            new Item(new FabricItemSettings().group(BOILING_WITCH_GROUP)));

    // Brewing Advancing Items
    public static Item BREWING_VISOR = registerItem("visor_brewing",
            new VisorItem(ArmourMaterialsRegister.MYSTIC, new FabricItemSettings().group(BOILING_WITCH_GROUP)));

    public static Item FAIRY_CAKE = registerItem("fairy_cake",
            new Item(new FabricItemSettings().group(BOILING_WITCH_GROUP).maxCount(1).food(FoodComponentRegister.FAIRY_CAKE)));
    public static Item CLEANSED_FAIRY_CAKE = registerItem("cleansed_fairy_cake",
            new Item(new FabricItemSettings().group(BOILING_WITCH_GROUP).maxCount(1).food(FoodComponentRegister.CLEANSED_FAIRY_CAKE)));

    public static void register() {}

}
