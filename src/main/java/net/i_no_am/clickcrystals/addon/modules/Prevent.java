package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.utils.BlockUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;

public class Prevent extends ListenerModule {
    public Prevent() {
        super("prevent", AddonCategory.ADDON, "Prevent you from doing stupid mistakes in cpvp");
    }

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Boolean> disableGlowstonePlacement = scGeneral.add(createBoolSetting()
            .name("disable-placing-glowstone")
            .description("Disable placing glowstone on any block that isn't anchor.")
            .def(true)
            .build()
    );
    public final ModuleSetting<Boolean> disableAnchorOnGlowstone = scGeneral.add(createBoolSetting()
            .name("disable-anchor-placing-on-glowstone")
            .description("Disable placing anchor on glowstone.")
            .def(true)
            .build()
    );
    public final ModuleSetting<Boolean> disableDoubleAnchorPlacement = scGeneral.add(createBoolSetting()
            .name("disable-placing-anchor-on-anchor")
            .description("Disable placing anchor on anchor.")
            .def(true)
            .build()
    );


   public boolean canPlace() {
        if (Module.get(Prevent.class).isEnabled()) {
            if (HotbarUtils.isHolding(Items.RESPAWN_ANCHOR) && disableDoubleAnchorPlacement.getVal() && BlockUtils.isLookingAt(Blocks.RESPAWN_ANCHOR)) {
                return true;
            } else if (HotbarUtils.isHolding(Items.GLOWSTONE) && disableGlowstonePlacement.getVal() && !BlockUtils.isLookingAt(Blocks.RESPAWN_ANCHOR) || BlockUtils.anchorWithOneCharge()) {
                return true;
            } else
                return HotbarUtils.isHolding(Items.RESPAWN_ANCHOR) && disableAnchorOnGlowstone.getVal() && BlockUtils.isLookingAt(Blocks.GLOWSTONE);
        }
        return false;
    }
}