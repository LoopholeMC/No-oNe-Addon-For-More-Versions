package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.Categories;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AutoRefill extends ListenerModule {
    public AutoRefill() {
        super("auto-refill", Categories.PVP,"automatically refill missing items");
    }
    public static final int OFFHAND_ID = PlayerInventory.OFF_HAND_SLOT;
    public static final int OFFHAND_PKT_ID = 45;

    private static final List<Integer> SEARCH_SLOTS =
            Stream.concat(IntStream.range(0, 36).boxed(), Stream.of(OFFHAND_ID))
                    .collect(Collectors.toCollection(ArrayList::new));




    }
