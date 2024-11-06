package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NetherPortalCommand extends Command {

    public NetherPortalCommand() {
        super("findportal", "Finds Nether portals nearby", ",findportal");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> literalArgumentBuilder) {
        literalArgumentBuilder.executes(context -> {
            BlockPos playerPos = PlayerUtils.player().getBlockPos();
            List<BlockPos> portalLocations = new ArrayList<>();

            int renderDistanceChunks = mc.options.getViewDistance().getValue();
            int radius = renderDistanceChunks * 16;

            Box searchArea = new Box(
                    new Vec3d(playerPos.getX() - radius, playerPos.getY() - radius, playerPos.getZ() - radius),
                    new Vec3d(playerPos.getX() + radius, playerPos.getY() + radius, playerPos.getZ() + radius)
            );

            for (int x = (int) searchArea.minX; x <= searchArea.maxX; x++) {
                for (int y = (int) searchArea.minY; y <= searchArea.maxY; y++) {
                    for (int z = (int) searchArea.minZ; z <= searchArea.maxZ; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (isNetherPortal(PlayerUtils.getWorld(), pos)) {
                            portalLocations.add(pos);
                        }
                    }
                }
            }

            if (!portalLocations.isEmpty()) {
                StringBuilder message = new StringBuilder("Found Nether portals at:\n");
                for (BlockPos portalPos : portalLocations) {
                    message.append(String.format("X: %d, Y: %d, Z: %d\n", portalPos.getX(), portalPos.getY(), portalPos.getZ()));
                }
                info(message.toString());
            } else {
                info("No Nether portals found nearby.");
            }
            return SINGLE_SUCCESS;
        });
    }

    private boolean isNetherPortal(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.NETHER_PORTAL;
    }
}
