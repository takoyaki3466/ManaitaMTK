package com.takoy3466.ManaitaMTK.armor;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

public class FlyAndInvulnerable {
    public static void FAI(Entity entity, Player player) {
        if (entity == null)
            return;
            player.getAbilities().mayfly = ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ManaitaMTKItems.HELMET_MANAITA.get());
            player.onUpdateAbilities();

            player.getAbilities().invulnerable = ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ManaitaMTKItems.HELMET_MANAITA.get());
            player.onUpdateAbilities();
    }
}
