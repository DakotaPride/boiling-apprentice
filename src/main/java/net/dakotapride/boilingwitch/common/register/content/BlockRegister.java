package net.dakotapride.boilingwitch.common.register.content;

import net.dakotapride.boilingwitch.common.IBoilingWitchItemGroup;
import net.dakotapride.boilingwitch.common.block.GlyphEnhancementTableBlock;
import net.dakotapride.boilingwitch.common.block.HoistingBlock;
import net.dakotapride.boilingwitch.common.block.RestingNettleBlock;
import net.dakotapride.boilingwitch.common.block.saplingGenerator.WrinklingOakSaplingGenerator;
import net.dakotapride.boilingwitch.common.block.tree.PalistromSaplingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

import static net.dakotapride.boilingwitch.common.register.BoilingWitchRegistration.registerBlock;

public class BlockRegister implements IBoilingWitchItemGroup {

    public static Block RESTING_NETTLE = registerBlock("resting_nettle",
            new RestingNettleBlock(FabricBlockSettings.copy(Blocks.WITHER_ROSE)), BOILING_WITCH_GROUP);

    public static Block GLYPH_ENHANCEMENT_TABLE = registerBlock("glyph_enhancement_table",
            new GlyphEnhancementTableBlock(FabricBlockSettings.copy(Blocks.CARTOGRAPHY_TABLE)), BOILING_WITCH_GROUP);

    public static Block PALISTROM_LOG = registerBlock("palistrom_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), BOILING_WITCH_GROUP);
    public static Block STRIPPED_PALISTROM_LOG = registerBlock("stripped_palistrom_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), BOILING_WITCH_GROUP);
    public static Block PALISTROM_WOOD = registerBlock("palistrom_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), BOILING_WITCH_GROUP);
    public static Block STRIPPED_PALISTROM_WOOD = registerBlock("stripped_palistrom_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), BOILING_WITCH_GROUP);
    public static Block PALISTROM_LEAVES = registerBlock("palistrom_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), BOILING_WITCH_GROUP);
    public static Block PALISTROM_PLANKS = registerBlock("palistrom_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), BOILING_WITCH_GROUP);
    public static Block PALISTROM_SEEDS = registerBlock("palistrom_sapling",
            new PalistromSaplingBlock(FabricBlockSettings.copy(Blocks.OAK_SAPLING)), BOILING_WITCH_GROUP);

    public static Block WRINKLING_OAK_LOG = registerBlock("wrinkling_oak_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), BOILING_WITCH_GROUP);
    public static Block STRIPPED_WRINKLING_OAK_LOG = registerBlock("stripped_wrinkling_oak_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_WOOD = registerBlock("wrinkling_oak_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), BOILING_WITCH_GROUP);
    public static Block STRIPPED_WRINKLING_OAK_WOOD = registerBlock("stripped_wrinkling_oak_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_LEAVES = registerBlock("wrinkling_oak_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_PLANKS = registerBlock("wrinkling_oak_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_SAPLING = registerBlock("wrinkling_oak_sapling",
            new SaplingBlock(new WrinklingOakSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_STAIRS = registerBlock("wrinkling_oak_stairs",
            new StairsBlock(WRINKLING_OAK_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_SLAB = registerBlock("wrinkling_oak_slab",
            new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_FENCE = registerBlock("wrinkling_oak_fence",
            new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE)), BOILING_WITCH_GROUP);
    public static Block WRINKLING_OAK_FENCE_GATE = registerBlock("wrinkling_oak_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE)), BOILING_WITCH_GROUP);

    public static Block ABERRANT_STONE = registerBlock("aberrant_stone",
            new Block(FabricBlockSettings.copy(Blocks.STONE)), BOILING_WITCH_GROUP);

    public static Block HOISTING_BLOCK = registerBlock("hoisting_block",
            new HoistingBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), BOILING_WITCH_GROUP);

    public static Block TITAN_BONE_BLOCK = registerBlock("titan_bone_block",
            new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK)), BOILING_WITCH_GROUP);

    public static Block FOOLS_BLOOD_BLOCK = registerBlock("fools_blood_block",
            new Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE)
                    .slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS).luminance((state) -> 7)), BOILING_WITCH_GROUP);


    public static void register() {}

}
