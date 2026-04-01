package com.takoy3466.manaitamtk.core.interfaces;

import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import net.minecraft.world.item.ItemStack;

public interface IMTKMultiple {

    default void multipler(ItemStack stack, int multiple) {
        multipler(stack, stack.getCount(), multiple);
    }

    default void multipler(ItemStack stack) {
        multipler(stack, stack.getCount(), getMultiple());
    }

    default void multipler(ItemStack stack, int count, int multiple) {
        stack.setCount(count * multiple);
    }

    MTKTier getMTKTier();

    int getMultiple();

    String getMTKName();
}
