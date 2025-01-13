package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;

import java.util.UUID;

public class UUIDUtils implements Global {

    public static UUID getUUID(String username) {
        try {
            var json = NetworkUtils.readJson("https://api.mojang.com/users/profiles/minecraft/" + username);
            if (json != null && json.has("id")) {
                String rawUUID = json.get("id").getAsString();
                String formattedUUID = rawUUID.replaceFirst(
                        "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                        "$1-$2-$3-$4-$5"
                );
                return UUID.fromString(formattedUUID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
