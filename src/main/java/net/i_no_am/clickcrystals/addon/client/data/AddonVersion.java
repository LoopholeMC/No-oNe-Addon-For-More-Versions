package net.i_no_am.clickcrystals.addon.client.data;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.i_no_am.clickcrystals.addon.client.Manager;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddonVersion {

    @Nullable private final String version;

    public AddonVersion() {
        this(getVersion() != null ? getVersion().version : null);
    }

    private AddonVersion(@Nullable String version) {
        this.version = version;
    }

    public String get() {
        return new AddonVersion().version;
    }


    private static AddonVersion getVersion() {
        ModContainer mod = FabricLoader.getInstance().getModContainer(Manager.constants.MOD_ID).orElseThrow();
        String friendlyVersion = mod.getMetadata().getVersion().getFriendlyString();
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+)(?=-fabric|$)");
        Matcher matcher = pattern.matcher(friendlyVersion);
        if (matcher.find()) {
            return new AddonVersion(matcher.group(1));
        }
        return null;
    }
}
