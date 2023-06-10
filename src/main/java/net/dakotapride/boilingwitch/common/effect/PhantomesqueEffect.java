package net.dakotapride.boilingwitch.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class PhantomesqueEffect extends StatusEffect {
    public PhantomesqueEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x88FF00);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if (entity instanceof PlayerEntity playerEntity) {
            var abilities = playerEntity.getAbilities();

            abilities.allowFlying = true;
        }

        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if (entity instanceof PlayerEntity playerEntity) {
            var abilities = playerEntity.getAbilities();

            abilities.allowFlying = false;
        }

        super.onRemoved(entity, attributes, amplifier);
    }
}
