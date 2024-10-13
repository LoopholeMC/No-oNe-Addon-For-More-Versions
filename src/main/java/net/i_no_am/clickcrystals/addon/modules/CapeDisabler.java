package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.modules.data.AddonModule;

public class CapeDisabler extends AddonModule {
    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Boolean> disableStaffNotifications = scGeneral.add(createBoolSetting()
            .name("disable-staff-notifications")
            .description("Disable the staff message when joining the world.")
            .def(true)
            .build()
    );
    public CapeDisabler(){
        super("cape-disabler", "Disable cc capes.");
    }
}
