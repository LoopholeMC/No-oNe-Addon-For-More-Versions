package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.minecraft.command.CommandSource;

public class CrashCommand extends Command {

    public CrashCommand() {
        super("exit", "exit <error_number>", ",exit");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> literalArgumentBuilder) {
        literalArgumentBuilder
                .then(argument("num", IntegerArgumentType.integer(0))
                        .executes(this::executeCrashCommand));
    }

    private int executeCrashCommand(CommandContext<CommandSource> context) {
        int errorNumber = IntegerArgumentType.getInteger(context, "num");
        System.exit(errorNumber);
        return SINGLE_SUCCESS;
    }
}
