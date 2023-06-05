package net.dakotapride.boilingwitch.common.item;

import net.dakotapride.boilingwitch.common.register.content.ArmourMaterialsRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.List;

public class MaskOfPurityItem extends ArmorItem implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public MaskOfPurityItem(Settings settings) {
        super(ArmourMaterialsRegister.MYSTIC, EquipmentSlot.HEAD, settings);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));

        if (livingEntity instanceof ArmorStandEntity) {
            return PlayState.CONTINUE;
        }

        else if (livingEntity instanceof PlayerEntity player) {

            List<Item> equipmentList = new ArrayList<>();
            player.getItemsEquipped().forEach((x) -> equipmentList.add(x.getItem()));

            List<Item> armorList = equipmentList.subList(2, 6);

            boolean isWearingAll = armorList.contains(ItemRegister.MASK_OF_PURITY);
            return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this,
                "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
