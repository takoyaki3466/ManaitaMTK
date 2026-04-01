package com.takoy3466.manaitamtk.util.slot;

import com.takoy3466.manaitamtk.core.interfaces.IFurnaceMenu;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMultiFurnaceMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MTKFurnaceFuelSlot extends Slot {
    private final int MAX_VALUE = 2147483647;
    private final AbstractContainerMenu menu;

    public MTKFurnaceFuelSlot(AbstractContainerMenu menu, Container container, int slotId, int x, int y) {
        super(container, slotId, x, y);
        this.menu = menu;
    }

    public boolean mayPlace(ItemStack stack) {
        if (menu instanceof IFurnaceMenu furnaceMenu) {
            return furnaceMenu.isFuel(stack) || isBucket(stack);
        }
        else if (menu instanceof AbstractMultiFurnaceMenu multiFurnaceMenu) {
            return multiFurnaceMenu.isFuel(stack) || isBucket(stack);
        }else {
            return false;
        }
    }

    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : this.MAX_VALUE;
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
