package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.recipe.GlyphEnhancingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegister {

    public static RecipeSerializer<GlyphEnhancingRecipe> ENHANCEMENT_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER,
            new Identifier(BoilingWitchMod.MOD_ID, GlyphEnhancingRecipe.Serializer.ID), GlyphEnhancingRecipe.Serializer.INSTANCE);

    public static RecipeType<GlyphEnhancingRecipe> ENHANCEMENT_TYPE = Registry.register(Registry.RECIPE_TYPE,
            new Identifier(BoilingWitchMod.MOD_ID, GlyphEnhancingRecipe.Type.ID), GlyphEnhancingRecipe.Type.INSTANCE);

    public static void register() {}
}
