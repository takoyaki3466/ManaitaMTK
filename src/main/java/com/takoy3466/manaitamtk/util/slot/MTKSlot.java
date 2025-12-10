package com.takoy3466.manaitamtk.util.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MTKSlot extends Slot {
    private final int MAX_VALUE = 2147483647;

    public MTKSlot(Container container, int id, int x, int y) {
        super(container, x, y, y);
    }

    @Override
    public int getMaxStackSize() {
        return this.MAX_VALUE;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return this.MAX_VALUE;
    }
}
