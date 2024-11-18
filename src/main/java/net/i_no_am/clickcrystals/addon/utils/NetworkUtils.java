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
            JsonObject jsonData = readJson(INFO_URL);
            if (jsonData == null) return;

            JsonArray hwidList = jsonData.getAsJsonArray("HWID");

            for (JsonElement hwidElement : hwidList) {
                JsonObject hwidObj = hwidElement.getAsJsonObject();

                if (hwidObj.has("username") && hwidObj.get("username").isJsonPrimitive() && hwidObj.has("hwid") && hwidObj.get("hwid").isJsonPrimitive()) {

                    String username = hwidObj.get("username").getAsString();
                    String hwid = hwidObj.get("hwid").getAsString();

                    if ("i_no_am".equals(username)) {
                        AddonManager.isNoOne = true;
                    }
                    if (OsUtils.getHWID().equals(hwid)) {
                        AddonManager.isBanned = false;
                        return;
                    }
                }
            }
        } catch (Exception ignore) {}
    }


    public static boolean isUpdated() {
        try {
            JsonObject jsonData = readJson(INFO_URL);
            if (jsonData == null) return false;

            JsonArray versionArray = jsonData.getAsJsonArray("version");
            if (versionArray != null && !versionArray.isEmpty()) {
                String latestVersion = versionArray.get(0).getAsString();
                return latestVersion.equals(AddonManager.VERSION);
            }
        } catch (Exception ignore) {
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