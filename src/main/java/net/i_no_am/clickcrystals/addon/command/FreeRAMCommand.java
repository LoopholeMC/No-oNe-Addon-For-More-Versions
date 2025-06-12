package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.minecraft.command.CommandSource;

public class FreeRAMCommand extends Command {
    public FreeRAMCommand() {
        super("free-ram", "Free the RAM of the local system", ",free-ram");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        info("Request freeing RAM.");
        System.gc();
        info("Freed RAM.");
    }
}
