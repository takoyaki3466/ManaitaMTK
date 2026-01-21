package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMultiple extends INBTSerializable<CompoundTag> {
    void setMultiple(int multiple);
    int getMultiple();

    @Override
    void deserializeNBT(CompoundTag tag);

    @Override
    CompoundTag serializeNBT();
}
