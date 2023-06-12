package net.dakotapride.boilingwitch.common.register;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BoilingWitchRegistration {
    static StatusEffectInstance nettleEffectInstance = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1);

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BoilingWitchMod.MOD_ID, name), item);
    }

    public static Item registerBlockItem(String name, BlockItem blockItem) {
        return Registry.register(Registry.ITEM, new Identifier(BoilingWitchMod.MOD_ID, name), blockItem);
    }

    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(BoilingWitchMod.MOD_ID, name), block);
    }

    public static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(BoilingWitchMod.MOD_ID, name), block);
    }

    static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(BoilingWitchMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static FoodComponent.Builder foodComponentBuilder(int hunger, float saturation) {
        return new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation);
    }

    public static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(BoilingWitchMod.MOD_ID, name), effect);
    }

    public static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(BoilingWitchMod.MOD_ID, name), enchantment);
    }

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BoilingWitchMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static FoodComponent nettleStewBuilder() {
        return foodComponentBuilder(5, 0.3F).alwaysEdible().statusEffect(nettleEffectInstance, 1.0F).build();
    }

}
