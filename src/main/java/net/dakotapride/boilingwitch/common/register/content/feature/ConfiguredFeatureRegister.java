package net.dakotapride.boilingwitch.common.register.content.feature;

import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.datagen.tag.BlockTagData;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.OptionalInt;

public class ConfiguredFeatureRegister {
    private static final RuleTest FOOLS_BLOOD_REPLACEABLE = new TagMatchRuleTest(BlockTagData.FOOLS_BLOOD_REPLACEABLE);

    //public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PALISTROM_TREE =
    //        register("palistrom_tree", Feature.TREE, FeatureConfigRegistry.PALISTROM_TREE_CONFIG);

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_FOOLS_BLOOD =
            register("ore_fools_blood", Feature.ORE, new OreFeatureConfig(
                    FOOLS_BLOOD_REPLACEABLE, BlockRegister.FOOLS_BLOOD_BLOCK.getDefaultState(), 32));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> RESTING_NETTLE_PATCH =
            register("resting_nettle_patch", Feature.FLOWER, new RandomPatchFeatureConfig(16, 6, 2,
                    PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(BlockRegister.RESTING_NETTLE)))));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PALISTROM_TREE =
            register("palistrom", Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegister.PALISTROM_LOG),
                    new DarkOakTrunkPlacer(8, 4, 1), BlockStateProvider.of(BlockRegister.PALISTROM_LEAVES),
                    new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                    new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WRINKLING_OAK_TREE =
            register("wrinkling_oak", Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegister.WRINKLING_OAK_LOG),
                    new StraightTrunkPlacer(6, 4, 3), BlockStateProvider.of(BlockRegister.WRINKLING_OAK_LEAVES),
                    new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
                    new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build()));

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String name, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, (new Identifier(BoilingWitchMod.MOD_ID, name).toString()), new ConfiguredFeature<>(feature, config));
    }

}
