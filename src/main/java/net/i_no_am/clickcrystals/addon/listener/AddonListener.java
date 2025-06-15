package net.i_no_am.clickcrystals.addon.listener;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.events.listeners.TickEventListener;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.Manager;
import net.i_no_am.clickcrystals.addon.client.data.Constants;
import net.i_no_am.clickcrystals.addon.client.data.ban.BanState;
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

        CompletableFuture.runAsync(() -> {
            var banState = Manager.banData.getBan();

            boolean banned = banState.shouldDisplay();

            Constants.INSIDER.i_no_am = (banState == BanState.I_NO_AM);

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