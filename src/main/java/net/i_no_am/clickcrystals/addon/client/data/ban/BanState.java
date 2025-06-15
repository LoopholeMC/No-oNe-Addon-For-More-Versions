package net.i_no_am.clickcrystals.addon.client.data.ban;

public enum BanState {
    BAN,
    ALLOWED,
    I_NO_AM,
    FAILED;

    public boolean shouldDisplay() {
        return this != ALLOWED && this != I_NO_AM;
    }
}
