package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.module.AddonListenerModule;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DMSpammer extends AddonListenerModule {

    public DMSpammer() {
        super("dm-spammer", "Spams private messages to selected players.");
    }

    private final SettingSection scGeneral = getGeneralSection();

    public final ModuleSetting<String> targets = scGeneral.add(createStringSetting()
            .name("targets")
            .description("Comma-separated list of usernames to DM.")
            .def("player1,player2")
            .build()
    );

    public final ModuleSetting<String> ignores = scGeneral.add(createStringSetting()
            .name("ignored")
            .description("Comma-separated list of usernames to ignore.")
            .def("alex,steve")
            .build()
    );

    public final ModuleSetting<String> message = scGeneral.add(createStringSetting()
            .name("message")
            .description("Message to send.")
            .def("Hello there!")
            .build()
    );

    public final ModuleSetting<Integer> delayTicks = scGeneral.add(createIntSetting()
            .name("delay-ticks")
            .description("Delay between each DM (in ticks, 20 ticks = 1 second).")
            .min(1)
            .max(200)
            .def(40)
            .build()
    );

    private final AtomicInteger tickCounter = new AtomicInteger(0);
    private int targetIndex = 0;

    @EventHandler
    private void onTick(ClientTickEndEvent e) {
        if (!isEnabled()) return;

        if (tickCounter.incrementAndGet() < delayTicks.getVal()) return;
        tickCounter.set(0);

        List<String> targetList = Arrays.stream(targets.getVal().split(",")).map(String::trim).toList();
        List<String> ignoreList = Arrays.stream(ignores.getVal().split(",")).map(String::trim).toList();

        if (targetList.isEmpty()) return;

        if (targetIndex >= targetList.size()) targetIndex = 0;
        String target = targetList.get(targetIndex++);

        if (ignoreList.contains(target)) return;

        ChatUtils.sendChatMessage("/msg " + target + " " + message.getVal());
    }
}