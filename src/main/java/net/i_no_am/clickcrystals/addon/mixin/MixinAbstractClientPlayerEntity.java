package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.modules.CapeDisabler;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.SkinTextures;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class MixinAbstractClientPlayerEntity implements Global {

    @Shadow @Nullable protected abstract PlayerListEntry getPlayerListEntry();

    @Inject(method = "getSkinTextures", at = @At("RETURN"), cancellable = true)
    public void getCapeTexture(CallbackInfoReturnable<SkinTextures> cir) {

        CapeDisabler capeDisabler = Module.get(CapeDisabler.class);

        if (capeDisabler != null && capeDisabler.isEnabled() & capeDisabler.disableClickCrystalCapes.getVal()) {

            PlayerListEntry p = getPlayerListEntry();

            if (p == null) return;

            SkinTextures s = cir.getReturnValue();
            cir.setReturnValue(new SkinTextures(s.texture(), s.textureUrl(), s.capeTexture(), s.elytraTexture(), s.model(), s.secure()));
        }
    }
}