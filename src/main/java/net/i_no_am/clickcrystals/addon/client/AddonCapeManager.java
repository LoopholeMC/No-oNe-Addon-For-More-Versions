package net.i_no_am.clickcrystals.addon.client;

import com.mojang.authlib.GameProfile;
import io.github.itzispyder.clickcrystals.client.client.CapeManager;
import net.minecraft.util.Identifier;

public class AddonCapeManager extends CapeManager {

    @Override
    public void reloadTextures() {
    }

    @Override
    public Identifier getCapeTexture(GameProfile profile) {
        return null;
    }
}
