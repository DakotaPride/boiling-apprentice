package net.dakotapride.boilingwitch.client.entity.renderer;

import net.dakotapride.boilingwitch.client.entity.model.LightModel;
import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.entity.LightEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LightRenderer extends GeoEntityRenderer<LightEntity> {
    public LightRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new LightModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(LightEntity instance) {
        return new Identifier(BoilingWitchMod.MOD_ID, "textures/entity/glyph/light.png");
    }

    @Override
    public RenderLayer getRenderType(LightEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(0.8f, 0.8f, 0.8f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
