package com.takoy3466.manaitamtk.util.slot;

import com.takoy3466.manaitamtk.menu.PortableFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MTKFurnaceFuelHandler extends SlotItemHandler {
    private final int MAX_VALUE = 2147483647;
    private final PortableFurnaceMenu menu;

    public MTKFurnaceFuelHandler(PortableFurnaceMenu menu, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
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
