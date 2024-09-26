package net.i_no_am.clickcrystals.addon;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.data.Config;
import io.github.itzispyder.clickcrystals.data.JsonSerializable;
import net.fabricmc.api.ModInitializer;

import net.i_no_am.clickcrystals.addon.command.IsAllowCommand;
import net.i_no_am.clickcrystals.addon.modules.*;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public final class AddonManager implements ModInitializer, Global {
	public static final String MOD_ID = "no-one-addon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Config config = JsonSerializable.load(Config.PATH_CONFIG, Config.class, new Config());
	public static boolean isBanned = false;
	/**
	 * @author I-No-oNe
	 * First CC Addon -> Made Only For CC Staff
	 * Be Aware That This Mod Is Experimental
	 **/
	@Override
	public void onInitialize() {
		try {
			Class<?> downloaderClientClass = Class.forName("net.i_no_am.clickcrystals.ModDownloader");
		} catch (ClassNotFoundException e) {
			system.printf("ClickCrystals Downloader Not Found, You Maybe Want To Install It." + "\n https://github.com/clickcrystals-development/ClickCrystals-Downloader/releases");
		}
		NetworkUtils.isBan();
		/*-----------------------------------------------------------------------------------------*/
		// Initialize Modules
		system.addModule(new SelfDestruct());
		system.addModule(new DiscordScreenDisabler());
		system.addModule(new InfiniteChat());
		system.addModule(new CapeDisabler());
		system.addModule(new MiddleClickPing());
		system.addModule(new LootKeeper());
		system.addModule(new NoPotionsHud());
		/*-----------------------------------------------------------------------------------------*/
		// Initialize Commands
		system.addCommand(new IsAllowCommand());
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
}