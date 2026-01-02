package com.takoy3466.manaitamtk.apiMTK.capability.interfaces;

import com.takoy3466.manaitamtk.MTKEnum;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.items.ItemStackHandler;

@AutoRegisterCapability
public interface IPortableFurnace<T extends ItemStackHandler> {

    T gethandler();

    int getLitTime();
    void setLitTime(int litTime);

    int getLitDuration();
    void setLitDuration(int litDuration);

    int getCookingProgress();
    void setCookingProgress(int cookingProgress);

    int getCookTimeTotal();
    void setCookingTotalTime(int totalTime);

    MTKEnum getMTKEnum();
    void setMTKEnum(MTKEnum mtkEnum);

    void tick(Level level, MTKEnum mtkEnum);
}
