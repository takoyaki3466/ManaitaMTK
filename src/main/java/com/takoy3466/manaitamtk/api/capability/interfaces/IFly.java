package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IFly extends INBTSerializable<CompoundTag> {

    void setCanFly(boolean isFly);
    boolean canFly();

    void setFlySpeed(float flySpeed);
    float getFlySpeed();

    @Override
    void deserializeNBT(CompoundTag tag);

    @Override
    CompoundTag serializeNBT();
}
