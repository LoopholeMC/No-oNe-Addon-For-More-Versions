package net.i_no_am.clickcrystals.addon.listener;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.events.listeners.TickEventListener;
import net.i_no_am.clickcrystals.addon.interfaces.OverlayReloadListener;

public class AddonListener extends TickEventListener {
    @Override
    @EventHandler
    public void onTickEnd(ClientTickEndEvent e) {
        OverlayReloadListener.callEvent();
    }
}