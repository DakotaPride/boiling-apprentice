package net.dakotapride.boilingwitch.common.block.tree;

import net.dakotapride.boilingwitch.common.block.saplingGenerator.PalistromSaplingGenerator;

public class PalistromSaplingBlock extends SeedBlock {
    public PalistromSaplingBlock(Settings settings) {
        super(new PalistromSaplingGenerator(), settings);
    }
}
