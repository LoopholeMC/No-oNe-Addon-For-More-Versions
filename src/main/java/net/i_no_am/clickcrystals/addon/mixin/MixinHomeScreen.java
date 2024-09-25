package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.gui.screens.HomeScreen;
import io.github.itzispyder.clickcrystals.modules.Module;
import net.i_no_am.clickcrystals.addon.modules.DiscordScreenDisabler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HomeScreen.class)
public class MixinHomeScreen implements Global {


    @Shadow public static boolean OPENED_BEFORE;

    @Inject(remap = false ,method = "mouseClicked", at = @At("HEAD"))
    private void disableDiscordScreen(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        if (Module.get(DiscordScreenDisabler.class).isEnabled()) OPENED_BEFORE = true;
    }
}