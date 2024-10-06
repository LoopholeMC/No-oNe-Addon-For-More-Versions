package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.MouseClickEvent;
import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.BlockUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;

import java.util.Objects;

public class Prevent extends ListenerModule {
    public Prevent() {
        super("prevent", AddonCategory.ADDON, "Prevent you from doing stupid mistakes in cpvp");
    }

    private final SettingSection scGeneral = getGeneralSection();

    public final ModuleSetting<Boolean> cancelGlowStonePlacement = scGeneral.add(createBoolSetting()
            .name("disable-placing-glowstone")
            .description("Disable placing glowstone on any block that isn't anchor.")
            .def(true)
            .build()
    );

    public final ModuleSetting<Boolean> cancelAnchorOnGlowStonePlacement = scGeneral.add(createBoolSetting()
            .name("disable-anchor-placing-on-glowstone")
            .description("Disable placing anchor on glowstone.")
            .def(true)
            .build()
    );

    public final ModuleSetting<Boolean> cancelAnchorOnAnchorPlacement = scGeneral.add(createBoolSetting()
            .name("disable-placing-anchor-on-anchor")
            .description("Disable placing anchor on anchor.")
            .def(true)
            .build()
    );

    @EventHandler
    private void onMouseClick(MouseClickEvent e) {
        if (Module.get(Prevent.class).isEnabled() && e.getButton() == 2 && e.getAction().isDown()) {
            if (HotbarUtils.isHolding(Items.RESPAWN_ANCHOR) && cancelAnchorOnAnchorPlacement.getVal()
                    && BlockUtils.matchTargetBlock(Blocks.RESPAWN_ANCHOR)) {
                e.setCancelled(true);
            }

            if (HotbarUtils.isHolding(Items.GLOWSTONE) && cancelGlowStonePlacement.getVal() && BlockUtils.matchTargetBlock(Blocks.RESPAWN_ANCHOR)) {
                if (mc.crosshairTarget instanceof BlockHitResult blockHitResult) {
                    var blockState = Objects.requireNonNull(mc.world).getBlockState(blockHitResult.getBlockPos());
                    int charges = blockState.get(RespawnAnchorBlock.CHARGES);
                    e.setCancelled(true);
                    if (charges > 1) {
                        e.setCancelled(true);
                    }
                }
            }

            if (HotbarUtils.isHolding(Items.RESPAWN_ANCHOR) && cancelAnchorOnGlowStonePlacement.getVal() &&
                    BlockUtils.matchTargetBlock(Blocks.GLOWSTONE)) {
                e.setCancelled(true);
            }
        }
    }
}