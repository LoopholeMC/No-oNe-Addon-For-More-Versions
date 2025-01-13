package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.data.Config;
import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
import net.i_no_am.clickcrystals.addon.interfaces.IData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mixin(value = Config.class,remap = false)
public class MixinConfig {

    @Shadow @Final public static String PATH;

    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void changeFolder(CallbackInfo ci) {
        if (PlayerUtils.invalid()) return;
        IData iData = (IData) (Object) this;
        String customPath = iData.getGhostPath();
        try {
            Field pathField = Config.class.getDeclaredField("PATH");
            pathField.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(pathField, pathField.getModifiers() & ~Modifier.FINAL);
            pathField.set(null, customPath != null ? customPath : PATH);
        } catch (Exception e) {
            System.err.printf(e.getMessage());
        }
    }
}
