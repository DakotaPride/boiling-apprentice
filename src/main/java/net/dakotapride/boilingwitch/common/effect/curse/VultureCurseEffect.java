package net.dakotapride.boilingwitch.common.effect.curse;

import net.dakotapride.boilingwitch.common.effect.Curse;
import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectCategory;

public class VultureCurseEffect extends Curse implements ISpellStoring {
    public VultureCurseEffect() {
        super(StatusEffectCategory.HARMFUL, 0x604387);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        provideVultureSideEffects(entity);

        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        onRemoveVultureCurseFromTarget(entity, 0.05, amplifier);

        super.onRemoved(entity, attributes, amplifier);
    }
}
