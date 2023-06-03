package net.dakotapride.boilingwitch.common.integration.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.integration.rei.enhancement.EnhancementCategory;
import net.dakotapride.boilingwitch.common.integration.rei.enhancement.EnhancementDisplay;
import net.dakotapride.boilingwitch.common.recipe.GlyphEnhancingRecipe;
import net.minecraft.util.Identifier;

public class BoilingWitchPlugin implements REIClientPlugin {
    public static final CategoryIdentifier<EnhancementDisplay> ENHANCEMENT =
            CategoryIdentifier.of(new Identifier(BoilingWitchMod.MOD_ID, "plugins/enhancement"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new EnhancementCategory());
        registry.addWorkstations(ENHANCEMENT, EnhancementCategory.ICON);
        registry.setPlusButtonArea(ENHANCEMENT, bounds -> null);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(GlyphEnhancingRecipe.class, EnhancementDisplay::new);
    }
}