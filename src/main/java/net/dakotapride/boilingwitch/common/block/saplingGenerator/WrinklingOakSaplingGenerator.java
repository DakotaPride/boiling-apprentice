package net.dakotapride.boilingwitch.common.block.saplingGenerator;

import net.dakotapride.boilingwitch.common.register.content.feature.ConfiguredFeatureRegister;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class WrinklingOakSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ConfiguredFeatureRegister.WRINKLING_OAK_TREE;
    }
}
