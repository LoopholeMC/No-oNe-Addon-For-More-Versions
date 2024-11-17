package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import io.github.itzispyder.clickcrystals.events.events.networking.PacketReceiveEvent;
import io.github.itzispyder.clickcrystals.events.listeners.NetworkEventListener;
import net.i_no_am.clickcrystals.addon.listener.events.cc.PlayerJoinEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkEventListener.class)
public class MixinNetworkEventListener implements Global {
    @Inject(remap = false, method = "handPlayerJoin", at = @At(value = "INVOKE", target = "Lio/github/itzispyder/clickcrystals/client/system/ClickCrystalsInfo;getStaff(Ljava/util/UUID;)Lio/github/itzispyder/clickcrystals/client/system/ClickCrystalsInfo$User;"), cancellable = true)
    private void cancelStaffMessage(PacketReceiveEvent e, CallbackInfo ci) {
        system.eventBus.passWithCallbackInfo(ci, new PlayerJoinEvent());
    }

    @Inject(remap = false, method = "handPlayerJoin", at = @At(value = "INVOKE", target = "Lio/github/itzispyder/clickcrystals/client/system/ClickCrystalsInfo;getOwner(Ljava/util/UUID;)Lio/github/itzispyder/clickcrystals/client/system/ClickCrystalsInfo$User;"), cancellable = true)
    private void cancelOwnerMessage(PacketReceiveEvent e, CallbackInfo ci) {
        system.eventBus.passWithCallbackInfo(ci, new PlayerJoinEvent());
    }
}