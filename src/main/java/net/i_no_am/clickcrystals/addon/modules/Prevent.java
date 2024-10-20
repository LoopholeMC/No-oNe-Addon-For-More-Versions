package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import net.i_no_am.clickcrystals.addon.modules.data.AddonModule;
import net.i_no_am.clickcrystals.addon.utils.BlockUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class Prevent extends AddonModule {
    public Prevent() {
        super("prevent", "Prevent making common mistakes in crystal PvP");
    }

    private final SettingSection scGeneral = getGeneralSection();

    public final ModuleSetting<Boolean> disableGlowstoneOnGlowstone = scGeneral.add(createBoolSetting()
            .name("disable-placing-glowstone-on-glowstone")
            .description("Disable placing glowstone unless not targeting a glowstone.")
            .def(true)
            .build()
    );
        public final ModuleSetting<Boolean> disableP = scGeneral.add(createBoolSetting()
            .name("disable-placing-anchor-on-anchor")
            .description("Disable placing a respawn anchor on top of another anchor.")
            .def(true)
            .build()
    );
    public final ModuleSetting<Boolean> disableAnchorOnGlowstone = scGeneral.add(createBoolSetting()
            .name("disable-placing-anchor-on-glowstone")
            .description("Disable placing a respawn anchor on glowstone.")
            .def(true)
            .build()
    );
    public final ModuleSetting<Boolean> disableDoubleAnchorPlacement = scGeneral.add(createBoolSetting()
            .name("disable-placing-anchor-on-anchor")
            .description("Disable placing a respawn anchor on top of another anchor.")
            .def(true)
            .build()
    );
    
    public ActionResult cannotPlace() {
        if (HotbarUtils.isHolding(Items.RESPAWN_ANCHOR)
                && disableDoubleAnchorPlacement.getVal()
                && BlockUtils.isLookingAt(Blocks.RESPAWN_ANCHOR)) {
            if (BlockUtils.anchorWithCharges(1))
                return ActionResult.PASS;
            else {
                return ActionResult.FAIL;
            }
        } else if (HotbarUtils.isHolding(Items.GLOWSTONE)
                && disableGlowstonePlacement.getVal() &&
                HotbarUtils.isHoldingEitherHand(Items.GLOWSTONE)
                && (!BlockUtils.isLookingAt(Blocks.RESPAWN_ANCHOR) || BlockUtils.anchorWithCharges(1))) {
            return ActionResult.FAIL;
        } else if (HotbarUtils.isHolding(Items.RESPAWN_ANCHOR)
                && disableAnchorOnGlowstone.getVal()
                && BlockUtils.isLookingAt(Blocks.GLOWSTONE)) {
            return ActionResult.FAIL;
        } else if isHolding(Items.GLOWSTONE)
                && disableGlowstoneOnGlowstone.getVal()
                && BlockUtils.isLookingAt(Blocks.GLOWSTONE)) {
            return ActionResult.FAIL;
        return ActionResult.PASS;
    }
}

