package net.i_no_am.clickcrystals.addon.client;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.commands.Command;
import io.github.itzispyder.clickcrystals.modules.Module;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;
import net.i_no_am.clickcrystals.addon.modules.data.AddonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public abstract class Manager implements Global {

    // Constants
    public static final String MOD_ID = "no-one-addon";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static boolean isNoOne = false;

    // Managers
    public static final String addonVersion = AddonVersion.getVersion();

    public static List<Command> getAddonCommands() {
        return ModuleManager.getAddonCommands();
    }

    public static List<Module> getAddonModules() {
        return ModuleManager.getAddonModule();
    }


    public static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment() && isNoOne;
    }

    private static final class AddonVersion {
        public static String getVersion() {
            ModContainer mod = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();
            String friendlyVersion = mod.getMetadata().getVersion().getFriendlyString();
            Pattern pattern = Pattern.compile("(\\d+\\.\\d+)(?=-fabric|$)");
            Matcher matcher = pattern.matcher(friendlyVersion);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        }
    }


        private static final class ModuleManager {
            private ModuleManager() {
            }

            public static List<Command> getAddonCommands() {
                return new ArrayList<>(system.commands().values().stream()
                        .filter(command -> command.getClass().getPackage().getName().startsWith("net.i_no_am.clickcrystals.addon.command"))
                        .toList());
            }

            public static List<Module> getAddonModule() {
                return new ArrayList<>(system.collectModules().stream()
                        .filter(module -> module instanceof AddonLModule || module instanceof AddonModule)
                        .toList());
            }
        }
    }
