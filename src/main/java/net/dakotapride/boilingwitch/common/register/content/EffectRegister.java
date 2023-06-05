package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.effect.HoistingEffect;
import net.dakotapride.boilingwitch.common.effect.InsanityEffect;
import net.dakotapride.boilingwitch.common.effect.curse.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.registerEffect;

public class EffectRegister {

    // Curses
    public static StatusEffect AVIAN_CURSE = registerEffect("avian_curse", new AvianCurseEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "163eb67c-c9b2-49af-a734-5efce78e68d9",
                    -0.2247, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "de59f6de-d9ee-42fa-a4f6-21ec74045ae0",
                    0.46, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static StatusEffect VULTURE_CURSE = registerEffect("vulture_curse", new VultureCurseEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "163eb67c-c9b2-49af-a734-5efce78e68d9",
                    -0.2247, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "de59f6de-d9ee-42fa-a4f6-21ec74045ae0",
                    0.64, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static StatusEffect DEVIANCY_CURSE = registerEffect("deviancy_curse", new DeviancyCurseEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "163eb67c-c9b2-49af-a734-5efce78e68d9",
                    0.2247, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "9248a30b-beb5-4d9c-ad98-1d363358413a",
                    -0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static StatusEffect UNGUARDED_CURSE = registerEffect("unguarded_curse", new UnguardedCurseEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "9e6749cb-51d7-4429-be56-18a220c0c92a",
                    -0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
    public static StatusEffect BOILING_CURSE = registerEffect("boiling_curse", new BoilingCurseEffect());


    // Non-curses
    public static StatusEffect HOISTING = registerEffect("hoisting", new HoistingEffect());
    public static StatusEffect INSANITY = registerEffect("uphold", new InsanityEffect());

    public static void register() {}

}
