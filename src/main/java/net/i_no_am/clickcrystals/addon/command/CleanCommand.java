package net.i_no_am.clickcrystals.addon.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.itzispyder.clickcrystals.commands.Command;
import net.minecraft.command.CommandSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CleanCommand extends Command {

    private static final String LOGS_FOLDER = "logs";

    public CleanCommand() {
        super("clean", "Clean specific lines in chat or logs", "log-cleaner");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(LiteralArgumentBuilder.<CommandSource>literal("chat")
                .executes(context -> {
                    mc.inGameHud.getChatHud().clear(true);
                    return SINGLE_SUCCESS;
                })
        ).then(LiteralArgumentBuilder.<CommandSource>literal("logs")
                .then(argument("content", StringArgumentType.string())
                        .then(argument("fileName", StringArgumentType.string())
                                .suggests((context, suggestionsBuilder) -> {
                                    try {
                                        List<Path> logFiles = Files.list(Paths.get(LOGS_FOLDER))
                                                .filter(Files::isRegularFile)
                                                .filter(file -> !file.getFileName().toString().endsWith(".gz"))
                                                .toList();
                                        for (Path file : logFiles) {
                                            suggestionsBuilder.suggest(file.getFileName().toString());
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return suggestionsBuilder.buildFuture();
                                }).executes(context -> {
                                    String content = StringArgumentType.getString(context, "content");
                                    String fileName = StringArgumentType.getString(context, "fileName");
                                    return cleanLogFile(content, fileName);
                                })
                        ).executes(context -> {
                            String content = StringArgumentType.getString(context, "content");
                            return cleanAllLogFiles(content);
                        })
                )
        );
    }

    private int cleanLogFile(String lineToRemove, String fileName) {
        Path logFilePath = Paths.get(LOGS_FOLDER, fileName);

        if (!Files.exists(logFilePath)) {
            info("File not found: " + fileName);
            return COMMAND_PASS;
        }

        try {
            List<String> lines = Files.readAllLines(logFilePath);
            List<String> filteredLines = lines.stream()
                    .filter(line -> !line.contains(lineToRemove))
                    .collect(Collectors.toList());
            int deletedLinesCount = lines.size() - filteredLines.size();

            Files.write(logFilePath, filteredLines);
            info("Removed " + deletedLinesCount + " lines containing \"" + lineToRemove + "\" from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            info("An error occurred while cleaning the log file.");
        }
        return SINGLE_SUCCESS;
    }

    private int cleanAllLogFiles(String lineToRemove) {
        try {
            List<Path> logFiles = Files.list(Paths.get(LOGS_FOLDER))
                    .filter(Files::isRegularFile)
                    .filter(file -> !file.getFileName().toString().endsWith(".gz"))
                    .toList();

            int totalDeletedLines = 0;

            for (Path logFile : logFiles) {
                List<String> lines = Files.readAllLines(logFile);
                List<String> filteredLines = lines.stream()
                        .filter(line -> !line.contains(lineToRemove))
                        .collect(Collectors.toList());

                int deletedLinesCount = lines.size() - filteredLines.size();
                totalDeletedLines += deletedLinesCount;

                Files.write(logFile, filteredLines);
                info("Removed " + deletedLinesCount + " lines containing \"" + lineToRemove + "\" from " + logFile.getFileName());
            }
            info("Total lines removed across all files: " + totalDeletedLines);
        } catch (IOException e) {
            e.printStackTrace();
            info("An error occurred while cleaning the log files.");
        }
        return SINGLE_SUCCESS;
    }
}