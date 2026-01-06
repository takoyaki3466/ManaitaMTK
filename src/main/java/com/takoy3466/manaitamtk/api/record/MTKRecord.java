package com.takoy3466.manaitamtk.api.record;

import com.takoy3466.manaitamtk.api.MTKEnum;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

/**
 * MTKBlockListでリスト化してDataGenにて使っています。
 * @param item アイテム
 * @param mtkEnum 非推奨ですが、言語表をここにまとめているため使用中。今後移動予定
 */
public record MTKRecord(RegistryObject<Item> item, MTKEnum mtkEnum) {

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
