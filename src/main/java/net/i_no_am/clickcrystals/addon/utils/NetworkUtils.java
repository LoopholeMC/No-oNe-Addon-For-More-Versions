package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils implements Global {

    private static final String ADDON_SITE = ("https://i-no-one.github.io/info.html");
    private static final String GITHUB_API_URL = "https://api.github.com/repos/I-No-oNe/No-oNe-Addon/releases/latest";
    private static final String GITHUB_TOKEN = System.getenv("CC_GITHUB_TOKEN");

    public static void isBan() {
        try {
            if (!new BufferedReader(new InputStreamReader(new URL(ADDON_SITE).openStream())).readLine().contains(MinecraftClient.getInstance().getGameProfile().getName()))
                AddonManager.isBanned = true;
        } catch (Exception ignored) {}
    }

    public static boolean isUpdated() {
        System.out.println(getLatestVersionFromGitHub());
        return AddonManager.VERSION_NUMBER.equals(getLatestVersionFromGitHub());
    }

    public static String getLatestVersionFromGitHub() {
        try {
            URL url = new URL(GITHUB_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("Authorization", "token " + GITHUB_TOKEN);
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            String versionTag = jsonResponse.split("\"tag_name\":\"")[1].split("\"")[0];
            return versionTag.split("-")[0];
        } catch (Exception e) {
            return "Error fetching version";
        }
    }
}
