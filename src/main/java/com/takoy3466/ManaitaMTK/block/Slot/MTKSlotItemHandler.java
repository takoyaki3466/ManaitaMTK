package com.takoy3466.ManaitaMTK.block.Slot;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MTKSlotItemHandler extends SlotItemHandler {
    public int index;

    public MTKSlotItemHandler(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
        this.index = index;
    }

    @Override
    public int getMaxStackSize() {
        return 2147483647;
    }

    // Shift + F6
}
