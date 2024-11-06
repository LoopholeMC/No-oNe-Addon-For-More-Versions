package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkUtils implements Global {


    /*TODO:
    migrate version and player names to https://i-no-one.github.io/addon/info and make a discord bot that will update it automatically
     */

    private static final String WL = ("https://i-no-one.github.io/info.html");
    private static final String VA = ("https://i-no-one.github.io/addon/version");

    public static void isBan() {
        try {
            if (!new BufferedReader(new InputStreamReader(new URL(WL).openStream())).readLine().contains(MinecraftClient.getInstance().getGameProfile().getName()))
                AddonManager.isBanned = true;
        } catch (Exception ignored) {
        }
    }

    public static boolean isUpdated() {
        try {
            return (new BufferedReader(new InputStreamReader(new URL(VA).openStream())).readLine().equals(AddonManager.VERSION_NUMBER));
        } catch (Exception ignored) {}
        return false;
    }
}