package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.module.modules.misc.UpsideDown;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class MixinLivingEntityRenderer {

    @Inject(method = "shouldFlipUpsideDown", at = @At("HEAD"), cancellable = true)
    private static void onRender(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        UpsideDown u = Module.get(UpsideDown.class);
        if (u.isEnabled())
            cir.setReturnValue(!u.shouldIgnore(entity));
    }
}
