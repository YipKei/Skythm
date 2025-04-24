package com.yipkei.skythm.block;

import com.mojang.serialization.MapCodec;
import com.yipkei.skythm.init.ModBlocks;
import com.yipkei.skythm.init.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class SteelTrussBlock extends Block implements Waterloggable {
    public static final MapCodec<SteelTrussBlock> CODEC = createCodec(SteelTrussBlock::new);
    private static final int DELAY = 1;
    private static final VoxelShape NORMAL_OUTLINE_SHAPE = VoxelShapes.union(Block.createColumnShape(16.0,14.0,16.0),VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(0.0,0.0,0.0,2.0,16.0,2.0))
            .values()
            .stream()
            .reduce(VoxelShapes.empty(), VoxelShapes::union)
    );

    private static final VoxelShape COLLISION_SHAPE = Block.createColumnShape(16.0, 0.0, 2.0);
    private static final VoxelShape BOTTOM_OUTLINE_SHAPE = VoxelShapes.union(
            NORMAL_OUTLINE_SHAPE,
            COLLISION_SHAPE,
            (VoxelShape)VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(16.0, 0.0, 2.0, 0.0, 2.0))
                    .values()
                    .stream()
                    .reduce(VoxelShapes.empty(), VoxelShapes::union)
    );
    private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.fullCube().offset(0.0, -1.0, 0.0).simplify();
    public static final int MAX_DISTANCE = 15;
    public static final IntProperty DISTANCE = IntProperty.of("distance", 0, MAX_DISTANCE);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    @Override
    public MapCodec<SteelTrussBlock> getCodec() {return CODEC;}

    public SteelTrussBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DISTANCE, MAX_DISTANCE).with(WATERLOGGED, false).with(BOTTOM, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, WATERLOGGED, BOTTOM);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!context.isHolding(state.getBlock().asItem())) {
            return state.get(BOTTOM) ? BOTTOM_OUTLINE_SHAPE : NORMAL_OUTLINE_SHAPE;
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().isOf(this.asItem());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        int i = calculateDistance(world, blockPos);
        return this.getDefaultState()
                .with(WATERLOGGED, world.getFluidState(blockPos).getFluid() == Fluids.WATER)
                .with(DISTANCE, i)
                .with(BOTTOM, this.shouldBeBottom(world, blockPos, i));
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, DELAY);
        }
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
            Random random
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (!world.isClient()) {
            tickView.scheduleBlockTick(pos, this, DELAY);
        }
        return state;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = calculateDistance(world, pos);
        BlockState blockState = state.with(DISTANCE, i).with(BOTTOM, this.shouldBeBottom(world, pos, i));
        if ((Integer)blockState.get(DISTANCE) == MAX_DISTANCE) {
            if ((Integer)state.get(DISTANCE) == MAX_DISTANCE) {
                FallingBlockEntity.spawnFromBlock(world, pos, blockState);
            } else {
                world.breakBlock(pos, true);
            }
        } else if (state != blockState) {
            world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return calculateDistance(world, pos) < MAX_DISTANCE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isPlacement()) {
            return VoxelShapes.empty();
        } else if (context.isAbove(VoxelShapes.fullCube(), pos, true) && !context.isDescending()) {
            return NORMAL_OUTLINE_SHAPE;
        } else {
            return state.get(DISTANCE) != 0 && state.get(BOTTOM) && context.isAbove(OUTLINE_SHAPE, pos, true) ? COLLISION_SHAPE : VoxelShapes.empty();
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    private boolean shouldBeBottom(BlockView world, BlockPos pos, int distance) {
        return distance > 0 && !world.getBlockState(pos.down()).isOf(this);
    }

    public static int calculateDistance(BlockView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy().move(Direction.DOWN);
        BlockState blockState = world.getBlockState(mutable);
        int i = MAX_DISTANCE;
        if (blockState.isOf(ModBlocks.STEEL_TRUSS)) {                                   //如果下方是桁架，继承距离
            i = (Integer)blockState.get(DISTANCE);
        } else if (blockState.isSideSolidFullSquare(world, mutable, Direction.UP)) {    //如果下表面完整，距离设置为0
            return 0;
        }

        for (Direction direction : Direction.Type.HORIZONTAL) {                         //遍历水平连接方块
            BlockState blockState2 = world.getBlockState(mutable.set(pos, direction));  //记录水平连接的方块状态
            if (blockState2.isIn(ModTags.Blocks.FERROCONCRETE)) {                       //如果直接连接到钢筋混凝土上，则返回1
                return 1;
            }
            if (blockState2.isOf(ModBlocks.STEEL_TRUSS)) {                              //如果水平方向是桁架
                i = Math.min(i, (Integer)blockState2.get(DISTANCE) + 1);                //距离为水平方向桁架距离+1和15的最小值
                if (i == 1) {                                                           //如果已经是最小值1就不用算了
                    break;
                }
            }
        }

        return i;
    }
}
