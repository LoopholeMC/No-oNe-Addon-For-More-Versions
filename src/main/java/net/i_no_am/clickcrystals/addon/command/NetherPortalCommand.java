package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.i_no_am.clickcrystals.addon.utils.BlockUtils;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;

public class NetherPortalCommand extends Command {

    public NetherPortalCommand() {
        super("findportal", "Finds Nether portals nearby", ",findportal");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            BlockPos portalPos = BlockUtils.findBlockPos(Blocks.NETHER_PORTAL, mc.options.getSimulationDistance().getValue() * 16);
            if (portalPos == null)
                info("No Nether portals are nearby.");
            else
                info("Found Nether portal at: X: %d, Y: %d, Z: %d".formatted(portalPos.getX(), portalPos.getY(), portalPos.getZ()));
            return SINGLE_SUCCESS;
        });
    }
}
