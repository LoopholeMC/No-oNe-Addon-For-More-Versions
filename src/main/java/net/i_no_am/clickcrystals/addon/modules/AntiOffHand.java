package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.KeyPressEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;
import net.minecraft.util.Hand;

import java.util.Arrays;
import java.util.List;

public class AntiOffHand extends AddonLModule {

    public AntiOffHand() {
        super("anti-off-hand", "Cancel swapping items if certain conditions are met");
    }

    private final SettingSection scGeneral = getGeneralSection();

    public final ModuleSetting<String> offHandList = scGeneral.add(createStringSetting()
            .name("off-hand-item-list")
            .description("List of items that prevent off-hand swap.")
            .def("totem,crystal,pearl")
            .build()
    );

    public final ModuleSetting<String> mainHandList = scGeneral.add(createStringSetting()
            .name("main-hand-item-list")
            .description("List of items that prevent main-hand swap.")
            .def("totem,crystal,pearl")
            .build()
    );

    public final ModuleSetting<Boolean> disableMainHandSwap = scGeneral.add(createBoolSetting()
            .name("disable-main-hand-swap")
            .description("If enabled, swapping items from the main hand to the off-hand will be blocked if holding restricted items.")
            .def(false)
            .build()
    );

    @EventHandler
    private void onKeyPress(KeyPressEvent e) {
        if (e.getKeycode() == mc.options.swapHandsKey.getDefaultKey().getCode()) {
            if (isHoldingRestrictedItem(Hand.OFF_HAND, offHandList.getVal())) {
                e.cancel();
            }

            if (disableMainHandSwap.getVal() && isHoldingRestrictedItem(Hand.MAIN_HAND, mainHandList.getVal())) {
                e.cancel();
            }
        }
    }

    private boolean isHoldingRestrictedItem(Hand hand, String itemList) {
        List<String> restrictedItems = Arrays.asList(itemList.split(","));
        return restrictedItems.stream().anyMatch(item -> HotbarUtils.nameContains(item.trim(), hand));
    }
}
