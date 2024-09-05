package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class NetworkUtils implements Global {

    private static Set<String> cachedBannedPlayers;

    public static String findPlayerFromMessage(String message) {
        return Objects.requireNonNull(mc.getNetworkHandler()).getPlayerList().stream()
                .map(player -> player.getProfile().getName())
                .filter(message::contains)
                .findFirst()
                .orElse(null);
    }

    public static void isBan() {
        try {
            if (!new BufferedReader(new InputStreamReader(new URL("https://i-no-one.github.io/info.html").openStream())).readLine().contains(MinecraftClient.getInstance().getGameProfile().getName()))
                AddonManager.isBanned = true;
        } catch (Exception ignored) {
        }
    }
}