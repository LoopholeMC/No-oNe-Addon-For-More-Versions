package net.i_no_am.clickcrystals.addon;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.data.Config;
import io.github.itzispyder.clickcrystals.data.JsonSerializable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.i_no_am.clickcrystals.addon.client.AddonCapeManager;
import net.i_no_am.clickcrystals.addon.listener.AddonListener;
import net.i_no_am.clickcrystals.addon.command.*;
import net.i_no_am.clickcrystals.addon.modules.*;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * TODO: UPDATE VERSION_NUMBER to the latest version
 * TODO: UPDATE SITE (<a href="https://github.com/I-No-oNe/i-no-one.github.io/edit/main/addon/version.html">...</a>)
 */

@SuppressWarnings("unused")
public final class AddonManager implements ModInitializer, Global {
    public static final String MOD_ID = "no-one-addon";
    public static final String VERSION = "0.8";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Config config = JsonSerializable.load(Config.PATH_CONFIG, Config.class, new Config());
    public static boolean isBanned = true, isNoOne = false;
    public static AddonCapeManager cape = new AddonCapeManager();
    /**
     * @author I-No-oNe
     * Be Aware That This Mod Is Experimental
     **/

    @Override
    public void onInitialize() {
        /*-----------------------------------------------------------------------------------------*/
        // Initialize Listeners
        system.addListener(new AddonListener());
        /*-----------------------------------------------------------------------------------------*/
        // Initialize Modules
        system.addModule(new DiscordScreenDisabler());
        system.addModule(new InfiniteChat());
        system.addModule(new CapeDisabler());
        system.addModule(new MiddleClickPing());
        system.addModule(new LootKeeper());
        system.addModule(new NoPotionsHud());
        system.addModule(new HitColor());
        system.addModule(new ModMenuDisabler());
        system.addModule(new UpsideDown());
        system.addModule(new Prevent());
        system.addModule(new AntiOffHand());
        system.addModule(new GhostInteractions());
        system.addModule(new SafeWalk());
        system.addModule(new NameChanger());
        /*-----------------------------------------------------------------------------------------*/
        // Initialize Commands
        system.addCommand(new CleanCommand());
        system.addCommand(new QuitCommand());
        system.addCommand(new NetherPortalCommand());
        system.addCommand(new FreeRAMCommand());
        /*-----------------------------------------------------------------------------------------*/
        // Loading Configs and other checks (Because We disable The Regular CC Config Loading)
        system.println("-> loading config...");
        config.loadEntireConfig();
        system.println("-> loading profiles...");
        system.profiles.init();
        system.printf("-> checking if %s can use the addon...", mc.getSession().getUsername());
        system.printf("<- Profile set '%s'", system.profiles.profileConfig.getCurrentProfileName());
        system.printf("<- allowed to use the addon: %s", !isBanned);
        system.printf("Finish Loading No one's Addon!");
    }

    public static boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment() && isNoOne;
    }
}