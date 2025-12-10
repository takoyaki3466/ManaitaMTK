package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.ItemStackHandler;

public interface ISaveLoadMenu {

    <T extends ItemStackHandler> void saveAdditional(CompoundTag tag, T handler);

    <T extends ItemStackHandler> void load(CompoundTag tag, T handler);
}
