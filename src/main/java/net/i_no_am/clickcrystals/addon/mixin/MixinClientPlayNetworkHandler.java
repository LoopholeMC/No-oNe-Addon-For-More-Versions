package net.i_no_am.clickcrystals.addon.mixin;

import io.github.itzispyder.clickcrystals.Global;
import net.i_no_am.clickcrystals.addon.utils.NetworkUtils;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler implements Global {
    @Shadow public abstract void sendChatMessage(String content);

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void sendChatMsg(String content, CallbackInfo ci) {
        // Check if you want to override the content or send a specific message
        if (shouldInterceptMessage(content)) {
            // Extract the player's name from the content or any relevant information
            String playerName = extractPlayerName(content);
            String replyMessage = "Your custom message here"; // Customize the message as needed

            sendChatMessage(replyMessage); // Sends the message directly

            ci.cancel(); // Prevent the original message from being sent
        }
    }

    private boolean shouldInterceptMessage(String content) {
        // Implement your logic to decide if the message should be intercepted
        return content.contains("some condition"); // Adjust condition accordingly
    }

    private String extractPlayerName(String content) {
        // Implement logic to extract player name from content if needed
        // This could be based on the format of the message
        return NetworkUtils.findPlayerFromMessage(content);
    }
}
