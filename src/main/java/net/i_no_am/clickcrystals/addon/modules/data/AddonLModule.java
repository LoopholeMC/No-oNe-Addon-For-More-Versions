package net.i_no_am.clickcrystals.addon.modules.data;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.i_no_am.clickcrystals.addon.client.Manager;

public class AddonLModule extends ListenerModule {
    protected final String MOD_ID;
    protected final boolean isDev;

    public AddonLModule(String name, String description) {
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
