package com.takoy3466.manaitamtk.api.interfaces;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import net.minecraft.world.item.ItemStack;

public interface IMTKMultiple {

    default void multipler(ItemStack stack, int multiple) {
        multipler(stack, stack.getCount(), multiple);
    }

    default void multipler(ItemStack stack, int count, int multiple) {
        stack.setCount(count * multiple);
    }

    MTKTier getMTKTier();

    int getMultiple();

    String getMTKName();
}
