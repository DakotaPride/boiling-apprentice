package net.dakotapride.boilingwitch.client.fog;

import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class VultureFogModifier implements BackgroundRenderer.StatusEffectFogModifier {
    public VultureFogModifier() {}

    @Override
    public float applyColorModifier(LivingEntity entity, StatusEffectInstance effect, float f, float tickDelta) {
        return 0x604387;
    }

    @Override
    public StatusEffect getStatusEffect() {
        return EffectRegister.VULTURE_CURSE;
    }

    @Override
    public void applyStartEndModifier(BackgroundRenderer.FogData fogData, LivingEntity entity,
                                      StatusEffectInstance effect, float viewDistance, float tickDelta) {
        float f = MathHelper.lerp(Math.min(1.0F, (float)effect.getDuration() / 20.0F), viewDistance, 5.0F);
        if (fogData.fogType == BackgroundRenderer.FogType.FOG_SKY) {
            fogData.fogStart = 0.0F;
            fogData.fogEnd = f * 0.8F;
        } else {
            fogData.fogStart = f * 0.25F;
            fogData.fogEnd = f;
        }
    }
}
