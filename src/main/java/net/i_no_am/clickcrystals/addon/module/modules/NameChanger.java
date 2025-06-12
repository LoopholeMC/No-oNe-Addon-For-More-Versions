package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.module.AddonModule;

public class NameChanger extends AddonModule {
    public NameChanger() {
        super("name-changer", "change your player name (client side)");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<String> fakeName = scGeneral.add(createStringSetting()
            .name("player-name")
            .description("A name that your real name will be replaced with")
            .def("Steve1")
            .build()
    );
// TODO FIX OR REMOVE!
    public String getUserName() {
        if (!this.isEnabled() || fakeName.getVal().isEmpty()) return null;
        return fakeName.getVal();
    }
}
