package com.takoy3466.manaitamtk.capability.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IMultiple extends INBTSerializable<CompoundTag> {
    void setMultiple(int multiple);
    int getMultiple();

    @Override
    void deserializeNBT(CompoundTag tag);

    @Override
    CompoundTag serializeNBT();
}
