package net.i_no_am.clickcrystals.addon.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;

import java.io.InputStreamReader;
import java.net.URL;

public class NetworkUtils implements Global {

    private static final String INFO_URL = "http://api.tutla.net/cc/reg.json";

    public static void isBan() {
        try {
            JsonArray hwidList = readJson(INFO_URL).getAsJsonArray("HWID");

            for (JsonElement hwid : hwidList) {
                if (hwid.getAsJsonObject().get("name").getAsString().equals("i_no_am"))
                    AddonManager.isNoOne = true;
                if (hwid.getAsJsonObject().get("hwid").getAsString().equals(OsUtils.getHWID())) {
                    AddonManager.isBanned = false;
                    return;
                }
            }
        } catch (Exception ignored) {
        }
    }

    public static boolean isUpdated() {
        try {
            JsonArray versionArray = readJson(INFO_URL).getAsJsonArray("version");
            if (!versionArray.isEmpty()) {
                String latestVersion = versionArray.get(0).getAsString();
                return latestVersion.equals(AddonManager.VERSION);
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public static JsonObject readJson(String site) throws Exception {
        URL url = new URL(site);
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }
}
