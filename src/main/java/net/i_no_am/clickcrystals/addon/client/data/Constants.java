package net.i_no_am.clickcrystals.addon.client.data;

import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;

public class Constants {
    public static class URL {
        private static final String API2 = "https://i-no-one.github.io/addon/info";
        private static final String API1 = "https://no-one-s-api-default-rtdb.firebaseio.com/.json";
        public static final String DOWNLOAD = "https://discord.com/channels/1256214501129191504/1256224383639224331";
        public static final String API = NetworkUtils.isValid(API1) == null ? API2 : API1;
    }

    public static class VARS {
        public static final String MOD_ID = "no-one-addon";
    }
}
