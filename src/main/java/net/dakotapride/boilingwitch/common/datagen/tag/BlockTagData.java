package net.dakotapride.boilingwitch.common.datagen.tag;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockTagData extends FabricTagProvider<Block> {
    public static TagKey<Block> PALISTROM_LOGS = TagKey.of(Registry.BLOCK_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "palistrom_logs"));
    public static TagKey<Block> WRINKLING_OAK_LOGS = TagKey.of(Registry.BLOCK_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "wrinkling_oak_logs"));
    public static TagKey<Block> AIR = TagKey.of(Registry.BLOCK_KEY,
            new Identifier("minecraft", "air"));
    public static TagKey<Block> FOOLS_BLOOD_REPLACEABLE = TagKey.of(Registry.BLOCK_KEY,
            new Identifier(BoilingWitchMod.MOD_ID, "fools_blood_replaceable"));

    public BlockTagData(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(PALISTROM_LOGS)
                .add(BlockRegister.PALISTROM_LOG)
                .add(BlockRegister.STRIPPED_PALISTROM_LOG)
                .add(BlockRegister.PALISTROM_WOOD)
                .add(BlockRegister.STRIPPED_PALISTROM_WOOD);

        getOrCreateTagBuilder(WRINKLING_OAK_LOGS)
                .add(BlockRegister.WRINKLING_OAK_LOG)
                .add(BlockRegister.STRIPPED_WRINKLING_OAK_LOG)
                .add(BlockRegister.WRINKLING_OAK_WOOD)
                .add(BlockRegister.STRIPPED_WRINKLING_OAK_WOOD);

        getOrCreateTagBuilder(AIR)
                .add(Blocks.AIR)
                .add(Blocks.CAVE_AIR)
                .add(Blocks.VOID_AIR);

        getOrCreateTagBuilder(FOOLS_BLOOD_REPLACEABLE)
                .add(Blocks.STONE)
                .add(Blocks.GRANITE)
                .add(Blocks.DIORITE)
                .add(Blocks.ANDESITE)
                .add(Blocks.DEEPSLATE)
                .add(BlockRegister.ABERRANT_STONE);
    }
}
