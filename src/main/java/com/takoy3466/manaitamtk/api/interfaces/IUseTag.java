package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public interface IUseTag {

    @Nullable
    String getPath();

    default boolean isContains(CompoundTag tag) {
        if (getPath().isEmpty() || tag == null) {
            return false;
        }
        return tag.contains(getPath());
    }

    default boolean isContains(ItemStack stack) {
        return isContains(stack.getOrCreateTag());
    }

    /**
     * Tagから指定したpathのTagを取得します
     * @param tag Tag取得元のTag
     * @return Pathの指定が無ければ与えられたtagをそのまま返します
     */
    default CompoundTag getTag(CompoundTag tag) {
        if (getPath() == null || getPath().isEmpty()) {
            return tag;
        }
        if (tag.contains(getPath())) {
            return tag.getCompound(getPath());
        }
        return tag;
    }

    /**
     * Tagを取得します
     * @param stack Tagを取得したい対象のアイテム
     * @return Pathの指定が無ければアイテムにあるtagをそのまま返します
     */
    default CompoundTag getTag(ItemStack stack) {
        return getTag(stack.getOrCreateTag());
    }
}
