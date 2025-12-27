package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.world.SimpleContainer;

public class MTKSimpleContainer extends SimpleContainer {
    public MTKSimpleContainer(int size) {
        super(size);
    }

    @Override
    public int getMaxStackSize() {
        return 2147483647;
    }
}
