package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.screen.handler.GlyphEnhancementScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ScreenRegister {
    public static ScreenHandlerType<GlyphEnhancementScreenHandler> ENHANCEMENT_HANDLER =
            new ScreenHandlerType<>(GlyphEnhancementScreenHandler::new);

    public static void register() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(BoilingWitchMod.MOD_ID,
                "glyph_enhancement"), ENHANCEMENT_HANDLER);
    }
}
