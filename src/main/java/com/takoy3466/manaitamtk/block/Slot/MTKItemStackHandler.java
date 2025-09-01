package com.takoy3466.manaitamtk.block.Slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class MTKItemStackHandler extends ItemStackHandler {
    public MTKItemStackHandler(int i) {
        super(i);
    }

    @Override
    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return 2100000000;
    }
}
