package net.i_no_am.clickcrystals.addon.modules.data;

import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class AddonModule extends DummyModule {
    public AddonModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
    }
    protected String MOD_ID = AddonManager.MOD_ID;
}

