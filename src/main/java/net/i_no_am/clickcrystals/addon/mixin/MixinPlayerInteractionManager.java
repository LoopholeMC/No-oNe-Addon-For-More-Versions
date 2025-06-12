package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.module.modules.Prevent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinPlayerInteractionManager {

    @Inject(method = "interactBlock", cancellable = true, at = @At("HEAD"))
    private void onInteractBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        Prevent p = Module.get(Prevent.class);
        if (!p.isEnabled() || PlayerUtils.invalid()) return;
        ActionResult result = p.cannotPlace();
        if (result == ActionResult.FAIL) cir.setReturnValue(ActionResult.FAIL);
    }
}