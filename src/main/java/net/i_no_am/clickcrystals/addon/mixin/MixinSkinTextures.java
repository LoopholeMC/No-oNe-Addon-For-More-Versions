package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.modules.NameChanger;
import net.i_no_am.clickcrystals.addon.utils.UUIDUtils;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(SkinTextures.class)
public abstract class MixinSkinTextures {
    @Inject(method = "texture", at = @At("HEAD"), cancellable = true)
    private void getSkinTextures(CallbackInfoReturnable<SkinTextures> cir) {
        SkinTextures pSkin = DefaultSkinHelper.getSkinTextures(UUIDUtils.getUUID(Module.get(NameChanger.class).getUserName()));
        if (pSkin == null) return;
        cir.cancel();
        cir.setReturnValue(pSkin);
    }
}
