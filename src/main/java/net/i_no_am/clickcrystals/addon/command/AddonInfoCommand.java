package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.i_no_am.clickcrystals.addon.client.Manager;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;
import net.minecraft.command.CommandSource;

import java.util.stream.Collectors;

public class AddonInfoCommand extends Command {
    public AddonInfoCommand() {
        super("addon-info", "Copy the add-on module' & commands' descriptions and names into a chart", ",get addon module and command info");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> literalArgumentBuilder) {
        literalArgumentBuilder.executes(context -> {
            String moduleChart = Manager.modules.getAddonModule().stream()
                    .map(module -> String.format("| %s | %s |",
                            module.getName(),
                            module.getDescription() != null ? module.getDescription() : "No description"))
                    .collect(Collectors.joining("\n"));

            String commandChart = Manager.modules.getAddonCommands().stream()
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

            info("Addon module and commands chart has been copied to your clipboard!");
            return SINGLE_SUCCESS;
        });
    }
}