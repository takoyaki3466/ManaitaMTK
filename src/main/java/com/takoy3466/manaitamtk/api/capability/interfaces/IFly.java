package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IFly {

    void setCanFly(boolean isFly);
    boolean canFly();

    void setFlySpeed(float flySpeed);
    float getFlySpeed();
}
