package net.dakotapride.boilingwitch.common.block;

import net.dakotapride.boilingwitch.common.block.entity.GlyphEnhancementTableEntity;
import net.dakotapride.boilingwitch.common.register.content.BlockEntityRegister;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class GlyphEnhancementTableBlock extends BlockWithEntity implements BlockEntityProvider {
    public GlyphEnhancementTableBlock(Settings settings) {
        super(settings);
    }

    VoxelShape shape = VoxelShapes.combineAndSimplify(Block.createCuboidShape(0, 9, 0, 16, 12, 16),
            Stream.of(
                    Block.createCuboidShape(0, 0, 13, 3, 9, 16),
                    Block.createCuboidShape(0, 0, 0, 3, 9, 3),
                    Block.createCuboidShape(13, 0, 13, 16, 9, 16),
                    Block.createCuboidShape(13, 0, 0, 16, 9, 3),
            Stream.of(
                    Block.createCuboidShape(0.5, 5, 3, 2.5, 7, 13),
                    Block.createCuboidShape(3, 7, 14, 13, 9, 15),
                    Block.createCuboidShape(3, 5, 0.5, 13, 7, 2.5),
                    Block.createCuboidShape(3, 7, 1, 13, 9, 2),
                    Block.createCuboidShape(3, 5, 13.5, 13, 7, 15.5),
                    Block.createCuboidShape(14, 7, 3, 15, 9, 13),
                    Block.createCuboidShape(13.5, 5, 3, 15.5, 7, 13),
                    Block.createCuboidShape(1, 7, 3, 2, 9, 13)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get()).reduce((v1, v2) ->
            VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get(), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GlyphEnhancementTableEntity) {
                ItemScatterer.spawn(world, pos, (GlyphEnhancementTableEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GlyphEnhancementTableEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityRegister.GLYPH_ENHANCEMENT_ENTITY, GlyphEnhancementTableEntity::tick);
    }
}