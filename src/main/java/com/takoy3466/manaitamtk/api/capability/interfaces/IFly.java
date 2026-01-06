package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IFly {

    void setCanFly(boolean isFly);
    boolean canFly();

    void setFlySpeed(float flySpeed);
    float getFlySpeed();

    void flySpeedChange(ItemStack stack, Player player);

}
