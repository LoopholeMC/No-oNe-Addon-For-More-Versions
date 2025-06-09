package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class BlockUtils implements Global {

    public static BlockPos findBlockPos(Block block, int range) {
        for (BlockPos pos : getBlocksInRange(range)) {
            if (mc.world.getBlockState(pos).getBlock() == block) return pos;
        }
        return new BlockPos(Vec3i.ZERO);
    }

    private static BlockPos[] getBlocksInRange(int range) {
        if (range < 0) throw new IllegalArgumentException("Range cannot be negative");
        BlockPos playerPos = mc.player.getBlockPos();
        BlockPos[] blocks = new BlockPos[(range * 2 + 1) * (range * 2 + 1) * (range * 2 + 1)];
        int index = 0;
        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    blocks[index++] = playerPos.add(x, y, z);
                }
            }
        }
        return blocks;
    }


    public static boolean isLookingAt(Block block) {
        if (mc.crosshairTarget instanceof BlockHitResult hitResult) {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = mc.world.getBlockState(pos);
            return state.isOf(block);
        }
        return false;
    }

    public static boolean isAnchorLoaded(int charges, BlockPos pos) {
        var state = mc.player.getWorld().getBlockState(pos);
        if (state.getBlock().equals(Blocks.RESPAWN_ANCHOR)) return state.get(RespawnAnchorBlock.CHARGES) == charges;
        return isAnchorLoaded(charges, pos);
    }
}