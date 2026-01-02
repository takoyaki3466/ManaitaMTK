package com.takoy3466.manaitamtk.apiMTK.record;

import com.takoy3466.manaitamtk.MTKEnum;
import net.minecraft.world.item.Item;

import java.util.Objects;

public record MTKRecord(Item item, MTKEnum mtkEnum) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MTKRecord that = (MTKRecord) o;
        return Objects.equals(item, that.item) && mtkEnum == that.mtkEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, mtkEnum);
    }
}
