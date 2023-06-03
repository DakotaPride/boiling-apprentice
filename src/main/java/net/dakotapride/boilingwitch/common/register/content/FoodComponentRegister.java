package net.dakotapride.boilingwitch.common.register.content;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.*;

public class FoodComponentRegister {

    public static FoodComponent RESTING_NETTLE_STEW_FOOD_COMPONENT = nettleStewBuilder();
    public static FoodComponent FAIRY_CAKE = foodComponentBuilder(3, 0.4F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 1), 0.3F).build();
    public static FoodComponent CLEANSED_FAIRY_CAKE = foodComponentBuilder(3, 0.4F).build();


    public static void register() {}

}
