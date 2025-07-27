package com.takoy3466.ManaitaMTK.item.armor;

import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FlyAndInvincible {
    static int modeNum = 0;
    static float modeF = 0.05F;
    static boolean canFAI;

    public static void FAI(Entity entity, Player player) {
        canFly(player);
        if (player == null || entity == null) return;
        if (player instanceof ServerPlayer sPlayer){
            if (sPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE
                    || sPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR
                    || player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()){
                canFAI = true;
            } else canFAI = false;
        }
    }

    public static void canFly(Player player){
        if (player.level().isClientSide){
            if(MTKKeyMapping.FlySpeedKey.consumeClick()){
                modeNum = modeNum < 3 ? modeNum + 1 : 0;
                switch (modeNum){
                    case 1:
                        modeF = 0.1F;
                        break;
                    case 2:
                        modeF = 0.2F;
                        break;
                    case 3:
                        modeF = 0.6F;
                        break;
                    case 0:
                    default:
                        modeF = 0.05F;
                        break;
                }
                player.displayClientMessage(Component.literal("value = " + modeF),true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event){
        if ((event.getEntity() instanceof  Player player)) {
            if (canFAI && player.getAbilities().mayfly) return;
            player.fallDistance = 0.0F;
            player.getAbilities().mayfly = canFAI;
            player.getAbilities().setFlyingSpeed(modeF);
            player.onUpdateAbilities();
        }
    }
}
