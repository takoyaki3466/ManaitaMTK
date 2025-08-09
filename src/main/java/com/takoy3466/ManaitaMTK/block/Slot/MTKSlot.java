package com.takoy3466.ManaitaMTK.block.Slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class MTKSlot extends Slot {
    public MTKSlot(Container container, int slotId, int x, int y) {
        super(container, slotId, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 2147483647;
    }
}
