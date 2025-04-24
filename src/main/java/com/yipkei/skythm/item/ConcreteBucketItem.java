package com.yipkei.skythm.item;

import com.yipkei.skythm.init.ModBlocks;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ConcreteBucketItem extends BlockItem implements FluidModificationItem {
    private final SoundEvent placeSound;
    private final Block block2;

    public ConcreteBucketItem(Block block, Block block2, SoundEvent placeSound, Item.Settings settings){
        super(block, settings);
        this.placeSound = placeSound;
        this.block2 = block2;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(ModBlocks.STEEL_TRUSS)) {
            world.setBlockState(blockPos, block2.getDefaultState(),Block.NOTIFY_ALL_AND_REDRAW);
            return ActionResult.SUCCESS;
        }
        ActionResult actionResult = super.useOnBlock(context);
        PlayerEntity playerEntity = context.getPlayer();
        if (actionResult.isAccepted() && playerEntity != null) {
            playerEntity.setStackInHand(context.getHand(), BucketItem.getEmptiedStack(context.getStack(), playerEntity));
        }
        return actionResult;
    }

    @Override
    protected SoundEvent getPlaceSound(BlockState state) {return this.placeSound;}

    @Override
    public boolean placeFluid(@Nullable LivingEntity user, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
        if (world.isInBuildLimit(pos) && world.isAir(pos)) {
            if (!world.isClient) {
                world.setBlockState(pos, this.getBlock().getDefaultState(), Block.NOTIFY_ALL);
            }

            world.emitGameEvent(user, GameEvent.FLUID_PLACE, pos);
            world.playSound(user, pos, this.placeSound, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }
}
