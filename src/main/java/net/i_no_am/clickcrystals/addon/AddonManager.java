package net.i_no_am.clickcrystals.addon;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.data.Config;
import io.github.itzispyder.clickcrystals.data.JsonSerializable;
import net.fabricmc.api.ClientModInitializer;
import net.i_no_am.clickcrystals.addon.client.Manager;
import net.i_no_am.clickcrystals.addon.command.*;
import net.i_no_am.clickcrystals.addon.listener.AddonListener;
import net.i_no_am.clickcrystals.addon.modules.*;

@SuppressWarnings("unused")
public final class AddonManager implements ClientModInitializer, Global {
    public static Config config = JsonSerializable.load(Config.PATH_CONFIG, Config.class, new Config());
    public static boolean isBanned = true;

    /**
     * @author I-No-oNe
     * WOW, this is actually working (;
     **/

    @Override
    public void onInitializeClient() {
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
        system.addCommand(new HwidCommand());
        system.addCommand(new AddonInfoCommand());
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
        if (Manager.isFeatherClient())
            exit();
    }
    private void exit(){
        System.exit(-1);
    }
}