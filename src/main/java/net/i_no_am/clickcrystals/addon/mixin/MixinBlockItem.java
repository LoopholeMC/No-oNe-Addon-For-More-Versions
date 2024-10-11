package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.modules.Prevent;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockItem.class)
public abstract class MixinBlockItem {


    @Inject(method = "canPlace",at = @At("RETURN"), cancellable = true)
    private void canPlace(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir){
    if (!Module.get(Prevent.class).canPlace(state))
        cir.setReturnValue(false);
    }
}
