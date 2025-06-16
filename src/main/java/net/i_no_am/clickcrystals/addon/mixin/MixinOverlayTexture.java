package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.listener.events.mc.OverlayTextureInitEvent;
import net.i_no_am.clickcrystals.addon.module.modules.render.HitColor;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverlayTexture.class)
public abstract class MixinOverlayTexture implements Global {

    @Shadow @Final private NativeImageBackedTexture texture;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void atInit(CallbackInfo ci) {
        var hit = Module.get(HitColor.class);
        hit.applyOverlayColor(this.texture);
        hit.resetOverlayColor(this.texture);
        OverlayTextureInitEvent event = new OverlayTextureInitEvent(this.texture);
        system.eventBus.pass(event);
    }
}