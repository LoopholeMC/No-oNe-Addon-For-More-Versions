package net.i_no_am.clickcrystals.addon.client.data;

import net.i_no_am.clickcrystals.addon.utils.network.BetterURL;

public class Constants {
    public static class URL {
        private static final String API2 = "https://i-no-one.github.io/addon/info";
        private static final String API1 = "https://no-one-s-api-default-rtdb.firebaseio.com/.json";
        public static final String DOWNLOAD = "https://discord.com/channels/1256214501129191504/1256224383639224331";
        public static final String SITE = "https://i-no-one.github.io/addon";
        public static final String EMPTY = "https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://www.youtube.com/watch%3Fv%3DdQw4w9WgXcQ&ved=2ahUKEwj_0MXzlMSOAxX0LPsDHUu1IW4QtwJ6BAgOEAI&usg=AOvVaw0aHtehaphMhOCAkCydRLZU";
        public static final String API = BetterURL.create(API1).orElse(API2).asString();
    }

    public static class VARS {
        public static final String MOD_ID = "no-one-addon";
    }
}
