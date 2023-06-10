package net.dakotapride.boilingwitch.mixin;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.item.magic.ISpellStoring;
import net.dakotapride.boilingwitch.common.register.content.DamageSourceRegister;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.dakotapride.boilingwitch.common.register.content.EnchantmentRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ISpellStoring {
    @Shadow protected int playerHitTimer;
    private static final Identifier BOILING_ISLES_KEY = new Identifier(BoilingWitchMod.MOD_ID, "isles");

    LivingEntity livingEntity = (LivingEntity) (Object) this;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void applyBoilingDamage(CallbackInfo ci) {
        if (livingEntity.isAlive()) {
            if (livingEntity.isTouchingWaterOrRain() && livingEntity.getWorld().getRegistryKey().getValue().equals(BOILING_ISLES_KEY)
                    && !hasBoilingCurse(livingEntity)) {
                livingEntity.damage(DamageSourceRegister.BOILING_SEA, 1.0F);
            }

            if (livingEntity.isTouchingWaterOrRain() && hasBoilingCurse(livingEntity)) {
                if (livingEntity.getWorld().getRegistryKey().getValue().equals(BOILING_ISLES_KEY)) {
                    livingEntity.damage(DamageSourceRegister.BOILING_SEA, 4.0F);
                } else {
                    livingEntity.damage(DamageSourceRegister.BOILING_SEA, 1.0F);
                }
            }
        }
    }

    // @Inject(method = "baseTick", at = @At("HEAD"))
    //    private void getME(CallbackInfo ci) {
    //        if (livingEntity instanceof PlayerEntity player && player.getMainHandStack().getItem() instanceof MysticItem mysticItem
    //                && player.getEquippedStack(EquipmentSlot.HEAD).isOf(ItemRegister.MASK_OF_PURITY)) {
    //            player.sendMessage(Text.literal("ME: " + mysticItem.getMysticEnergy()), true);
    //        }
    //    }

    @Inject(method = "onAttacking", at = @At("HEAD"))
    private void applyInsanityOnAttack(Entity target, CallbackInfo ci) {
        if (target instanceof LivingEntity livingTarget) {
            if (EnchantmentHelper.getLevel(EnchantmentRegister.DEMENTED, livingEntity.getOffHandStack()) > 0) {
                livingTarget.addStatusEffect(new StatusEffectInstance(EffectRegister.INSANITY, 100, 0));
            } else if (EnchantmentHelper.getLevel(EnchantmentRegister.INVERSE, livingEntity.getOffHandStack()) > 0) {
                livingTarget.heal(8.0F);
            }
        }
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void getInsanityModifiers(Vec3d movementInput, CallbackInfo ci) {
        if (livingEntity.hasStatusEffect(EffectRegister.INSANITY)) {
            livingEntity.setYaw(1.0F);
            livingEntity.onLanding();
        }
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void getHoistConfiguration(Vec3d movementInput, CallbackInfo ci) {
        if (livingEntity.canMoveVoluntarily() || livingEntity.isLogicalSideForUpdatingMovement()) {
            boolean bl = livingEntity.getVelocity().y <= 0.0;
            if (bl && livingEntity.hasStatusEffect(EffectRegister.HOISTING)) {
                livingEntity.onLanding();
            }
        }
    }

    @Inject(method = "travel", at = @At("HEAD"))
    private void applyMovementModifierByCurse(Vec3d movementInput, CallbackInfo ci) {
        if (livingEntity.canMoveVoluntarily() || livingEntity.isLogicalSideForUpdatingMovement()) {
            boolean bl = livingEntity.getVelocity().y <= 0.0;
            if (bl && hasAvianCurse(livingEntity) || bl && hasVultureCurse(livingEntity)) {
                livingEntity.setVelocity(livingEntity.getVelocity().multiply(1,0.6,1));

                livingEntity.onLanding();
            }
        }
    }

    @Inject(method = "getJumpBoostVelocityModifier", at = @At("HEAD"), cancellable = true)
    private void applyHoistingModifiers(CallbackInfoReturnable<Double> cir) {
        if (livingEntity.hasStatusEffect(EffectRegister.HOISTING)) {
            cir.setReturnValue(livingEntity.hasStatusEffect(EffectRegister.HOISTING) ? (double)
                    (0.2F * (float)(livingEntity.getStatusEffect(EffectRegister.HOISTING).getAmplifier() + 1)) : 0.0);
        }
    }
}
