package net.i_no_am.clickcrystals.addon.modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.fabricmc.loader.api.FabricLoader;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static net.i_no_am.clickcrystals.addon.AddonManager.MOD_ID;

public class ModMenuDisabler extends DummyModule {
    public ModMenuDisabler() {
        super("mod-menu-disabler", AddonCategory.ADDON, "disable clickcrystals from showing on mod menu, re-launch game after enabling the module");
    }

    @Override
    public void onEnable() {
        try {
            Path configDir = FabricLoader.getInstance().getConfigDir();
            File modMenuConfigFile = configDir.resolve("modmenu.json").toFile();

            if (!modMenuConfigFile.exists()) {
                system.printf("modmenu.json does not exist.");
                return;
            }

            JsonParser parser = new JsonParser();
            JsonObject modMenuConfig;
            try (FileReader reader = new FileReader(modMenuConfigFile)) {
                modMenuConfig = parser.parse(reader).getAsJsonObject();
            }

            JsonArray hiddenMods;
            if (modMenuConfig.has("hidden_mods") && modMenuConfig.get("hidden_mods").isJsonArray()) {
                hiddenMods = modMenuConfig.getAsJsonArray("hidden_mods");
            } else {
                hiddenMods = new JsonArray();
                modMenuConfig.add("hidden_mods", hiddenMods);
            }

            addModToHiddenMods(hiddenMods, modId);
            addModToHiddenMods(hiddenMods, MOD_ID);

            try (FileWriter writer = new FileWriter(modMenuConfigFile)) {
                writer.write(modMenuConfig.toString());
                writer.flush();
            }
        }
        catch (IOException | JsonSyntaxException ignore) {}
    }

    private void addModToHiddenMods(JsonArray hiddenMods, String modId) {
        boolean alreadyHidden = false;
        for (JsonElement element : hiddenMods) {
            if (element.getAsString().equals(modId)) {
                alreadyHidden = true;
                break;
            }
        }
        if (!alreadyHidden) {
            hiddenMods.add(modId);
        }
    }
}
