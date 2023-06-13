package net.dakotapride.boilingwitch.common.datagen;

import net.dakotapride.boilingwitch.common.datagen.recipe.GlyphEnhancementRecipeData;
import net.dakotapride.boilingwitch.common.datagen.recipe.RecipeData;
import net.dakotapride.boilingwitch.common.datagen.tag.BlockTagData;
import net.dakotapride.boilingwitch.common.datagen.tag.InstrumentTagData;
import net.dakotapride.boilingwitch.common.datagen.tag.ItemTagData;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BoilingWitchDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(RecipeData::new);
		fabricDataGenerator.addProvider(ItemTagData::new);
		fabricDataGenerator.addProvider(BlockTagData::new);
		fabricDataGenerator.addProvider(InstrumentTagData::new);
		fabricDataGenerator.addProvider(GlyphEnhancementRecipeData::new);
	}
}
