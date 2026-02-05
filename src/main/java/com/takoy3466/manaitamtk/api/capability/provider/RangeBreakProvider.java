package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.IRangeBreak;
import com.takoy3466.manaitamtk.util.ToolUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class RangeBreakProvider implements IRangeBreak {
    private final String RANGE_BREAK = "rangeBreak";

    private int range;

    @Override
    public void rangeBreak(Level level, int x, int y, int z, LivingEntity livingEntity, int size) {
        if (!level.isClientSide()) {
            ToolUtil.RangeBreak(level, x, y, z, livingEntity, size);
        }
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public int getRange() {
        if (this.range <= 0) {
            return 1;
        }
        return this.range;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(RANGE_BREAK, this.range);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.range = tag.getInt(RANGE_BREAK);
    }
}
