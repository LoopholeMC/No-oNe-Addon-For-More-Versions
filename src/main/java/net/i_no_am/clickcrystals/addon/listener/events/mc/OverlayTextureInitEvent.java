package net.i_no_am.clickcrystals.addon.listener.events.mc;

import io.github.itzispyder.clickcrystals.events.Event;
import net.minecraft.client.texture.NativeImageBackedTexture;

public class OverlayTextureInitEvent extends Event {

    private final NativeImageBackedTexture imageBackedTexture;

    public OverlayTextureInitEvent(NativeImageBackedTexture imageBackedTexture){
        this.imageBackedTexture = imageBackedTexture;
    }

    public NativeImageBackedTexture getImageBackedTexture() {
        return imageBackedTexture;
    }
}
