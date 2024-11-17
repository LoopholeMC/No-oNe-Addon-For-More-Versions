package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.keybinds.Keybind;
import io.github.itzispyder.clickcrystals.modules.settings.KeybindSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.List;

public class LootKeeper extends AddonLModule {

    public LootKeeper() {
        super("loot-keeper", "Throw every item on slot that isn't in the list.");
    }

    private boolean isOn = false;
    private long lastThrowTime = 0;
    private static final long THROW_DELAY_MS = 100;

    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<String> listItem = scGeneral.add(createStringSetting()
            .name("list-of-items-to-not-throw")
            .description("Which items the module won't throw, separated by commas:")
            .def("diamond,netherite,crystals,totem,pearl,sword,elytra,rockets")
            .build()
    );

    public final ModuleSetting<Keybind> activateModule = scGeneral.add(KeybindSetting.create()
            .name("activate-module-bind")
            .description("Key to start throwing items not in the list.")
            .def(GLFW.GLFW_KEY_G)
            .onPress(keybind -> {
                isOn = !isOn;
                ChatUtils.sendPrefixMessage("Module is " + (isOn ? Formatting.AQUA + "on" : Formatting.RED + "off") + Formatting.RESET);
            })
            .condition((bind, screen) -> screen == null)
            .build()
    );

    @EventHandler
    private void onTick(ClientTickEndEvent e) {
        if (PlayerUtils.invalid() || !isOn) return;

        long currentTime = System.currentTimeMillis();

        List<String> itemsToKeep = Arrays.stream(listItem.getVal().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        boolean shouldKeepItem = itemsToKeep.stream().anyMatch(HotbarUtils::nameContains);

        ItemStack slot = PlayerUtils.player().getStackInHand(Hand.MAIN_HAND);

        if (!slot.isEmpty() && !shouldKeepItem && (currentTime - lastThrowTime) >= THROW_DELAY_MS) {
            PlayerUtils.player().dropSelectedItem(true);
            lastThrowTime = currentTime;
            if (slot.isEmpty()) PlayerUtils.player().swingHand(Hand.MAIN_HAND);
        }
    }
}
