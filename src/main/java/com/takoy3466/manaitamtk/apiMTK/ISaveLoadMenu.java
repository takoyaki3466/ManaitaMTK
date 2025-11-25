package com.takoy3466.manaitamtk.apiMTK;

import com.takoy3466.manaitamtk.apiMTK.slot.MTKItemStackHandler;
import net.minecraft.nbt.CompoundTag;

public interface ISaveLoadMenu {
    void saveAdditional(CompoundTag tag, MTKItemStackHandler handler);

    void load(CompoundTag tag, MTKItemStackHandler handler);
}
