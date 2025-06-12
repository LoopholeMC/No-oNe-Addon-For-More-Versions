package net.i_no_am.clickcrystals.addon.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.client.Manager;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils implements Global {

    private static final String BACKUP = "https://i-no-one.github.io/addon/info";
    private static final String API = "https://no-one-s-api-default-rtdb.firebaseio.com/.json";

    private static final Map<String, String> cache = new HashMap<>();

    public static String getUrl() {
        if (isValid(API)) return API;
        if (isValid(BACKUP)) return BACKUP;
        return null;
    }

    public static boolean isValid(String url) {
        try {
            int status = getResponse(url).statusCode();
            return status == 200 || status == 201;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBan() {
        try {
            String url = getUrl();
            if (url == null) return true;

            JsonObject jsonData = readJson(url);
            if (jsonData == null || !jsonData.has("HWID")) return true;

            JsonObject hwidData = jsonData.getAsJsonObject("HWID");
            String currentHwid = OsUtils.getHWID();

            for (Map.Entry<String, JsonElement> entry : hwidData.entrySet()) {
                String username = entry.getKey();
                JsonObject hwidObj = entry.getValue().getAsJsonObject();

                if (username.equals("i_no_am")) {
                    Manager.constants.isNoOne = true;
                }

                if (hwidObj.has("hwid") && currentHwid.equals(hwidObj.get("hwid").getAsString())) {
                    return false; // Not banned
                }
            }

        } catch (Exception ignored) {}

        return true;
    }

    public static boolean isUpdated() {
        try {
            // Use cached value if available
            if (cache.containsKey("latestVersion")) {
                return cache.get("latestVersion").equals(Manager.version.get());
            }

            String url = getUrl();
            if (url == null) return false;

            JsonObject jsonData = readJson(url);
            if (jsonData == null || !jsonData.has("version")) return false;

            String latestVersion = String.valueOf(jsonData.get("version").getAsInt());
            cache.put("latestVersion", latestVersion);
            return latestVersion.equals(Manager.version.get());

        } catch (Exception ignored) {}

        return false;
    }

    public static HttpResponse<String> getResponse(String link) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static JsonObject readJson(String site) throws Exception {
        HttpResponse<String> response = getResponse(site);
        return JsonParser.parseReader(new StringReader(response.body())).getAsJsonObject();
    }
}
