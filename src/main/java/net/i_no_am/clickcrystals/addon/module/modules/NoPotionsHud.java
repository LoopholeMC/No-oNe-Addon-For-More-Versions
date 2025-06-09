package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import net.i_no_am.clickcrystals.addon.listener.events.mc.EffectsOverlayInitEvent;
import net.i_no_am.clickcrystals.addon.module.AddonListenerModule;

public class NoPotionsHud extends AddonListenerModule {
    public NoPotionsHud() {
        super("no-potions-render", "disable the potions/effect hud.");
    }

    @EventHandler
    private void effectGui(EffectsOverlayInitEvent e) {
        if (isEnabled()) e.cancel();
    }
}
