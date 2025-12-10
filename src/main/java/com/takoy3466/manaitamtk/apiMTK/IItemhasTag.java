package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.world.item.ItemStack;

public interface IItemhasTag<T> {

    T getTag(ItemStack stack);
}
