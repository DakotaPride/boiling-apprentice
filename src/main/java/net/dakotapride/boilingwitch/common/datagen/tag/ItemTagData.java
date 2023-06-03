package net.dakotapride.boilingwitch.common.datagen.tag;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemTagData extends FabricTagProvider<Item> {
    public static TagKey<Item> IS_ACCEPTABLE_TOME_INGREDIENTS = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "tome/ingredients"));
    public static TagKey<Item> IS_NATURAL_GLYPH = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "glyph/natural"));
    public static TagKey<Item> IS_PARCHMENT = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "parchments"));
    public static TagKey<Item> CHALK_STONE = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "chalk_stone"));
    public static TagKey<Item> PALISTROM_LOGS = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "palistrom_logs"));
    public static TagKey<Item> WRINKLING_OAK_LOGS = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "wrinkling_oak_logs"));
    public static TagKey<Item> IS_CHALK = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "chalk"));
    public static TagKey<Item> CAN_REPAIR_MYSTIC_EQUIPMENT = TagKey.of(Registry.ITEM_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "mystic/can_repair"));

    public ItemTagData(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(IS_ACCEPTABLE_TOME_INGREDIENTS)
                .add(Items.RED_MUSHROOM)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.SPIDER_EYE)
                .add(Items.FERMENTED_SPIDER_EYE)
                .add(Items.ROTTEN_FLESH);

        getOrCreateTagBuilder(IS_NATURAL_GLYPH)
                .add(ItemRegister.FIRE_GLYPH)
                .add(ItemRegister.ICE_GLYPH)
                .add(ItemRegister.PLANT_GLYPH)
                .add(ItemRegister.LIGHT_GLYPH);

        getOrCreateTagBuilder(IS_PARCHMENT)
                .add(ItemRegister.PARCHMENT_INVISIBILITY)
                .add(ItemRegister.PARCHMENT_SAFE_HOVER);

        getOrCreateTagBuilder(CHALK_STONE)
                .add(Items.DIORITE)
                .add(Items.GRANITE)
                .add(Items.ANDESITE)
                .add(Items.STONE)
                .add(Items.DEEPSLATE);

        getOrCreateTagBuilder(PALISTROM_LOGS)
                .add(BlockRegister.PALISTROM_LOG.asItem())
                .add(BlockRegister.STRIPPED_PALISTROM_LOG.asItem())
                .add(BlockRegister.PALISTROM_WOOD.asItem())
                .add(BlockRegister.STRIPPED_PALISTROM_WOOD.asItem());

        getOrCreateTagBuilder(WRINKLING_OAK_LOGS)
                .add(BlockRegister.WRINKLING_OAK_LOG.asItem())
                .add(BlockRegister.STRIPPED_WRINKLING_OAK_LOG.asItem())
                .add(BlockRegister.WRINKLING_OAK_WOOD.asItem())
                .add(BlockRegister.STRIPPED_WRINKLING_OAK_WOOD.asItem());

        getOrCreateTagBuilder(IS_CHALK)
                .add(ItemRegister.CHALK_FIRE)
                .add(ItemRegister.CHALK_ICE)
                .add(ItemRegister.CHALK_PLANT)
                .add(ItemRegister.CHALK_LIGHT);

        getOrCreateTagBuilder(CAN_REPAIR_MYSTIC_EQUIPMENT)
                .add(Items.WHITE_WOOL)
                .add(Items.BLACK_WOOL)
                .add(Items.BROWN_WOOL)
                .add(Items.GRAY_WOOL)
                .add(Items.LIGHT_GRAY_WOOL)
                .add(Items.MAGENTA_WOOL)
                .add(Items.RED_WOOL)
                .add(Items.ORANGE_WOOL)
                .add(Items.YELLOW_WOOL)
                .add(Items.LIME_WOOL)
                .add(Items.GREEN_WOOL)
                .add(Items.BLUE_WOOL)
                .add(Items.CYAN_WOOL)
                .add(Items.LIGHT_BLUE_WOOL)
                .add(Items.PURPLE_WOOL)
                .add(Items.PINK_WOOL);
    }
}
