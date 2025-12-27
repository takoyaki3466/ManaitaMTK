package com.takoy3466.manaitamtk.apiMTK;

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
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemStackKey other) || item != other.item || damage != other.damage) {
            return false;
        }
        return Objects.equals(tag, other.tag);
    }

    public ItemStack getDefaultInstance() {
        return this.item.getDefaultInstance();
    }
}
