package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class SafeWalk extends DummyModule {
    public SafeWalk(){
        super("safe-walk", AddonCategory.ADDON,"crouch when the player in the edge of a block");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Boolean> itemCheck = scGeneral.add(createBoolSetting()
            .name("check-item-name")
            .description("If holding the requited item the module will work.")
            .def(true)
            .build()
    );
    public final ModuleSetting<String> itemNames = scGeneral.add(createStringSetting()
            .name("items-to-check")
            .description("Write down here the names of the items")
            .def("")
            .build()
    );
}
