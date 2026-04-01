package com.takoy3466.manaitamtk.util.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class MTKItemStackHandler extends ItemStackHandler {
    private final int MAX_VALUE = 2147483647;

    public MTKItemStackHandler(int i) {
        super(i);
    }

    @Override
    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return this.MAX_VALUE;
    }

    public static MTKItemStackHandler withSize(int size, ItemStack defaultStack) {
        MTKItemStackHandler handler = new MTKItemStackHandler(size);
        handler.setAll(defaultStack);
        return handler;
    }

    public void setAll(ItemStack stack) {
        for (int i = 0; i < this.getSlots(); i++) {
            this.setStackInSlot(i, stack);
        }
    }

    public Iterator<ItemStack> iterator() {
        return this.stacks.iterator();
    }

    public ItemStack set(int slotId, ItemStack stack) {
        return this.stacks.set(slotId, stack);
    }

    public void clear() {
        this.stacks.clear();
    }
}
