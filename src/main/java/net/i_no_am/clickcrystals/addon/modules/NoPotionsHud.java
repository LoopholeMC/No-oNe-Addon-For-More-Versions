package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import net.i_no_am.clickcrystals.addon.listener.events.mc.EffectsOverlayInitEvent;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;

public class NoPotionsHud extends AddonLModule {
    public NoPotionsHud() {
        super("no-potions-render", "disable the potions/effect hud.");
    }

    @EventHandler
    private void effectGui(EffectsOverlayInitEvent e) {
        if (isEnabled()) e.cancel();
    }
}
