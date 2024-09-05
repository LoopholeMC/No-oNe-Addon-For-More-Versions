package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.screen.AddonScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen implements Global {
    @Inject(method = "init", at = @At("RETURN"))
    private void initGame(CallbackInfo ci){
        if (AddonManager.isBanned) {
        mc.setScreen(new AddonScreen());
        }
    }
}