package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.modules.data.AddonModule;
import net.minecraft.util.Identifier;

public class NameChanger extends AddonModule {
    public NameChanger(){
        super("name-changer","change your player name (client side)");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<String> fakeName = scGeneral.add(createStringSetting()
            .name("player-name")
            .description("A name that your real name will be replaced with")
            .def("Steve1")
            .build()
    );
    public final ModuleSetting<Boolean> skin = scGeneral.add(createBoolSetting()
            .name("change-skin")
            .description("Change player skin")
            .def(false)
            .build()
    );

    public Identifier path(){
        return skin.getVal() ? Identifier.ofVanilla("textures/entity/player/slim/steve.png") : null;
    }
}
