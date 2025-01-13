package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.data.Config;
import io.github.itzispyder.clickcrystals.gui.screens.settings.AdvancedSettingScreen;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.BooleanSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.modules.settings.StringSetting;
import net.i_no_am.clickcrystals.addon.interfaces.IData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AdvancedSettingScreen.class,remap = false)
public class MixinAdvancedSettingScreen implements IData {
    @Shadow @Final private SettingSection scGui;
    @Unique private String path;


    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lio/github/itzispyder/clickcrystals/gui/screens/settings/AdvancedSettingScreen;scGui:Lio/github/itzispyder/clickcrystals/modules/settings/SettingSection;", shift = At.Shift.BY, by = 1))
    public void onInit(CallbackInfo ci) {
        ModuleSetting<Boolean> useGhostPath = scGui.add(BooleanSetting.create()
                .name("move-config")
                .description("move the config to ghost path")
                .def(false)
                .build()
        );
        ModuleSetting<String> ghostPath = scGui.add(StringSetting.create()
                .name("ghost-path")
                .description("a ghost path that will move the config to its name")
                // A nice reference to Thunder Hack Recode UnHook.java
                .def("XaeroWaypoints_BACKUP092738")
                .build()
        );
        path = (useGhostPath.getVal() && !ghostPath.getVal().isEmpty()) ? ghostPath.getVal() : Config.PATH;
    }

    @Override
    public String getGhostPath() {
        return this.path + "/";
    }
}