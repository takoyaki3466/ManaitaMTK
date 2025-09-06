package com.takoy3466.manaitamtk.block.Slot;

import com.takoy3466.manaitamtk.menu.MTKFurnaceMenuBase;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MTKFurnaceFuelSlot extends Slot {
    private final int MAX_VALUE = 2147483647;
    private final MTKFurnaceMenuBase menu;

    public MTKFurnaceFuelSlot(MTKFurnaceMenuBase menu, Container container, int slotId, int x, int y) {
        super(container, slotId, x, y);
        this.menu = menu;
    }

    public boolean mayPlace(ItemStack stack) {
        return this.menu.isFuel(stack) || isBucket(stack);
    }

    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : this.MAX_VALUE;
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
