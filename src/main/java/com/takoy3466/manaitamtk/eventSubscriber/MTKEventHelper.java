package com.takoy3466.manaitamtk.eventSubscriber;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MTKEventHelper {

    public static void cancel(LivingEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.getItemBySlot(EquipmentSlot.HEAD).getCapability(MTKCapabilities.INVINCIBLE).ifPresent(iInvincible -> {
                if (iInvincible.isInvincible()) {
                    event.setCanceled(true);
                }
            });
        }
    }
}
