package com.takoy3466.manaitamtk.apiMTK.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class MTKItemStackHandler extends ItemStackHandler {
    private final int MAX_VALUE = 2147483647;

    public MTKItemStackHandler(int i) {
        super(i);
    }

    @Override
    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return this.MAX_VALUE;
    }
}
