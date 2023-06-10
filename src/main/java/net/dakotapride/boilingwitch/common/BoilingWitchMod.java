package net.dakotapride.boilingwitch.common;

import net.dakotapride.boilingwitch.common.config.BoilingWitchConfigs;
import net.dakotapride.boilingwitch.common.register.content.*;
import net.dakotapride.boilingwitch.common.register.content.biome.BoilingWitchBiomes;
import net.dakotapride.boilingwitch.common.register.content.feature.PlacedFeatureRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class BoilingWitchMod implements ModInitializer {
	public static final String MOD_ID = "boilingwitch";
	public static final Logger LOGGER = LoggerFactory.getLogger("boilingwitch");

	@Override
	public void onInitialize() {
		BlockRegister.register();
		ItemRegister.register();
		EffectRegister.register();
		FoodComponentRegister.register();
		RecipeRegister.register();
		ScreenRegister.register();
		EnchantmentRegister.register();
		BlockEntityRegister.register();

		PlacedFeatureRegister.init();

		BoilingWitchBiomes.register();

		BoilingWitchConfigs.registerConfigs();

		GeckoLib.initialize();

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
				ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(MOD_ID, "no3d"),
						modContainer, ResourcePackActivationType.NORMAL));

	}

}