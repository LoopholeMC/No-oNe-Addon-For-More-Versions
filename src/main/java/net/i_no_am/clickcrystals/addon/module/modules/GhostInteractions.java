package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.networking.PacketSendEvent;
import net.i_no_am.clickcrystals.addon.module.AddonListenerModule;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class GhostInteractions extends AddonListenerModule {
    public GhostInteractions() {
        super("ghost-interactions", "Make it so closing a container will be only client side");
    }

    @EventHandler
    private void onTickSend(PacketSendEvent e) {
        if (e.getPacket() instanceof CloseHandledScreenC2SPacket) {
            e.cancel();
        }
    }
}
