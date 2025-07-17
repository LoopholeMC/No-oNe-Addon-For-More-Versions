package net.i_no_am.clickcrystals.addon.utils.network;

import net.i_no_am.clickcrystals.addon.client.data.Constants;

import java.net.URI;
import java.net.URL;

public class BetterURL {

    public static final BetterURL EMPTY = BetterURL.create(Constants.URL.EMPTY);

    private final URL url;

    public static BetterURL create(String url) {
        try {
            return new BetterURL(url);
        } catch (Exception ignore) {
            return EMPTY;
        }
    }

    private BetterURL(String url) throws Exception {
        this.url = URI.create(url).toURL();
    }

    public URL isValid() {
        try {
            int status = NetworkUtils.getResponse(url.toString()).statusCode();
            if (status == 200 || status == 201)
                return new URI(url.toString()).toURL();
        } catch (Exception ignore) {
        }
        return EMPTY.toURL();
    }

    public BetterURL orElse(String otherURL) {
        try {
            if (isValid() == EMPTY.toURL())
                return new BetterURL(otherURL);
        } catch (Exception ignore) {

        }
        return BetterURL.create(url.toString());
    }

    public URL toURL() {
        return url;
    }

    public String asString() {
        return url.toString();
    }
}