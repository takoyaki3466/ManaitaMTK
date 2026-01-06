package com.takoy3466.manaitamtk.api.interfaces;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import net.minecraft.world.item.ItemStack;

public interface IMTKMultiple {

    default void multipler(ItemStack stack, int multiple) {
        stack.setCount(multiple);
    }

    MTKTier getMTKTier();

    int getMultiple();

    String getMTKName();
}
