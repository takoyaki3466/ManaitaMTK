package com.takoy3466.manaitamtk.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MTKMenuHelper {
    private static final int MAX_VALUE = 2147483647;

    public static boolean moveItemStackTo(NonNullList<Slot> slots, ItemStack stack, int i1, int i2, boolean b) {
        boolean flag = false;
        int i = i1;
        if (b) {
            i = i2 - 1;
        }

        Slot slot1;
        ItemStack itemstack;
        if (stack.isStackable()) {
            while(!stack.isEmpty()) {
                if (b) {
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;
                }

                slot1 = slots.get(i);
                itemstack = slot1.getItem();
                if (!itemstack.isEmpty() && ItemStack.isSameItemSameTags(stack, itemstack)) {
                    long itemStackCount = itemstack.getCount();
                    long stackCount = stack.getCount();

                    long j = itemStackCount + stackCount;
                    int maxSize = MAX_VALUE;
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount((int) j);
                        slot1.setChanged();
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot1.setChanged();
                        flag = true;
                    }
                }

                if (b) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (b) {
                i = i2 - 1;
            } else {
                i = i1;
            }

            while(true) {
                if (b) {
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;
                }

                slot1 = slots.get(i);
                itemstack = slot1.getItem();
                if (itemstack.isEmpty() && slot1.mayPlace(stack)) {
                    if (stack.getCount() > slot1.getMaxStackSize()) {
                        slot1.setByPlayer(stack.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.setByPlayer(stack.split(stack.getCount()));
                    }

                    slot1.setChanged();
                    flag = true;
                    break;
                }

                if (b) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
    }

}
