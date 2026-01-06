package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IRangeBreak {

    void rangeBreak(LevelAccessor accessor, int x, int y, int z, LivingEntity livingEntity, int size);

    void setRange(int range);

    int getRange();
}
