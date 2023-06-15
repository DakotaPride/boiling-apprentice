package net.dakotapride.boilingwitch.common.block.tree;

import net.dakotapride.boilingwitch.common.block.saplingGenerator.WrinklingOakSaplingGenerator;

public class WrinklingOakSaplingBlock extends SeedBlock {
    public WrinklingOakSaplingBlock(Settings settings) {
        super(new WrinklingOakSaplingGenerator(), settings);
    }
}
