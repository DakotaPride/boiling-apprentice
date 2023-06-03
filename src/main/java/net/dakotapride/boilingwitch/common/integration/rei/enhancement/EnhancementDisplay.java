package net.dakotapride.boilingwitch.common.integration.rei.enhancement;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.dakotapride.boilingwitch.common.integration.rei.BoilingWitchPlugin;
import net.dakotapride.boilingwitch.common.recipe.GlyphEnhancingRecipe;

import java.util.Collections;
import java.util.List;

public class EnhancementDisplay implements Display {
    private final List<EntryIngredient> input;
    private final List<EntryIngredient> output;

    public EnhancementDisplay(GlyphEnhancingRecipe recipe) {
        input = EntryIngredients.ofIngredients(recipe.getIngredients());
        output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return BoilingWitchPlugin.ENHANCEMENT;
    }
}