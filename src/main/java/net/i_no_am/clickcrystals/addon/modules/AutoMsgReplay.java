package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.ChatReceiveEvent;
import io.github.itzispyder.clickcrystals.events.events.client.ChatSendEvent;
import io.github.itzispyder.clickcrystals.modules.ModuleSetting;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.modules.settings.SettingSection;
import io.github.itzispyder.clickcrystals.modules.settings.StringSetting;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AutoMsgReplay extends ListenerModule {

    private final Map<String, String> playerCache = new ConcurrentHashMap<>();
    private boolean startConversation = false;
    private String playerName = null;
    private final SettingSection scGeneral = getGeneralSection();

    public final ModuleSetting<String> messageKeywords = scGeneral.add(StringSetting.create()
            .name("message-keywords")
            .description("Comma-separated keywords that indicate a private message.")
            .def("msg,whisper,tell")
            .build()
    );

    public final ModuleSetting<Boolean> keepChatting = scGeneral.add(createBoolSetting()
            .name("keep-chatting")
            .description("If enabled, the conversation won't be reset after a message is sent.")
            .def(false)
            .build()
    );

    public final ModuleSetting<Boolean> debug = scGeneral.add(createBoolSetting()
            .name("debug")
            .description("Enables debug mode for testing.")
            .def(false)
            .build()
    );

    public AutoMsgReplay() {
        super("auto-message-replay", AddonCategory.ADDON, "Automatically replay privately to players.");
    }

    @EventHandler
    public void onChatReceive(ChatReceiveEvent event) {
        String[] keywords = messageKeywords.getVal().split(",");
        for (String keyword : keywords) {
            if (event.getMessage().contains(keyword.trim())) {
                playerName = NetworkUtils.findPlayerFromMessage(event.getMessage());
                if (playerName != null && !playerName.equals(mc.getSession().getUsername())) {
                    if (debug.getVal()) ChatUtils.sendMessage("Detected player: " + playerName);
                    startConversation = true;
                    playerCache.put(playerName, event.getMessage());
                } else {
                    if (debug.getVal()) ChatUtils.sendMessage("Skipping own name: " + mc.getSession().getUsername());
                }
                break;
            }
        }
    }

    @EventHandler
    public void onChatSend(ChatSendEvent event) {
        if (event.getMessage() == null || playerName == null || !startConversation) return;

        if (event.getMessage() != null) event.cancel();

        if (event.isCancelled()) {
            playerCache.put(playerName, event.getMessage());
            replayLastMessage();

            if (!keepChatting.getVal()) {
                playerCache.remove(playerName);
                startConversation = false;
            }
        }
    }

    public void replayLastMessage() {
        if (playerName != null && playerCache.containsKey(playerName)) {
            String lastMessage = playerCache.get(playerName);
            ChatUtils.sendChatCommand("msg " + playerName + " " + lastMessage);
        }
    }
}
