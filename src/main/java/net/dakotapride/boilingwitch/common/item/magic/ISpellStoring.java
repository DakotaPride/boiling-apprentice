package net.dakotapride.boilingwitch.common.item.magic;

import net.dakotapride.boilingwitch.common.register.content.DamageSourceRegister;
import net.dakotapride.boilingwitch.common.register.content.EffectRegister;
import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public interface ISpellStoring extends IGlyph {
    Random random = new Random();
    String fireModifier = "fire";
    String plantModifier = "plant";
    String lightModifier = "light";
    String iceModifier = "ice";

    String glyphMedium = "glyph";
    String chalkMedium = "chalk";
    String parchmentMedium = "parchment";

    String curseValue = "curse";



    // Curse/Bless With Effects (onApply / onRemove)

    // Give Base Effect
    default void giveTargetAvianCurse(LivingEntity entity, int duration, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(EffectRegister.AVIAN_CURSE, duration, amplifier));
    }

    default void giveTargetVultureCurse(LivingEntity entity, int duration, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(EffectRegister.VULTURE_CURSE, duration, amplifier));
    }

    // Add Effects

    default void provideVultureSideEffects(LivingEntity entity) {
        provideTargetWithDamageFromCurse(10.0F, entity, DamageSourceRegister.VULTURE_RETRIBUTION);
    }

    default void provideTargetWithDamageFromCurse(float damageAmount, LivingEntity entity, DamageSource source) {
        entity.damage(source, damageAmount);
    }

    default void provideTargetWithHungerFromCurse(float exhaustion, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            player.addExhaustion(exhaustion);
        }
    }

    // Remove Effect
    default void onRemoveVultureCurseFromTarget(LivingEntity entity, double exhaustion, int amplifier) {
        createStatusEffectAsResultFromCurse(entity, StatusEffects.SLOWNESS, 400, 1);
        ((PlayerEntity)entity).addExhaustion((int) exhaustion * (float)(amplifier + 1));
    }

    default void createStatusEffectAsResultFromCurse(LivingEntity entity, StatusEffect effect, int duration, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
    }

    default void regenerateHealthFromCurse(float regenerateHealth, LivingEntity entity, StatusEffectInstance effectInstance) {
        entity.heal(regenerateHealth);

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, effectInstance.getDuration(), effectInstance.getAmplifier()));
    }


    // Has Curse

    default boolean hasAvianCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.AVIAN_CURSE);
    }

    default boolean hasBoilingCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.BOILING_CURSE);
    }

    default boolean hasVultureCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.VULTURE_CURSE);
    }

    default boolean hasUnguardedCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.UNGUARDED_CURSE);
    }

    default boolean hasDeviancyCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.DEVIANCY_CURSE);
    }

    default boolean hasPhantomesqueCurse(LivingEntity entity) {
        return entity.hasStatusEffect(EffectRegister.PHANTOMESQUE_CURSE);
    }


    // Misc (Tooltip, Is Of Spells, etc.)

    default void playCurseSound(World world, BlockPos pos) {
        world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
    }

    default void spawnCurseParticles(World world, BlockPos pos, ItemStack stack, int count, LivingEntity entity) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                world.addParticle(ParticleTypes.ENCHANT,
                        pos.getX() + 0.5d, pos.getY() + 1, pos.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }

        for(int i = 0; i < count; ++i) {
            Vec3d vec3d = new Vec3d(((double)this.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            vec3d = vec3d.rotateX(-entity.getPitch() * 0.017453292F);
            vec3d = vec3d.rotateY(-entity.getYaw() * 0.017453292F);
            double d = (double)(-this.random.nextFloat()) * 0.6 - 0.3;
            Vec3d vec3d2 = new Vec3d(((double)this.random.nextFloat() - 0.5) * 0.3, d, 0.6);
            vec3d2 = vec3d2.rotateX(-entity.getPitch() * 0.017453292F);
            vec3d2 = vec3d2.rotateY(-entity.getYaw() * 0.017453292F);
            vec3d2 = vec3d2.add(entity.getX(), entity.getEyeY(), entity.getZ());
            world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), vec3d2.x, vec3d2.y, vec3d2.z, vec3d.x, vec3d.y + 0.05, vec3d.z);
        }
    }

    default void curseEntity(LivingEntity entity, Hand hand) {
        BlockPos pos = entity.getBlockPos();
        World world = entity.getWorld();
        ItemStack stack = entity.getStackInHand(hand);
        int count = 10;


        playCurseSound(world, pos);
        spawnCurseParticles(world, pos, stack, count, entity);
    }

    default boolean isFireRelatedSpell(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.FIRE_GLYPH) || item.getDefaultStack().isOf(ItemRegister.CHALK_FIRE);
    }

    default boolean isPlantRelatedSpell(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.PLANT_GLYPH) || item.getDefaultStack().isOf(ItemRegister.CHALK_PLANT);
    }

    default boolean isIceRelatedSpell(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.ICE_GLYPH) || item.getDefaultStack().isOf(ItemRegister.CHALK_ICE);
    }

    default boolean isLightRelatedSpell(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.LIGHT_GLYPH) || item.getDefaultStack().isOf(ItemRegister.CHALK_LIGHT);
    }

    default boolean isOfCursedParchment(Item item) {
        return isVultureParchment(item) || isAvianParchment(item)
                || isUnguardedParchment(item) || isDeviancyParchment(item)
                || isBoilingParchment(item);
    }


    default void setSpellTooltip(List<Text> text, ItemStack stack, String spellMedium, String spellModifier) {
        if (!isOfCursedParchment(stack.getItem())) {
            text.add(Text.translatable("text.boilingwitch.spell." + spellMedium + "." + spellModifier).formatted(Formatting.GRAY));
        } else {
            text.add(Text.translatable("text.boilingwitch.spell." + spellMedium + "." + spellModifier + "." + curseValue).formatted(Formatting.GRAY));
        }
    }

    // Get Parchment
    default boolean isVultureParchment(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.CURSED_PARCHMENT_VULTURE);
    }

    default boolean isAvianParchment(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.CURSED_PARCHMENT_AVIAN);
    }

    default boolean isUnguardedParchment(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.CURSED_PARCHMENT_UNGUARDED);
    }

    default boolean isDeviancyParchment(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.CURSED_PARCHMENT_DEVIANCY);
    }

    default boolean isBoilingParchment(Item item) {
        return item.getDefaultStack().isOf(ItemRegister.CURSED_PARCHMENT_BOILING);
    }


}
