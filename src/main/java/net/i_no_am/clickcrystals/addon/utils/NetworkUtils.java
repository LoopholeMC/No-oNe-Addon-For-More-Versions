package net.i_no_am.clickcrystals.addon.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.client.Manager;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils implements Global {

    private static final String BACKUP = "https://i-no-one.github.io/addon/info";
    private static final String API = "http://api.tutla.net/cc/reg.json";

    public static String AddonUrl() {
        try {
            if (isUrlWorking(API)) return API;
            if (isUrlWorking(BACKUP)) return BACKUP;
        } catch (Exception ignore) {}
        return null;
    }

    public static boolean isUrlWorking(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            return connection.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public static void isBan() {
        try {
            String url = AddonUrl();
            if (url == null) return;

            JsonObject jsonData = readJson(url);
            if (jsonData == null) return;

            JsonArray hwidList = jsonData.getAsJsonArray("HWID");

            for (JsonElement hwidElement : hwidList) {
                JsonObject hwidObj = hwidElement.getAsJsonObject();

                if (hwidObj.has("username") && hwidObj.get("username").isJsonPrimitive() && hwidObj.has("hwid") && hwidObj.get("hwid").isJsonPrimitive()) {

                    String username = hwidObj.get("username").getAsString();
                    String hwid = hwidObj.get("hwid").getAsString();

                    if ("i_no_am".equals(username)) {
                        Manager.isNoOne = true;
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
            String url = AddonUrl();
            if (url == null) return false;

            JsonObject jsonData = readJson(url);
            if (jsonData == null) return false;

            JsonArray versionArray = jsonData.getAsJsonArray("version");
            if (versionArray != null && !versionArray.isEmpty()) {
                String latestVersion = versionArray.get(0).getAsString();
                return latestVersion.equals(Manager.addonVersion);
            }
        } catch (Exception ignore) {}
        return false;
    }

    public static JsonObject readJson(String site) throws Exception {
        URL url = new URL(site);
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }
}
