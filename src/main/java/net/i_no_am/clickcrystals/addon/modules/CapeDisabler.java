package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.listener.events.cc.PlayerJoinEvent;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;

public class CapeDisabler extends AddonLModule {
    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Boolean> disableStaffNotifications = scGeneral.add(createBoolSetting()
            .name("disable-staff-notifications")
            .description("Disable the staff message when joining the world.")
            .def(true)
            .build()
    );
    public final ModuleSetting<Boolean> disableClickCrystalCapes = scGeneral.add(createBoolSetting()
            .name("disable-capes")
            .description("Remove staff capes.")
            .def(true)
            .build()
    );
    public CapeDisabler() {
        super("cape-disabler", "Disable cc capes.");
    }

    @EventHandler
    private void onTick(ClientTickEndEvent e){
        if (isEnabled() && disableClickCrystalCapes.getVal()){
            AddonManager.cape.getCapeTexture(null);
            AddonManager.cape.reloadTextures();
        }
    }
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e){
        if (isEnabled() && disableStaffNotifications.getVal())
            e.cancel();
    }
}
