package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.minecraft.command.CommandSource;

public class IsAllowCommand extends Command {
    public IsAllowCommand() {
        super("allow", "see if the player is allow to use the addon.", ",allow");
    }

    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes((context) -> {
            if (!AddonManager.isBanned) ChatUtils.sendPrefixMessage("YOU ARE ALLOW TO USE THIS ADDON.");
            return SINGLE_SUCCESS;
        });
    }
}