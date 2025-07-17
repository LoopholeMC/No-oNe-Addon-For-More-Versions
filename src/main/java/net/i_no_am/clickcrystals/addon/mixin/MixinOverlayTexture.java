package net.i_no_am.clickcrystals.addon.mixin;

import net.i_no_am.clickcrystals.addon.accessor.OverlayTextureAccessor;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OverlayTexture.class)
public class MixinOverlayTexture implements OverlayTextureAccessor {

    @Shadow @Final private NativeImageBackedTexture texture;

    @Override
    public NativeImageBackedTexture addon$getTexture() {
        return this.texture;
    }
}