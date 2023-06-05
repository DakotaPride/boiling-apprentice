package net.dakotapride.boilingwitch.client.geckolib.armour;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.item.MaskOfPurityItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VisorModel extends AnimatedGeoModel<MaskOfPurityItem> {

    @Override
    public Identifier getModelResource(MaskOfPurityItem object) {
        return new Identifier(BoilingWitchMod.MOD_ID, "geo/visor_brewing.geo.json");
    }

    @Override
    public Identifier getTextureResource(MaskOfPurityItem object) {
        return new Identifier(BoilingWitchMod.MOD_ID, "textures/models/armor/visor.png");
    }

    @Override
    public Identifier getAnimationResource(MaskOfPurityItem animatable) {
        return new Identifier(BoilingWitchMod.MOD_ID, "animations/visor.animation.json");
    }
}
