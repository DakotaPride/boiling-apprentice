package net.dakotapride.boilingwitch.common.register.content;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
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
    public static StatusEffect PHANTOMESQUE_CURSE = registerEffect("phantomesque_curse", new PhantomesqueCurseEffect()
            .addAttributeModifier(ReachEntityAttributes.REACH, "8a944592-387b-47c5-be34-1fe1218eb43a",
                    2.75, EntityAttributeModifier.Operation.ADDITION)
            .addAttributeModifier(ReachEntityAttributes.ATTACK_RANGE, "889cbf0a-a4aa-420e-86e6-735c550bbb3d",
                    2.75, EntityAttributeModifier.Operation.ADDITION))
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "3f7d5e30-746c-46c8-b6bd-0183251fb28e",
                    -2.0, EntityAttributeModifier.Operation.ADDITION);
    public static StatusEffect CONSTRICTING_CURSE = registerEffect("constricting_curse", new ConstrictingCurseEffect()
            .addAttributeModifier(ReachEntityAttributes.REACH, "99caa763-d7e3-4c91-8379-4cb379440739",
                    -1.15, EntityAttributeModifier.Operation.ADDITION)
            .addAttributeModifier(ReachEntityAttributes.ATTACK_RANGE, "5cc7fb3d-ee86-4438-862f-05141dc9adae",
                    -1.15, EntityAttributeModifier.Operation.ADDITION))
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "0a0d3261-aa42-4e47-98a6-794949244a37",
                    4.0, EntityAttributeModifier.Operation.ADDITION);
    public static StatusEffect REJUVENATION_CURSE = registerEffect("rejuvenation_curse", new RejuvenationCurseEffect());
    public static StatusEffect REVITALIZATION_CURSE = registerEffect("revitalization_curse", new RevitalizationCurseEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "6e3a453b-a594-4a83-aa7d-742cfdf226ce",
                    6.0, EntityAttributeModifier.Operation.ADDITION));


    // Non-curses
    public static StatusEffect HOISTING = registerEffect("hoisting", new HoistingEffect());
    public static StatusEffect INSANITY = registerEffect("insanity", new InsanityEffect());

    public static void register() {}

}
