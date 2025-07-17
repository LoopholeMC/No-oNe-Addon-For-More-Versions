package net.i_no_am.clickcrystals.addon.client.data.ban;

public enum BanState {
    BAN,
    ALLOWED,
    I_NO_AM,
    FAILED;

    /***
     * @apiNote returns false because the addon is public.
     */
    public boolean shouldDisplay() {
//        return this != ALLOWED && this != I_NO_AM;
    return false;
    }
}
