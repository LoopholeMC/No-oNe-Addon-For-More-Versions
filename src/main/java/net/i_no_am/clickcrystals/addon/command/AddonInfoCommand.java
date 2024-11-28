package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.i_no_am.clickcrystals.addon.client.Manager;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;
import net.minecraft.command.CommandSource;

import java.util.stream.Collectors;

public class AddonInfoCommand extends Command {
    public AddonInfoCommand() {
        super("addon-info", "Copy the add-on modules' & commands' descriptions and names into a chart", ",get addon modules and command info");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> literalArgumentBuilder) {
        literalArgumentBuilder.executes(context -> {
            String moduleChart = Manager.getAddonModules().stream()
                    .map(module -> String.format("| %s | %s |",
                            module.getName(),
                            module.getDescription() != null ? module.getDescription() : "No description"))
                    .collect(Collectors.joining("\n"));

            // Filter commands by package name
            String commandChart = Manager.getAddonCommands().stream()
                    .filter(command -> command.getClass().getPackage().getName().startsWith("net.i_no_am.clickcrystals.addon.command"))
                    .map(command -> String.format("| %s | %s |",
                            command.getName(),
                            command.getDescription() != null ? command.getDescription() : "No description"))
                    .collect(Collectors.joining("\n"));

            String chart = """
                    ## Addon Modules and Commands
                    
                    ### Modules:
                    | Name          | Description       |
                    |---------------|-------------------|
                    %s
                    
                    ### Commands:
                    | Name          | Description       |
                    |---------------|-------------------|
                    %s
                    """.formatted(moduleChart, commandChart);

            OsUtils.copy(chart);

            info("Addon modules and commands chart has been copied to your clipboard!");
            return SINGLE_SUCCESS;
        });
    }
}