package net.i_no_am.clickcrystals.addon.listener.events.cc;

import io.github.itzispyder.clickcrystals.events.Cancellable;
import io.github.itzispyder.clickcrystals.events.Event;

public class PlayerJoinEvent extends Event implements Cancellable {

    private boolean cancelled;

    public PlayerJoinEvent() {
        this.cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
