package net.dakotapride.boilingwitch.client.entity.model;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.entity.LightEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LightModel extends AnimatedGeoModel<LightEntity> {

    @Override
    public Identifier getModelResource(LightEntity object) {
        return new Identifier(BoilingWitchMod.MOD_ID, "geo/light.geo.json");
    }

    @Override
    public Identifier getTextureResource(LightEntity object) {
        return new Identifier(BoilingWitchMod.MOD_ID, "textures/entity/glyph/light.png");
    }

    @Override
    public Identifier getAnimationResource(LightEntity animatable) {
        return new Identifier(BoilingWitchMod.MOD_ID, "animations/light.animation.json");
    }
}
