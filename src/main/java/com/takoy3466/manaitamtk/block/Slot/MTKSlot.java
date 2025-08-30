package com.takoy3466.manaitamtk.block.Slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class MTKSlot extends Slot {
    public static final int MTK_STACK_SIZE = 2100000000;

    public MTKSlot(Container container, int x, int y, int index) {
        super(container, x, y, index);
    }
}
