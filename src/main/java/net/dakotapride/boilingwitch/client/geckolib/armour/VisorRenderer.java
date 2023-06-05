package net.dakotapride.boilingwitch.client.geckolib.armour;

import net.dakotapride.boilingwitch.common.item.MaskOfPurityItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class VisorRenderer extends GeoArmorRenderer<MaskOfPurityItem> {
    public VisorRenderer() {
        super(new VisorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
