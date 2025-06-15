package net.i_no_am.clickcrystals.addon.listener;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.events.listeners.TickEventListener;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.Manager;
import net.i_no_am.clickcrystals.addon.interfaces.OverlayReloadListener;
import net.i_no_am.clickcrystals.addon.listener.events.mc.TitleScreenInitEvent;
import net.i_no_am.clickcrystals.addon.screen.AddonScreen;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;

import java.util.concurrent.CompletableFuture;

public class AddonListener extends TickEventListener {

    @EventHandler
    public void onTick(ClientTickEndEvent e) {
        OverlayReloadListener.callEvent();
    }

    @EventHandler
    public void onScreenInit(TitleScreenInitEvent e) {
        System.out.println(Manager.banData.getBan());

        CompletableFuture.runAsync(() -> {

            boolean banned = Manager.banData.getBan().shouldDisplay();

            mc.execute(() -> {
                AddonManager.isBanned = banned;
                if (banned && !(mc.currentScreen instanceof AddonScreen)) {
                    mc.setScreen(new AddonScreen());
                    OsUtils.copy(OsUtils.getHWID());
                } else
                    Manager.version.notifyUpdate();
            });
        });
    }

}