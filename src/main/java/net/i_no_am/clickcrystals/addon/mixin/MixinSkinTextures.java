package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.module.modules.NameChanger;
import net.i_no_am.clickcrystals.addon.utils.UUIDUtils;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkinTextures.class)
public abstract class MixinSkinTextures {

    @Inject(method = "texture", at = @At("HEAD"), cancellable = true)
    private void getSkinTextures(CallbackInfoReturnable<Identifier> cir) {
        // Ensure the player is valid before proceeding
        if (PlayerUtils.invalid()) {
            return;
        }

        // Retrieve the NameChanger module
        NameChanger nameChanger = Module.get(NameChanger.class);
        if (nameChanger == null || nameChanger.getUserName() == null) {
            return; // Ensure the username is valid
        }

        // Get the UUID from the NameChanger and fetch the custom skin textures
        String userName = nameChanger.getUserName();
        var uuid = UUIDUtils.getUUID(userName);
        if (uuid == null) {
            return; // Ensure the UUID is valid
        }

        SkinTextures customSkin = DefaultSkinHelper.getSkinTextures(uuid);

        if (customSkin != null && customSkin.texture() != null) {
            // If we have a custom skin, return it and cancel the default texture loading
            cir.setReturnValue(customSkin.texture());
            cir.cancel();
        }
    }
}
