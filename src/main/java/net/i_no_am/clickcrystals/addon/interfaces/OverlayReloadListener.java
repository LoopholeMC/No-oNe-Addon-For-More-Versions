package net.i_no_am.clickcrystals.addon.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface OverlayReloadListener {
    List<OverlayReloadListener> listeners = new ArrayList<>();
    void no_oNe_Addon$onOverlayReload();

    static void register(OverlayReloadListener listener) {
        listeners.add(listener);
    }

    static void callEvent() {
        for (OverlayReloadListener listener : listeners) {
            listener.no_oNe_Addon$onOverlayReload();
        }
    }
}