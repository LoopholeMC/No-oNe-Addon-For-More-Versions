package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.module.modules.misc.SafeWalk;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinKeyBinding implements Global {

    @Inject(method = "clipAtLedge", at = @At("HEAD"), cancellable = true)
    private void onIsPressed(CallbackInfoReturnable<Boolean> cir) {
        if (PlayerUtils.invalid()) return;
        SafeWalk safeWalk = Module.get(SafeWalk.class);
        if (!safeWalk.isEnabled() || safeWalk.itemCheck.getVal() && HotbarUtils.nameContains(safeWalk.itemNames.getVal())) return;
        cir.setReturnValue(safeWalk.isEnabled());
    }
}