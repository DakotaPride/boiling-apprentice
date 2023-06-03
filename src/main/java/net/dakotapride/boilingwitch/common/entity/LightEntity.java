package net.dakotapride.boilingwitch.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class LightEntity extends AmbientEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private static final TrackedData<BlockPos> BLOCK_POSITION = DataTracker.registerData
            (LightEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);

    public LightEntity(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AmbientEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.light.hover", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM;
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(BLOCK_POSITION, this.getBlockPos());
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
