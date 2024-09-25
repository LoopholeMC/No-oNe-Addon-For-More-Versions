package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.modules.modules.DummyModule;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class DiscordScreenDisabler extends DummyModule {
    public DiscordScreenDisabler() {
        super("discord-screen-disabler", AddonCategory.ADDON, "Cancel the annoying join discord sever screen.");
    }
}