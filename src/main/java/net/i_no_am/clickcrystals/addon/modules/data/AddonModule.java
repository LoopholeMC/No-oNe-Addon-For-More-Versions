package net.i_no_am.clickcrystals.addon.modules.data;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.i_no_am.clickcrystals.addon.client.Manager;

public class AddonModule extends DummyModule {
    protected final String MOD_ID;
    protected final boolean isDev;

    public AddonModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
        this.MOD_ID = Manager.MOD_ID;
        this.isDev = Manager.isDev();
    }
//    TODO REMOVE
    public static boolean isDev() {
        AddonModule m = Module.get(AddonModule.class);
        return m.isDev;
    }
}
