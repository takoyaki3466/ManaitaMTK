package com.takoy3466.manaitamtk.api.capability.interfaces;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
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

    MTKTier getMTKTier();
    void setMTKTier(MTKTier mtkTier);

    void tick(Level level, MTKTier mtkEnum);
}
