package net.i_no_am.clickcrystals.addon.client;

import net.i_no_am.clickcrystals.addon.client.data.AddonVersion;
import net.i_no_am.clickcrystals.addon.client.data.BanData;
import net.i_no_am.clickcrystals.addon.client.data.Constants;
import net.i_no_am.clickcrystals.addon.client.data.ModuleManager;

public interface Manager {

    @Deprecated(forRemoval = true)
    Constants constants = new Constants();

    AddonVersion version = new AddonVersion();

    ModuleManager modules = new ModuleManager();

    BanData banData = new BanData();

}