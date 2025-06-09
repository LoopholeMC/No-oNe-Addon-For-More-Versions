package net.i_no_am.clickcrystals.addon.module.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.MouseClickEvent;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.module.AddonListenerModule;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class MiddleClickPing extends AddonListenerModule {
    public MiddleClickPing() {
        super("middle-click-ping", "Press middle click on a player to get their ping.");
    }

    @EventHandler
    public void onMouseClick(MouseClickEvent e) {
        if (e.getButton() != GLFW.GLFW_MOUSE_BUTTON_MIDDLE && mc.currentScreen != null && mc.crosshairTarget == null) return;
        if (mc.crosshairTarget instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() instanceof PlayerEntity targetPlayer) {
            PlayerListEntry entry = Objects.requireNonNull(mc.getNetworkHandler()).getPlayerListEntry(targetPlayer.getUuid());
            if (entry != null)
                ChatUtils.sendPrefixMessage("Player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + entry.getLatency() + " ms.");
            else
                ChatUtils.sendPrefixMessage("Could not retrieve ping for player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + ".");
        }
    }
}