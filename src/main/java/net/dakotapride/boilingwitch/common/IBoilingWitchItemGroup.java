package net.dakotapride.boilingwitch.common;

import net.dakotapride.boilingwitch.common.register.content.ItemRegister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface IBoilingWitchItemGroup {
    ItemGroup BOILING_WITCH_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(BoilingWitchMod.MOD_ID, "boiling_witch"))
            .icon(() -> new ItemStack(ItemRegister.GLYPH.asItem())).build();
}
