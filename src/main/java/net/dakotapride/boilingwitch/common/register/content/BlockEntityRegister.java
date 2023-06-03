package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.block.entity.GlyphEnhancementTableEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegister {
    public static BlockEntityType<GlyphEnhancementTableEntity> GLYPH_ENHANCEMENT_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(BoilingWitchMod.MOD_ID, "glyph_enhancement_table"),
                    FabricBlockEntityTypeBuilder.create(GlyphEnhancementTableEntity::new,
                            BlockRegister.GLYPH_ENHANCEMENT_TABLE).build(null));

    public static void register() {}
}
