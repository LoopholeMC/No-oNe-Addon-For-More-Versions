package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.Listener;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// ported from https://github.com/MeteorDevelopment/meteor-client/pull/4923/

public class AutoCreeperIgnite extends DummyModule implements Listener {
    public AutoCreeperIgnite(){
        super("auto-creeper-ignite", AddonCategory.ADDON,"explode creepers using flint and steal");
    }
    private final SettingSection scGeneral = getGeneralSection();

    private final ModuleSetting<Boolean> autoSwitch = scGeneral.add(createBoolSetting()
            .name("auto-switch")
            .description("automatically switches to flint and steal if in hotbar")
            .def(true)
            .build()
    );

    private final ModuleSetting<Boolean> ignoreNamed = scGeneral.add(createBoolSetting()
            .name("ignore-named")
            .description("Ignore creepers that are named")
            .def(false)
            .build()
    );

    private final ModuleSetting<Double> distance = scGeneral.add(createDoubleSetting()
            .name("distance")
            .description("The maximum distance the creeper has to be to be ignited.")
            .min(0.0)
            .def(5.0)
            .max(6.0)
            .build()
    );

    private final ArrayList<CreeperEntity> targets = new ArrayList<>();
    private Hand hand;

    @Override
    public void onDisable() {
        targets.clear();
    }

    @EventHandler
    public void onTick(ClientTickEndEvent e) {
        Item mainHand = PlayerUtils.player().getInventory().getStack(PlayerUtils.player().getInventory().selectedSlot).getItem();
        Item offHand = PlayerUtils.player().getOffHandStack().getItem();
        // Return early if not needed
        if (!autoSwitch.getVal() && !(mainHand instanceof FlintAndSteelItem) && !(offHand instanceof FlintAndSteelItem)) return;
        checkEntities();

    }
    public void checkEntities() {
        for (Entity entity : PlayerUtils.player().clientWorld.getEntities()) {
            if (entity instanceof CreeperEntity creeper && creeper.isAlive() &&
                    PlayerUtils.getNearestEntity(distance.getVal(), e -> e instanceof CreeperEntity) == creeper &&
                    !creeper.isIgnited() && !targets.contains(creeper)) {
                if (ignoreNamed.getVal() && creeper.hasCustomName()) continue;
                targets.add(creeper);
                CreeperEntity currentTarget = targets.get(ThreadLocalRandom.current().nextInt(targets.size()));
                checkHands(currentTarget);
            }
        }
    }


    public void checkHands(CreeperEntity currentTarget) {
        Item mainHand = PlayerUtils.player().getInventory().getStack(PlayerUtils.player().getInventory().selectedSlot).getItem();
        Item offHand = PlayerUtils.player().getOffHandStack().getItem();
//         If player isn't holding flint and steal and auto switch is enabled, switch and then ignite
        if (autoSwitch.getVal() && !(mainHand instanceof FlintAndSteelItem) && !(offHand instanceof FlintAndSteelItem)) {
            HotbarUtils.search(Items.FLINT_AND_STEEL);
            ignite(currentTarget);
        }
        if (mainHand instanceof FlintAndSteelItem) {
            hand = Hand.MAIN_HAND;
            ignite(currentTarget);
        }
        if (offHand instanceof FlintAndSteelItem) {
            hand = Hand.OFF_HAND;
            ignite(currentTarget);
        }
    }
    public void ignite(CreeperEntity target) {
        mc.interactionManager.interactEntity(mc.player, target, hand);
        targets.remove(target);
    }
}