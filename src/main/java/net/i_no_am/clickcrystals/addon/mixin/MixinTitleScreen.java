package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import net.fabricmc.loader.api.FabricLoader;
import net.i_no_am.clickcrystals.addon.AddonManager;
import net.i_no_am.clickcrystals.addon.screen.AddonScreen;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.URI;

@Mixin(TitleScreen.class)
public class MixinTitleScreen implements Global {
    @Inject(method = "init", at = @At("RETURN"))
    private void initGame(CallbackInfo ci){
        if (AddonManager.isBanned && !FabricLoader.getInstance().isDevelopmentEnvironment()) {
        mc.setScreen(new AddonScreen());
        } else if (!NetworkUtils.isUpdated() && !FabricLoader.getInstance().isDevelopmentEnvironment()) {
            mc.setScreen(new ConfirmScreen(
                    confirm -> {
                        if (confirm)
                            Util.getOperatingSystem().open(URI.create("https://discord.com/channels/1256214501129191504/1256224383639224331"));
                        else mc.stop();
                    },
                    Text.of(Formatting.DARK_RED + "You are using an outdated version of " + Formatting.GREEN + "No one's Addon"), Text.of("Please download the latest version from " + Formatting.DARK_PURPLE + "Discord"), Text.of("Download"), Text.of("Quit Game")));
        }
    }
}