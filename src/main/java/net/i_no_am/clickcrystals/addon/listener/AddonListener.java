package net.i_no_am.clickcrystals.addon.listener;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.events.listeners.TickEventListener;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.listener.events.mc.TitleScreenInitEvent;
import net.i_no_am.clickcrystals.addon.interfaces.OverlayReloadListener;
import net.i_no_am.clickcrystals.addon.screen.AddonScreen;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import java.net.URI;

public class AddonListener extends TickEventListener {

    @Override
    @EventHandler
    public void onTickEnd(ClientTickEndEvent e) {
        OverlayReloadListener.callEvent();
    }

    @EventHandler
    public void onScreenInit(TitleScreenInitEvent e) {
        NetworkUtils.isBan();
        if (AddonManager.isBanned && !AddonManager.isDev()) {
            mc.setScreen(new AddonScreen());
            OsUtils.copyHwid();
        } else if (!NetworkUtils.isUpdated() && !AddonManager.isDev()) {
            mc.setScreen(new ConfirmScreen(
                    confirm -> {
                        if (confirm)
                            Util.getOperatingSystem().open(URI.create("https://discord.com/channels/1256214501129191504/1256224383639224331"));
                        else mc.stop();
                    },
                    Text.of(Formatting.DARK_RED + "You are using an outdated version of " + Formatting.GREEN + "No one's Addon"), Text.of("Please download the latest version from " + Formatting.DARK_PURPLE + "Discord"), Text.of("Download"), Text.of("Quit Game")));
        }
    }
}