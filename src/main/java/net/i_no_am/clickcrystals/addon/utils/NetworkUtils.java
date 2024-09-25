package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkUtils implements Global {

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