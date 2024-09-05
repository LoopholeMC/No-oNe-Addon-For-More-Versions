package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.Categories;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;

public class CapeDisabler extends DummyModule {
    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Boolean> disableStaffNotifications = scGeneral.add(createBoolSetting()
            .name("disable-staff-notifications")
            .description("Disable the staff message when joining the world.")
            .def(true)
            .build()
    );
    public CapeDisabler(){
        super("cape-disabler", Categories.CLIENT,"Disable cc capes.");
    }
}
