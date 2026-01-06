package com.takoy3466.manaitamtk.api.record;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;

public record ItemStackKey(Item item, int damage, @Nullable CompoundTag tag) {

    public static ItemStackKey of(ItemStack stack) {
        return new ItemStackKey(
                stack.getItem(),
                stack.getDamageValue(),
                stack.hasTag() ? stack.getTag().copy() : null
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStackKey that)) return false;
        return damage == that.damage && Objects.equals(item, that.item) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, damage, tag);
    }

    public ItemStack getDefaultInstance() {
        return this.item.getDefaultInstance();
    }
}
