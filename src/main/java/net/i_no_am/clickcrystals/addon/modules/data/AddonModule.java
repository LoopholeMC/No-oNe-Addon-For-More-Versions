package net.i_no_am.clickcrystals.addon.modules.data;

import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class AddonModule extends DummyModule {
    protected final String MOD_ID;

    public AddonModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
        this.MOD_ID = AddonManager.MOD_ID;
    }
}
