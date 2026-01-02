package com.takoy3466.manaitamtk.apiMTK.interfaces;

import net.minecraft.world.item.ItemStack;

public interface IItemhasTag<T> {

    T getTag(ItemStack stack);
}
