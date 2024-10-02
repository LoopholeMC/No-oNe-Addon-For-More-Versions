//package net.i_no_am.clickcrystals.addon.modules;
//
//import io.github.itzispyder.clickcrystals.events.EventHandler;
//import io.github.itzispyder.clickcrystals.events.events.world.ClientTickEndEvent;
//import io.github.itzispyder.clickcrystals.events.events.world.ItemUseEvent;
//import io.github.itzispyder.clickcrystals.modules.modules.ListenerModule;
//import io.github.itzispyder.clickcrystals.util.minecraft.BlockUtils;
//import io.github.itzispyder.clickcrystals.util.minecraft.PlayerUtils;
//import net.i_no_am.clickcrystals.addon.client.AddonCategory;
//import net.minecraft.block.Blocks;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.decoration.EndCrystalEntity;
//import net.minecraft.entity.mob.SlimeEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.hit.EntityHitResult;
//import net.minecraft.util.hit.HitResult;
//import net.minecraft.util.math.BlockPos;
//import org.lwjgl.glfw.GLFW;
//
//public class AutoDTap extends ListenerModule {
//    public AutoDTap() {
//        super("Auto Dtap", AddonCategory.ADDON, "Auto Dtap from francium.");
//    }
//
//
//    @Override
//    public void onEnable() {
//        super.onEnable();
//        crystalPlaceClock = 0;
//        crystalBreakClock = 0;
//    }
//
//
//    private boolean isDeadBodyNearby() {
//        return mc.world.getPlayers().parallelStream()
//                .filter(e -> mc.player != e)
//                .filter(e -> e.squaredDistanceTo(mc.player) < 36)
//                .anyMatch(LivingEntity::isDead);
//    }
//
//    @EventHandler
//    public void onPlayerTick(ClientTickEndEvent e) {
//        boolean dontPlaceCrystal = crystalPlaceClock != 0;
//        boolean dontBreakCrystal = crystalBreakClock != 0;
//        if (dontPlaceCrystal)
//            crystalPlaceClock--;
//        if (dontBreakCrystal)
//            crystalBreakClock--;
//        if (activateOnRightClick.get() && GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_2) != GLFW.GLFW_PRESS)
//            return;
//        ItemStack mainHandStack = mc.player.getMainHandStack();
//        if (!mainHandStack.isOf(Items.END_CRYSTAL))
//            return;
//        if (stopOnKill.get() && isDeadBodyNearby())
//            return;
//
//        if (mc.crosshairTarget instanceof EntityHitResult hit) {
//            if (!dontBreakCrystal && (hit.getEntity() instanceof EndCrystalEntity || hit.getEntity() instanceof SlimeEntity)) {
//                crystalBreakClock = breakInterval.get();
//                mc.interactionManager.attackEntity(mc.player, hit.getEntity());
//                mc.player.swingHand(Hand.MAIN_HAND);
//                Client.INSTANCE.crystalDataTracker().recordAttack(hit.getEntity());
//            }
//        }
//        if (mc.crosshairTarget instanceof BlockHitResult hit) {
//            BlockPos block = hit.getBlockPos();
//            if (!dontPlaceCrystal && CrU.canPlaceCrystalServer(block)) {
//                crystalPlaceClock = placeInterval.get();
//                ActionResult result = mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hit);
//                if (result.isAccepted() && result.shouldSwingHand())
//                    mc.player.swingHand(Hand.MAIN_HAND);
//            }
//        }
//    }
//
//    @EventHandler
//    public void onItemUse(ItemUseEvent event) {
//        ItemStack mainHandStack = PlayerUtils.player().getMainHandStack();
//        if (mc.crosshairTarget.getType() == HitResult.Type.BLOCK) {
//            BlockHitResult hit = (BlockHitResult) mc.crosshairTarget;
//            if (mainHandStack.isOf(Items.END_CRYSTAL) && BlockUtils.matchTargetBlock(Blocks.OBSIDIAN){
//                event.cancel();
//        }
//    }
//}
//}
