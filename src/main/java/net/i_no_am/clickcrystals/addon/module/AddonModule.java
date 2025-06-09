package net.i_no_am.clickcrystals.addon.module;

import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class AddonModule extends DummyModule {

    public AddonModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
    }
}
