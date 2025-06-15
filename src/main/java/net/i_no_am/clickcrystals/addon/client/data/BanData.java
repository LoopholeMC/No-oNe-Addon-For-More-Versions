package net.i_no_am.clickcrystals.addon.client.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.client.data.ban.BanState;
import net.i_no_am.clickcrystals.addon.utils.OsUtils;

import java.util.Map;

import static net.i_no_am.clickcrystals.addon.utils.NetworkUtils.isValid;
import static net.i_no_am.clickcrystals.addon.utils.NetworkUtils.readJson;


public class BanData implements Global {

    public BanState getBan() {
        try {
            String url = isValid(Constants.URL.API);
            if (url == null) return BanState.FAILED;

            JsonObject jsonData = readJson(url);
            if (jsonData == null || !jsonData.has("HWID")) return BanState.BAN;

            JsonObject hwidData = jsonData.getAsJsonObject("HWID");
            String currentHwid = OsUtils.getHWID();

            for (Map.Entry<String, JsonElement> entry : hwidData.entrySet()) {
                String username = entry.getKey();
                JsonObject hwidObj = entry.getValue().getAsJsonObject();

                if (username.equalsIgnoreCase("i_no_am"))
                    return BanState.I_NO_AM;

                if (hwidObj.has("hwid") && currentHwid.equals(hwidObj.get("hwid").getAsString()))
                    return BanState.ALLOWED;
            }

        } catch (Exception e) {
            system.logger.error("Failed to check ban status -> " + e.getMessage());
        }

        return BanState.BAN;
    }
}