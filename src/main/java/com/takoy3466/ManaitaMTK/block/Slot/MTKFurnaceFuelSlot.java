package com.takoy3466.ManaitaMTK.block.Slot;

import com.takoy3466.ManaitaMTK.block.menu.MTKFurnaceMenuBase;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MTKFurnaceFuelSlot extends Slot {
    private final MTKFurnaceMenuBase menu;

    public MTKFurnaceFuelSlot(MTKFurnaceMenuBase menu, Container container, int slotId, int x, int y) {
        super(container, slotId, x, y);
        this.menu = menu;
    }

    public boolean mayPlace(ItemStack stack) {
        return this.menu.isFuel(stack) || isBucket(stack);
    }

    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
