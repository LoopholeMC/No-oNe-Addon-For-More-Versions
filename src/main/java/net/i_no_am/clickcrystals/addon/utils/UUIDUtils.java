package net.i_no_am.clickcrystals.addon.utils;

import io.github.itzispyder.clickcrystals.Global;

import java.util.UUID;

public class UUIDUtils implements Global {

    public static UUID getUUID(String username) {
        try {
            var json = NetworkUtils.readJson("https://api.mojang.com/users/profiles/minecraft/" + username);
            if (json != null && json.has("id"))
                return UUID.fromString(json.get("id").getAsString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}