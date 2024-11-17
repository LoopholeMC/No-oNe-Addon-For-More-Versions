package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.minecraft.HotbarUtils;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.modules.SafeWalk;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public abstract class MixinKeyBinding implements Global {

    @Shadow
    public abstract boolean equals(KeyBinding other);

    @Shadow
    public abstract boolean isPressed();

    @Inject(method = "isPressed", at = @At("HEAD"), cancellable = true)
    private void onIsPressed(CallbackInfoReturnable<Boolean> cir) {
        SafeWalk safeWalk = Module.get(SafeWalk.class);
        if (safeWalk.isEnabled() && this.equals(mc.options.sneakKey) && PlayerUtils.valid() && PlayerUtils.player().isOnGround() && PlayerUtils.player().getWorld().getBlockState(new BlockPos((int) Math.floor(PlayerUtils.getPos().getX()), (int) Math.floor(PlayerUtils.getPos().getY()) - 1, (int) Math.floor(PlayerUtils.getPos().getZ()))).isAir()) {
            if (safeWalk.itemCheck.getVal() && !safeWalk.itemNames.getVal().isEmpty()) {
                if (HotbarUtils.nameContains(safeWalk.itemNames.getVal()) || HotbarUtils.nameContains(safeWalk.itemNames.getVal(), Hand.OFF_HAND)) {
                    cir.setReturnValue(true);
                }
            } else {
                cir.setReturnValue(true);
            }
        }
    }
}