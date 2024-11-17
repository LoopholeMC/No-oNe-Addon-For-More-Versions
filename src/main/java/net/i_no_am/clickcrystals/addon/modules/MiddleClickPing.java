package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.client.MouseClickEvent;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.modules.data.AddonLModule;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.Objects;

public class MiddleClickPing extends AddonLModule {
    public MiddleClickPing() {
        super("middle-click-ping", "Press middle click on a player to get their ping.");
    }

    @EventHandler
    public void onMouseClick(MouseClickEvent e) {
        if (e.getButton() == 2 && mc.currentScreen == null) {
            if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) mc.crosshairTarget;
                if (entityHitResult.getEntity() instanceof PlayerEntity targetPlayer) {
                    PlayerListEntry entry = Objects.requireNonNull(mc.getNetworkHandler()).getPlayerListEntry(targetPlayer.getUuid());
                    if (entry != null) {
                        int ping = entry.getLatency();
                        ChatUtils.sendPrefixMessage("Player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + ping + " ms.");
                    } else {
                        ChatUtils.sendPrefixMessage("Could not retrieve ping for player " + Formatting.AQUA + targetPlayer.getName().getString() + Formatting.RESET + ".");
                    }
                }
            }
        }
    }
}