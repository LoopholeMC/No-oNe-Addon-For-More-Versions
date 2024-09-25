package net.i_no_am.clickcrystals.addon.modules;

import com.terraformersmc.modmenu.api.UpdateChecker;
import com.terraformersmc.modmenu.api.UpdateInfo;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SelfDestruct extends ListenerModule implements Mod {
    public SelfDestruct() {
        super("self-destruct", AddonCategory.ADDON, "Make it so it will be like cc never have been installed");
    }

    @Override
    public @NotNull NativeImageBackedTexture getIcon(FabricIconHandler iconHandler, int i) {
        return null;
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0"; // Example version
    }

    @Override
    public @NotNull String getPrefixedVersion() {
        return "v1.0"; // Example prefixed version
    }

    @Override
    public @NotNull List<String> getAuthors() {
        return List.of("Your Name"); // Your name or team
    }

    @Override
    public @NotNull Map<String, Collection<String>> getContributors() {
        return Map.of("Contributors", List.of("Contributor Name")); // Add contributors
    }

    @Override
    public @NotNull SortedMap<String, Set<String>> getCredits() {
        return new TreeMap<>(); // Provide credits if necessary
    }

    @Override
    public @NotNull Set<Badge> getBadges() {
        return Set.of(); // Add badges if applicable
    }

    @Override
    public @Nullable String getWebsite() {
        return "https://yourmodwebsite.com"; // Your mod's website
    }

    @Override
    public @Nullable String getIssueTracker() {
        return "https://yourissue.tracker.com"; // Link to issue tracker
    }

    @Override
    public @Nullable String getSource() {
        return "https://github.com/yourrepo"; // Link to source code
    }

    @Override
    public @Nullable String getParent() {
        return null; // If applicable
    }

    @Override
    public @NotNull Set<String> getLicense() {
        return Set.of("MIT"); // Specify your license
    }

    @Override
    public @NotNull Map<String, String> getLinks() {
        return Map.of(); // Add any other relevant links
    }

    @Override
    public boolean isReal() {
        return true; // Set to true if the mod is real
    }

    @Override
    public boolean allowsUpdateChecks() {
        return false; // Set to true if you want to allow updates
    }

    @Override
    public @Nullable UpdateChecker getUpdateChecker() {
        return null; // Implement if needed
    }

    @Override
    public void setUpdateChecker(@Nullable UpdateChecker updateChecker) {
        // Implement if needed
    }

    @Override
    public @Nullable UpdateInfo getUpdateInfo() {
        return null; // Implement if needed
    }

    @Override
    public void setUpdateInfo(@Nullable UpdateInfo updateInfo) {
        // Implement if needed
    }

    @Override
    public void setChildHasUpdate() {
        // Implement if needed
    }

    @Override
    public boolean getChildHasUpdate() {
        return false; // Set to true if a child mod has an update
    }

    @Override
    public boolean isHidden() {
        // Always hide this mod
        if (this.getId().equals("no-one-addon")) {
            return true;
        }

        // Check if the mod is "clickcrystals"
        if (this.getId().equals("clickcrystals")) {
            return true;
        }

        return false; // Return false for any other mods
    }
}
