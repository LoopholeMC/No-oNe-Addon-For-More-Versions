package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class NoPotionsHud extends DummyModule {
    public NoPotionsHud(){
        super("no-potions-render", AddonCategory.ADDON,"disable the potions/effect hud.");
    }
}
