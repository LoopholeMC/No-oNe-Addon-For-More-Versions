package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.modules.NameChanger;
import net.i_no_am.clickcrystals.addon.utils.UUIDUtils;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkinTextures.class)
public abstract class MixinSkinTextures {
    @Inject(method = "texture", at = @At("HEAD"), cancellable = true)
    private void getSkinTextures(CallbackInfoReturnable<SkinTextures> cir) {
        if (PlayerUtils.invalid()) return;
        NameChanger nameChanger = Module.get(NameChanger.class);
        if (nameChanger == null || !nameChanger.isEnabled()) return;
        String customUserName = nameChanger.getUserName();
        if (customUserName == null || customUserName.isEmpty()) return;
        SkinTextures customSkin = DefaultSkinHelper.getSkinTextures(UUIDUtils.getUUID(customUserName));
        if (customSkin == null) return;
        cir.setReturnValue(customSkin);
        cir.cancel();
    }

}
