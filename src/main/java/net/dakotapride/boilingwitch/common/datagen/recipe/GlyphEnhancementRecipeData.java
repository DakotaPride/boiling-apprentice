package net.dakotapride.boilingwitch.common.datagen.recipe;

import net.dakotapride.boilingwitch.common.datagen.recipe.enhancement.GlyphEnhancementJsonBuilder;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class GlyphEnhancementRecipeData extends FabricRecipeProvider {
    public GlyphEnhancementRecipeData(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        createBasicGlyphRegistry(exporter, ItemRegister.PLANT_GLYPH, 1, ItemTags.SMALL_FLOWERS,
                "flowers", ItemRegister.CHALK_PLANT, "glyph/plant");
        createBasicGlyphRegistry(exporter, ItemRegister.FIRE_GLYPH, 1, Items.QUARTZ,
                "quartz", ItemRegister.CHALK_FIRE, "glyph/fire");
        createBasicGlyphRegistry(exporter, ItemRegister.ICE_GLYPH, 1, Items.PACKED_ICE,
                "packed_ice", ItemRegister.CHALK_ICE, "glyph/ice");
        createBasicGlyphRegistry(exporter, ItemRegister.LIGHT_GLYPH, 1, Items.GLOWSTONE_DUST,
                "glowstone_dust", ItemRegister.CHALK_LIGHT, "glyph/light");


    }

    private void createBasicGlyphRegistry(Consumer<RecipeJsonProvider> exporter, Item item, int count,
                                          Item elementIngredient, String ofItem, Item chalk, String recipeName) {
        GlyphEnhancementJsonBuilder.create(item, count)
                .input(ItemRegister.MYSTIC_ESSENCE)
                .criterion("has_essence", RecipeProvider.conditionsFromItem(ItemRegister.MYSTIC_ESSENCE))
                .input(elementIngredient)
                .input(elementIngredient)
                .criterion("has_" + ofItem, RecipeProvider.conditionsFromItem(elementIngredient))
                .input(chalk)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(chalk))
                .input(ItemRegister.GLYPH)
                .criterion("has_glyph", RecipeProvider.conditionsFromItem(ItemRegister.GLYPH))
                .offerTo(exporter, new Identifier(recipeName));
    }

    private void createBasicGlyphRegistry(Consumer<RecipeJsonProvider> exporter, Item item, int count,
                                          TagKey<Item> elementIngredient, String hasOf, Item chalk, String recipeName) {
        GlyphEnhancementJsonBuilder.create(item, count)
                .input(ItemRegister.MYSTIC_ESSENCE)
                .criterion("has_essence", RecipeProvider.conditionsFromItem(ItemRegister.MYSTIC_ESSENCE))
                .input(elementIngredient)
                .input(elementIngredient)
                .criterion("has_" + hasOf, RecipeProvider.conditionsFromTag(elementIngredient))
                .input(chalk)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(chalk))
                .input(ItemRegister.GLYPH)
                .criterion("has_glyph", RecipeProvider.conditionsFromItem(ItemRegister.GLYPH))
                .offerTo(exporter, new Identifier(recipeName));
    }
}
