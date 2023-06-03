package net.dakotapride.boilingwitch.mixin;

import com.google.common.collect.Lists;
import net.dakotapride.boilingwitch.client.fog.VultureFogModifier;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    private static final List<BackgroundRenderer.StatusEffectFogModifier> VULTURE_MODIFIER =
            Lists.newArrayList(new VultureFogModifier());

    @Inject(method = "getFogModifier", at = @At("HEAD"), cancellable = true)
    private static void getFogModifierForVultureCurse(Entity entity, float tickDelta,
                                                      CallbackInfoReturnable<BackgroundRenderer.StatusEffectFogModifier> cir) {

        if (entity instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(EffectRegister.VULTURE_CURSE)) {
            cir.setReturnValue(VULTURE_MODIFIER.stream().filter((modifier) ->
                    modifier.shouldApply(livingEntity, tickDelta)).findFirst().orElse(null));
        }

    }

}
