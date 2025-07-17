package net.i_no_am.clickcrystals.addon.utils.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.itzispyder.clickcrystals.Global;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NetworkUtils implements Global {


    public static JsonElement getJson(String url, String key) {
        try {
            BetterURL finalUrl = BetterURL.create(url);
            if (finalUrl.toString() == null) return null;

            JsonObject jsonData = readJson(finalUrl.toString());
            if (jsonData == null || !jsonData.has(key)) return null;

            return jsonData.get(key);

        } catch (Exception ignored) {
        }
        return null;
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
