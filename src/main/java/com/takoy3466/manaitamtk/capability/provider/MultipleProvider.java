package com.takoy3466.manaitamtk.capability.provider;

import com.takoy3466.manaitamtk.capability.interfaces.IMultiple;
import net.minecraft.nbt.CompoundTag;

public class MultipleProvider implements IMultiple {
    private final String MULTIPLE = "multiple";

    private int multiple;

    @Override
    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    @Override
    public int getMultiple() {
        if (this.multiple <= 0) {
            return 1;
        }
        return this.multiple;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(MULTIPLE, this.multiple);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.multiple = tag.getInt(MULTIPLE);
    }
}
