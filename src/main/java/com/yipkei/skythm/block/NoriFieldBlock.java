package com.yipkei.skythm.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class NoriFieldBlock extends PillarBlock implements Waterloggable {
    public static final MapCodec<NoriFieldBlock> CODEC = createCodec(NoriFieldBlock::new);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final Map<Direction.Axis, VoxelShape> SHAPES_BY_AIXS = VoxelShapes.createAxisShapeMap(Block.createCuboidShape(3.0,3.0,16.0));

    @Override
    public MapCodec<NoriFieldBlock> getCodec() {
        return CODEC;
    }

    public NoriFieldBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, true).with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES_BY_AIXS.get(state.get(AXIS));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random) {
        if (state.get(WATERLOGGED)) {
           tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(stack.isOf(Items.BONE_MEAL)) {
            stack.decrement(1);
            convertConnectedChainBlocks(world, pos, state, 2);
            return ActionResult.SUCCESS;
        }   else {
            world.setBlockState(pos,Blocks.CHAIN.getDefaultState().with(AXIS, state.get(AXIS)).with(WATERLOGGED, true),Block.NOTIFY_ALL);

            return ActionResult.SUCCESS;
        }
    };


    private void convertConnectedChainBlocks(World world, BlockPos pos, BlockState state, int distance) {
        Direction.Axis axis = state.get(AXIS);
        Direction positiveDir = Direction.from(axis, Direction.AxisDirection.POSITIVE);
        Direction negativeDir = Direction.from(axis, Direction.AxisDirection.NEGATIVE);
        // 向正方向检测
        convertChainBlocksInDirection(world, pos, state, positiveDir, distance);
        // 向负方向检测
        convertChainBlocksInDirection(world, pos, state, negativeDir, distance);
    }

    private void convertChainBlocksInDirection(World world, BlockPos currentPos, BlockState sourceState, Direction direction, int distance) {
        BlockPos checkPos = currentPos;
        for (int i = 0; i < distance; i++) {
            checkPos = checkPos.offset(direction);
            BlockState nextState = world.getBlockState(checkPos);
            if (nextState.isOf(Blocks.CHAIN)) {
                Direction.Axis nextAxis = nextState.get(AXIS);
                if (nextAxis == sourceState.get(AXIS)){
                    world.setBlockState(checkPos, this.getDefaultState().with(AXIS, sourceState.get(AXIS)));
                } else return;
            } else return;
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(10) == 0) { // 10%概率扩散
            Direction.Axis axis = state.get(AXIS);
            Direction facing = Direction.from(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);
            BlockPos targetPos = pos.offset(facing);
            // 如果连接的方块是铁链且方向相同，则扩散
            if (world.getBlockState(targetPos).isOf(Blocks.CHAIN) &&
                world.getBlockState(targetPos).get(AXIS) == axis) {
                convertChainBlocksInDirection(world, targetPos, state, facing, 1);
            }
        }
    }

}
