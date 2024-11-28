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
    public final ModuleSetting<String> itemName = scGeneral.add(createStringSetting()
            .name("item-name")
            .description("If holding the required item, the module will work.")
            .def("totem,crystal,pearl")
            .build()
    );
    public final ModuleSetting<Boolean> disableMainHandSwap = scGeneral.add(createBoolSetting()
            .name("disable-main-hand-swap")
            .description("If holding the item in the list, swapping from main to off hand won't work")
            .def(false)
            .build()
    );

    @EventHandler
    private void onKeyPress(KeyPressEvent e) {
        if (e.getKeycode() == mc.options.swapHandsKey.getDefaultKey().getCode()) {
            if (isHoldingRestrictedItem(Hand.OFF_HAND))
                e.cancel();
        }
        if (e.getKeycode() == mc.options.swapHandsKey.getDefaultKey().getCode()) {
            if (isHoldingRestrictedItem(Hand.MAIN_HAND) && disableMainHandSwap.getVal())
                e.cancel();
        }
    }

    private boolean isHoldingRestrictedItem(Hand hand) {
        List<String> restrictedItems = Arrays.asList(itemName.getVal().split(","));
        return restrictedItems.stream().anyMatch(item -> HotbarUtils.nameContains(item, hand));
    }
}
