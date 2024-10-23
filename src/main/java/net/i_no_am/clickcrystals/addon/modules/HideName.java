package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.modules.data.AddonModule;

public class HideName extends AddonModule {
    public HideName(){
        super("hide-name","hide your name and replace it with something other.");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<String> fakeName = scGeneral.add(createStringSetting()
            .name("fake-player-name")
            .description("A name that your real name will be replaced with")
            .def("Steve69")
            .build()
    );
}
