package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

import java.util.Objects;

public class AutoRefill extends ListenerModule {

    public AutoRefill() {
        super("auto-refill", AddonCategory.ADDON, "Automatically refills missing items in your hotbar.");
    }
    private final SettingSection scGeneral = getGeneralSection();
    public final ModuleSetting<Double> itemCount = scGeneral.add(createDoubleSetting()
            .name("item-count")
            .description("When the module will start refilling:")
            .def(16.0)
            .min(0.0)
            .max(64.0)
            .build()
    );

    @EventHandler
    private void onTick(ClientTickEndEvent e) {
        if (PlayerUtils.invalid()) return;

        PlayerInventory inventory = PlayerUtils.player().getInventory();
        checkAndRefillHotbar(inventory);
    }

    private void checkAndRefillHotbar(PlayerInventory inventory) {
        for (int i = 0; i < 9; i++) {
            ItemStack hotbarStack = inventory.getStack(i);
            if (hotbarStack.isEmpty() || hotbarStack.getCount() < itemCount.getVal()) {
                refillFromInventory(inventory, i);
            }
        }
    }

    private void refillFromInventory(PlayerInventory inventory, int hotbarSlot) {
        for (int i = 9; i < inventory.size(); i++) {
            if (i == 40) continue;

            ItemStack stack = inventory.getStack(i);
            ItemStack hotbarStack = inventory.getStack(hotbarSlot);
            if (!stack.isEmpty() && (hotbarStack.isEmpty() || ItemStack.areItemsEqual(stack, hotbarStack))) {
                Objects.requireNonNull(mc.interactionManager).clickSlot(PlayerUtils.player().playerScreenHandler.syncId,i,hotbarSlot,SlotActionType.SWAP,mc.player);
                break;
            }
        }
    }
}

