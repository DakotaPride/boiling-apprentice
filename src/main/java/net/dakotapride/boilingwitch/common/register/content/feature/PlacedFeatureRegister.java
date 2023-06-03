package net.dakotapride.boilingwitch.common.register.content.feature;

import com.google.common.collect.ImmutableList;
import net.dakotapride.boilingwitch.common.BoilingWitchMod;
import net.dakotapride.boilingwitch.common.register.content.BlockRegister;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

import static net.dakotapride.boilingwitch.common.register.content.feature.ConfiguredFeatureRegister.register;

public class PlacedFeatureRegister {

    public static final RegistryEntry<PlacedFeature> FOOLS_BLOOD_PATCH = createPlacedFeature("fools_blood",
            ConfiguredFeatureRegister.ORE_FOOLS_BLOOD, modifiersWithRarity(6,
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    // Palistrom Trees
    private static final RegistryEntry<PlacedFeature> PALISTROM_TREE_PLACED = createPlacedFeatureWithoutBiomeFilter("palistrom_tree",
            ConfiguredFeatureRegister.PALISTROM_TREE);

    public static final RegistryEntry<PlacedFeature> PALISTROM_CHECKED = createPlacedFeature("palistrom_seeds_checker",
            ConfiguredFeatureRegister.PALISTROM_TREE, PlacedFeatures.wouldSurvive(BlockRegister.PALISTROM_SEEDS));

    private static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PALISTROM_TREE_CONFIGURED = register("palistrom_trees",
            Feature.RANDOM_SELECTOR, new RandomFeatureConfig(ImmutableList.of(new RandomFeatureEntry(PALISTROM_TREE_PLACED, 1.0F)), PALISTROM_CHECKED));

    public static final RegistryEntry<PlacedFeature> PALISTROM_TREES =
            createPlacedFeature("palistrom_trees", PALISTROM_TREE_CONFIGURED,
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 0),
                    SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT)));

    public static final RegistryEntry<PlacedFeature> RESTING_NETTLE_PLACED =
            createPlacedFeature("resting_nettle_placed", ConfiguredFeatureRegister.RESTING_NETTLE_PATCH, RarityFilterPlacementModifier.of(2),
                    SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // Wrinkling Oak Trees
    private static final RegistryEntry<PlacedFeature> WRINKLING_OAK_TREE_PLACED = createPlacedFeatureWithoutBiomeFilter("wrinkling_tree",
            ConfiguredFeatureRegister.WRINKLING_OAK_TREE);

    public static final RegistryEntry<PlacedFeature> WRINKLING_OAK_CHECKED = createPlacedFeature("wrinkling_sapling_checker",
            ConfiguredFeatureRegister.WRINKLING_OAK_TREE, PlacedFeatures.wouldSurvive(BlockRegister.WRINKLING_OAK_SAPLING));

    private static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> WRINKLING_OAK_TREE_CONFIGURED = register("wrinkling_oak_trees",
            Feature.RANDOM_SELECTOR, new RandomFeatureConfig(ImmutableList.of(new RandomFeatureEntry(WRINKLING_OAK_TREE_PLACED, 1.0F)), WRINKLING_OAK_CHECKED));

    public static final RegistryEntry<PlacedFeature> WRINKLING_OAK_TREES =
            createPlacedFeature("wrinkling_oak_trees", WRINKLING_OAK_TREE_CONFIGURED,
                    PlacedFeatures.createCountExtraModifier(10, 0.1f, 1),
                    SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT)));

    public static final RegistryEntry<PlacedFeature> RARE_WRINKLING_OAK_TREES =
            createPlacedFeature("rare_wrinkling_oak_trees", WRINKLING_OAK_TREE_CONFIGURED,
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 0),
                    SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT)));


    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
        List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
        list.add(BiomePlacementModifier.of());
        return createPlacedFeature(id, feature, list);
    }

    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeatureWithoutBiomeFilter(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
        List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
        return createPlacedFeature(id, feature, list);
    }

    public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, List<PlacementModifier> placementModifiers) {
        Identifier realID = new Identifier(BoilingWitchMod.MOD_ID, id);
        if (BuiltinRegistries.PLACED_FEATURE.getIds().contains(realID))
            throw new IllegalStateException("Placed Feature ID: \"" + realID + "\" already exists in the Placed Features registry!");

        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, realID, new PlacedFeature(RegistryEntry.upcast(feature), List.copyOf(placementModifiers)));
    }

    public static void init() {}

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

}
