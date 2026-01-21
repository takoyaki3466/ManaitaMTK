package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IRangeBreak extends INBTSerializable<CompoundTag> {

    void rangeBreak(Level level, int x, int y, int z, LivingEntity livingEntity, int size);

    void setRange(int range);

    int getRange();

    @Override
    void deserializeNBT(CompoundTag tag);

    @Override
    CompoundTag serializeNBT();
}
