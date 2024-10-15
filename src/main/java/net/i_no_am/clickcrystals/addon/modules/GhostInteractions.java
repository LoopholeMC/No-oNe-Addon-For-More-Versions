package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.networking.PacketSendEvent;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class GhostInteractions extends ListenerModule {
    public GhostInteractions(){
        super("ghost-interactions", AddonCategory.ADDON,"Make it so closing a container will be only client side");
    }
    @EventHandler
    private void onTickSend(PacketSendEvent e){
        if (e.getPacket() instanceof CloseHandledScreenC2SPacket){
            e.cancel();
        }
    }
}
