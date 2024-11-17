package net.i_no_am.clickcrystals.addon.modules.data;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class AddonLModule extends ListenerModule {
    protected final String MOD_ID;
    protected final boolean isDev;

    public AddonLModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
        this.MOD_ID = AddonManager.MOD_ID;
        this.isDev = AddonManager.isDev();
    }
    public static boolean isDev() {
        AddonModule m = Module.get(AddonModule.class);
        return m.isDev;
    }
}
