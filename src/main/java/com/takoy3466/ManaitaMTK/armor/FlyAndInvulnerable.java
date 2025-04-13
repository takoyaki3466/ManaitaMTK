package com.takoy3466.ManaitaMTK.armor;

import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class FlyAndInvulnerable {
    static int modeNum = 0;
    static float modeF;
    static boolean iBoolean;

    public static void FAI(Entity entity, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()){
            iBoolean = true;
        }
        else iBoolean = false;


        if (entity == null) {
            return;
        }
        if(MTKKeyMapping.FlySpeedKey.consumeClick()){
            modeChange(player);
        }
        player.fallDistance = 0.0F;
        player.getAbilities().mayfly = iBoolean;
        player.getAbilities().setFlyingSpeed(modeF);
        player.onUpdateAbilities();
    }

    private static void modeChange(Player player){
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
