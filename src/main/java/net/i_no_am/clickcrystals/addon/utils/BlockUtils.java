package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class BlockUtils implements Global {

    public static boolean isLookingAt(Block block) {
        if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) mc.crosshairTarget;
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = PlayerUtils.getWorld().getBlockState(blockPos);
            return blockState.getBlock() == block;
        }
        return false;
    }

    public static boolean anchorWithOneCharge() {
        if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.BLOCK && HotbarUtils.isHoldingEitherHand(Items.GLOWSTONE)) {
            BlockHitResult blockHitResult = (BlockHitResult) mc.crosshairTarget;
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = PlayerUtils.getWorld().getBlockState(blockPos);
            if (blockState.getBlock() == Blocks.RESPAWN_ANCHOR) {
                int charges = blockState.get(RespawnAnchorBlock.CHARGES);
                return charges == 1;
            }
        }
        return false;
    }
}