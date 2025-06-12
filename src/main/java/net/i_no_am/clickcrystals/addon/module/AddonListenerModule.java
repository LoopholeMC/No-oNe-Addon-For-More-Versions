package net.i_no_am.clickcrystals.addon.module;

import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.i_no_am.clickcrystals.addon.module.category.AddonCategory;

public class AddonListenerModule extends ListenerModule {

    public AddonListenerModule(String name, String description) {
        super(name, AddonCategory.ADDON, description);
    }
}
