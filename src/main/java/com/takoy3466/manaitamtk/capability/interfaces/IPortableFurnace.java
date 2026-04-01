package com.takoy3466.manaitamtk.capability.interfaces;

import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

@AutoRegisterCapability
public interface IPortableFurnace<T extends ItemStackHandler> extends INBTSerializable<CompoundTag> {

    T gethandler();

    int getLitTime();
    void setLitTime(int litTime);

    int getLitDuration();
    void setLitDuration(int litDuration);

    int getCookingProgress();
    void setCookingProgress(int cookingProgress);

    int getCookTimeTotal();
    void setCookingTotalTime(int totalTime);

    MTKTier getMTKTier();
    void setMTKTier(MTKTier mtkTier);

    void tick(Level level, MTKTier mtkTier);

    @Override
    void deserializeNBT(CompoundTag compoundTag);

    @Override
    CompoundTag serializeNBT();
}
