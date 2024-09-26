package net.i_no_am.clickcrystals.addon.modules;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
import io.github.itzispyder.clickcrystals.util.minecraft.ChatUtils;
import net.i_no_am.clickcrystals.addon.client.AddonCategory;

public class SelfDestruct extends ListenerModule {
    public SelfDestruct() {
        super("self-destruct", AddonCategory.ADDON, "Make it so it will be like cc never have been installed");
    }
    @EventHandler
    private void dontUse(ClientTickEndEvent e){
        if (this.isEnabled()) ChatUtils.sendPrefixMessage("isnt working rn, turning off");
        setEnabled(false);
    }
}
