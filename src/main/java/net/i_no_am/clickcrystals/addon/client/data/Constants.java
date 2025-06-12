package net.i_no_am.clickcrystals.addon.client.data;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public String MOD_ID = "no-one-addon";
    public final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public boolean isNoOne = false;
    public boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment() && isNoOne;
    }
}
