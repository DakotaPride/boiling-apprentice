package net.dakotapride.boilingwitch.common;

import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface IBoilingWitchItemGroup {

    static ItemStack getOcarinaStackFromNBT(ItemStack stack, int curseEffect, String curseKey, float curse) {

        stack.getOrCreateNbt().putInt("curseEffect", curseEffect);
        stack.getOrCreateNbt().putString("curseKey", curseKey);
        stack.getOrCreateNbt().putFloat("curse", curse);

        return stack;
    }

    static ItemStack getImmortalOcarina(ItemStack stack) {

        stack.getOrCreateNbt().putInt("immortal", 1);

        return stack;
    }

    ItemGroup BOILING_WITCH_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(BoilingWitchMod.MOD_ID, "boiling_witch"))
            .icon(() -> new ItemStack(ItemRegister.GLYPH.asItem())).appendItems(((itemStacks, group) -> {

                itemStacks.add(ItemRegister.OCARINA.getDefaultStack());
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 0, "avian", 0.15F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 1, "vulture", 0.3F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 2, "deviancy", 0.75F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 3, "unguarded", 0.45F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 4, "boiling", 0.6F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 5, "phantomesque", 0.9F));
                itemStacks.add(getOcarinaStackFromNBT(ItemRegister.OCARINA.getDefaultStack(), 6, "constricting", 1.0F));

            })).build();
}
