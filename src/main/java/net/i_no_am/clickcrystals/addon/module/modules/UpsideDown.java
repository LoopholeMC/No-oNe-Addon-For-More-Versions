package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class UpsideDown extends ListenerModule {
    public UpsideDown() {
        super("upside-down", AddonCategory.ADDON, "Make every player & entity upside down.");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<IgnoreType> ignoreType = scGeneral.add(createEnumSetting(IgnoreType.class)
            .name("ignore-type")
            .description("Make it so the selected entity won't be affected by this module")
            .def(IgnoreType.NONE)
            .build()
    );

    public enum IgnoreType {
        SELF, PLAYERS, ENTITIES, NONE
    }

    public boolean shouldIgnore(Entity entity) {
        IgnoreType ignoreType = this.ignoreType.getVal();
        return switch (ignoreType) {
            case SELF -> entity instanceof ClientPlayerEntity;
            case PLAYERS -> entity instanceof PlayerEntity;
            case ENTITIES -> !(entity instanceof ClientPlayerEntity);
            case NONE -> false;
        };
    }
}