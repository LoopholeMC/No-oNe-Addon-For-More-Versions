package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.util.Formatting;

public class HwidCommand extends Command {
    public HwidCommand(){
        super("hwid","show you your hwid",",hwid");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> literalArgumentBuilder) {
        info( "Your HWID is: "+ Formatting.GOLD + OsUtils.getHWID());
        OsUtils.copy(OsUtils.getHWID());
    }
}
