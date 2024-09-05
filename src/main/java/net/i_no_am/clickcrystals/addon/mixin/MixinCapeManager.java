package net.i_no_am.clickcrystals.addon.mixin;

import com.mojang.authlib.GameProfile;
import io.github.itzispyder.clickcrystals.client.client.CapeManager;
import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.modules.CapeDisabler;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CapeManager.class)
public class MixinCapeManager {
	@Inject(at = @At("HEAD"), method = "getCapeTexture", cancellable = true)
	private void loadCape(GameProfile profile, CallbackInfoReturnable<Identifier> cir) {
		if (Module.isEnabled(CapeDisabler.class)) {
			cir.setReturnValue(null);
		}
	}
}