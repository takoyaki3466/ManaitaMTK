package com.takoy3466.manaitamtk.apiMTK.interfaces;

import com.takoy3466.manaitamtk.apiMTK.helper.MTKContainerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.items.ItemStackHandler;

public interface ISaveLoad {

    default <T extends ItemStackHandler> void saveAdditional(CompoundTag tag, T handler) {
        MTKContainerHelper.saveHandler(tag, handler);
    }

    default <T extends ItemStackHandler> void load(CompoundTag tag, T handler) {
        MTKContainerHelper.loadHandler(tag, handler);
    }
}
