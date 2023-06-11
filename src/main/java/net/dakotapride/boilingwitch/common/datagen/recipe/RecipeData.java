package net.dakotapride.boilingwitch.common.datagen.recipe;

import net.dakotapride.boilingwitch.common.datagen.tag.ItemTagData;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeData extends FabricRecipeProvider {

    public RecipeData(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        ShapelessRecipeJsonBuilder.create(ItemRegister.RUINED_LETTER)
                .input(Items.PAPER)
                .criterion("has_paper", RecipeProvider.conditionsFromItem(Items.PAPER))
                .input(BlockRegister.ABERRANT_STONE.asItem())
                .criterion("has_stone", RecipeProvider.conditionsFromItem(BlockRegister.ABERRANT_STONE.asItem()))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ItemRegister.RUINED_LETTER)));

        ShapelessRecipeJsonBuilder.create(ItemRegister.RESTING_NETTLE_STEW)
                .input(Items.BOWL)
                .criterion("has_bowl", RecipeProvider.conditionsFromItem(Items.BOWL))
                .input(BlockRegister.RESTING_NETTLE)
                .criterion("has_nettle", RecipeProvider.conditionsFromItem(BlockRegister.RESTING_NETTLE))
                .input(Items.RED_MUSHROOM)
                .criterion("has_mushroom", RecipeProvider.conditionsFromItem(Items.RED_MUSHROOM))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ItemRegister.RESTING_NETTLE_STEW)));

        // Chalk
        ShapelessRecipeJsonBuilder.create(ItemRegister.CHALK)
                .input(Items.HONEYCOMB)
                .criterion("has_honeycomb", RecipeProvider.conditionsFromItem(Items.HONEYCOMB))
                .input(ItemTagData.CHALK_STONE)
                .criterion("has_stone_for_chalk", RecipeProvider.conditionsFromTag(ItemTagData.CHALK_STONE))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ItemRegister.CHALK)));

        String chalk = "chalk/"; // TODO: Make The Chalk System More Interactive For Players
        ShapelessRecipeJsonBuilder.create(ItemRegister.CHALK_FIRE)
                .input(ItemRegister.CHALK)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(ItemRegister.CHALK))
                .input(Items.MAGMA_CREAM)
                .criterion("has_chalk_ingredient", RecipeProvider.conditionsFromItem(Items.MAGMA_CREAM))
                .offerTo(exporter, new Identifier(chalk + "fire"));
        ShapelessRecipeJsonBuilder.create(ItemRegister.CHALK_ICE)
                .input(ItemRegister.CHALK)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(ItemRegister.CHALK))
                .input(Items.ICE)
                .criterion("has_chalk_ingredient", RecipeProvider.conditionsFromItem(Items.ICE))
                .offerTo(exporter, new Identifier(chalk + "ice"));
        ShapelessRecipeJsonBuilder.create(ItemRegister.CHALK_PLANT)
                .input(ItemRegister.CHALK)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(ItemRegister.CHALK))
                .input(Items.GRASS)
                .criterion("has_chalk_ingredient", RecipeProvider.conditionsFromItem(Items.GRASS))
                .offerTo(exporter, new Identifier(chalk + "plant"));
        ShapelessRecipeJsonBuilder.create(ItemRegister.CHALK_LIGHT)
                .input(ItemRegister.CHALK)
                .criterion("has_chalk", RecipeProvider.conditionsFromItem(ItemRegister.CHALK))
                .input(Items.CANDLE)
                .criterion("has_chalk_ingredient", RecipeProvider.conditionsFromItem(Items.CANDLE))
                .offerTo(exporter, new Identifier(chalk + "light"));

        // Glyphs
        ShapelessRecipeJsonBuilder.create(ItemRegister.GLYPH)
                .input(Items.PAPER)
                .criterion("has_paper", RecipeProvider.conditionsFromItem(Items.PAPER))
                .input(ItemTagData.IS_ACCEPTABLE_TOME_INGREDIENTS)
                .criterion("has_tome_ingredients", RecipeProvider.conditionsFromTag(ItemTagData.IS_ACCEPTABLE_TOME_INGREDIENTS))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ItemRegister.GLYPH)));

        // TODO: Expand Upon The Existing Glyph System And Add More Abilities to Already Existing Glyphs

        ShapedRecipeJsonBuilder.create(ItemRegister.MASK_OF_PURITY)
                .pattern(" # ")
                .pattern("AXA")
                .input('#', Items.ENDER_PEARL)
                .criterion("has_ender_pearl", RecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .input('A', Items.PURPLE_WOOL)
                .criterion("has_wool", RecipeProvider.conditionsFromItem(Items.PURPLE_WOOL))
                .input('X', Items.STRING)
                .criterion("has_string", RecipeProvider.conditionsFromItem(Items.STRING))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ItemRegister.MASK_OF_PURITY)));

        // Wood Types
        // Planks
        ShapelessRecipeJsonBuilder.create(BlockRegister.PALISTROM_PLANKS, 4)
                .input(ItemTagData.PALISTROM_LOGS)
                .criterion("has_palistrom_wood", RecipeProvider.conditionsFromTag(ItemTagData.PALISTROM_LOGS))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(BlockRegister.PALISTROM_PLANKS)));

        ShapelessRecipeJsonBuilder.create(BlockRegister.WRINKLING_OAK_PLANKS, 4)
                .input(ItemTagData.WRINKLING_OAK_LOGS)
                .criterion("has_wrinkling_oak_wood", RecipeProvider.conditionsFromTag(ItemTagData.WRINKLING_OAK_LOGS))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(BlockRegister.WRINKLING_OAK_PLANKS)));

        // Woods
        ShapedRecipeJsonBuilder.create(BlockRegister.PALISTROM_WOOD, 3)
                .pattern("XX")
                .pattern("XX")
                .input('X', BlockRegister.PALISTROM_LOG.asItem())
                .criterion("has_palistrom_log", RecipeProvider.conditionsFromItem(BlockRegister.PALISTROM_LOG.asItem()))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(BlockRegister.PALISTROM_WOOD)));

        ShapedRecipeJsonBuilder.create(BlockRegister.WRINKLING_OAK_WOOD, 3)
                .pattern("XX")
                .pattern("XX")
                .input('X', BlockRegister.WRINKLING_OAK_LOG.asItem())
                .criterion("has_wrinkling_oak_log", RecipeProvider.conditionsFromItem(BlockRegister.WRINKLING_OAK_LOG.asItem()))
                .offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(BlockRegister.WRINKLING_OAK_WOOD)));

    }
}
