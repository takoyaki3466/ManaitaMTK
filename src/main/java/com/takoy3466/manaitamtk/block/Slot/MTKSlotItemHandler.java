package com.takoy3466.manaitamtk.block.Slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class MTKSlotItemHandler extends SlotItemHandler {
    private final int MAX_VALUE = 2147483647;
    public int index;

    public MTKSlotItemHandler(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
        this.index = index;
    }

    @Override
    public int getMaxStackSize() {
        return this.MAX_VALUE;
    }
    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        return this.MAX_VALUE;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
