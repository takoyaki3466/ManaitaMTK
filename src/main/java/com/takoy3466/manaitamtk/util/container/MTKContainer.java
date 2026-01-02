package com.takoy3466.manaitamtk.util.container;

import net.minecraft.world.SimpleContainer;

public class MTKContainer extends SimpleContainer {
    private final int MAX_VALUE = 2147483647;

    public MTKContainer(int size) {
        super(size);
    }

    @Override
    public int getMaxStackSize() {
        return MAX_VALUE;
    }
}
