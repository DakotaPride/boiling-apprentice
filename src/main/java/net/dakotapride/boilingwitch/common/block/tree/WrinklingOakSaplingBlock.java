package net.dakotapride.boilingwitch.common.block.tree;

import net.dakotapride.boilingwitch.common.block.saplingGenerator.PalistromSaplingGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WrinklingOakSaplingBlock extends SaplingBlock {
    public WrinklingOakSaplingBlock(Settings settings) {
        super(new PalistromSaplingGenerator(), settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3.5, 0, 3.5, 12.5, 3, 12.5);
    }
}
