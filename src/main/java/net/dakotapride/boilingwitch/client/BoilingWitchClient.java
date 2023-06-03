package net.dakotapride.boilingwitch.client;

import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.dakotapride.boilingwitch.common.register.content.ScreenRegister;
import net.dakotapride.boilingwitch.common.screen.screen.GlyphEnhancementScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class BoilingWitchClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.RESTING_NETTLE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.PALISTROM_SEEDS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.WRINKLING_OAK_SAPLING, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.GLYPH_ENHANCEMENT_TABLE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.WRINKLING_OAK_STAIRS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.WRINKLING_OAK_SLAB, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.WRINKLING_OAK_FENCE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.WRINKLING_OAK_FENCE_GATE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.HOISTING_BLOCK, RenderLayer.getCutout());

		HandledScreens.register(ScreenRegister.ENHANCEMENT_HANDLER, GlyphEnhancementScreen::new);
	}
}