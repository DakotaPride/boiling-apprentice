package net.dakotapride.boilingwitch.common.effect.curse;

import net.dakotapride.boilingwitch.common.effect.Curse;
import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class RejuvenationCurseEffect extends Curse implements ISpellStoring {
    public RejuvenationCurseEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xFF2172);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (this == EffectRegister.REJUVENATION_CURSE && entity instanceof PlayerEntity) {
            ((PlayerEntity)entity).addExhaustion(0.05F * (float)(amplifier + 2));
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (float)(6 * (amplifier + 2)));
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (float)(6 * (amplifier + 2)));
        super.onApplied(entity, attributes, amplifier);
    }
}
