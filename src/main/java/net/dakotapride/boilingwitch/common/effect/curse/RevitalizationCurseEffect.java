package net.dakotapride.boilingwitch.common.effect.curse;

import net.dakotapride.boilingwitch.common.effect.Curse;
import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class RevitalizationCurseEffect extends Curse implements ISpellStoring {
    public RevitalizationCurseEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xFF4221);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (this == EffectRegister.REVITALIZATION_CURSE && entity instanceof PlayerEntity) {
            ((PlayerEntity)entity).addExhaustion(0.15F * (float)(amplifier + 2));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return this == EffectRegister.REVITALIZATION_CURSE;
    }
}
