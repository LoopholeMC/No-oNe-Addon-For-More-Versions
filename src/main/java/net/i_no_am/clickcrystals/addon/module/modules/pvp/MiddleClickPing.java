package net.i_no_am.clickcrystals.addon.module.modules.pvp;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.MouseClickEvent;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.module.AddonListenerModule;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;

public class MiddleClickPing extends AddonListenerModule {
    public MiddleClickPing() {
        super("middle-click-ping", "Pings a player in chat when you middle-click on them");
    }

    @EventHandler
    public void onMouseClick(MouseClickEvent e) {

        if (e.getButton() != GLFW.GLFW_MOUSE_BUTTON_MIDDLE || mc.crosshairTarget == null || PlayerUtils.invalid()) return;

        if (mc.crosshairTarget instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() instanceof PlayerEntity targetPlayer) {

            PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(targetPlayer.getUuid());

            if (entry != null) ChatUtils.sendPrefixMessage("Player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + entry.getLatency() + " ms.");
            else ChatUtils.sendPrefixMessage("Could not retrieve ping for player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + ".");
        }
    }
}