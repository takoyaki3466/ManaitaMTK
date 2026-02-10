package com.takoy3466.manaitamtk.api.helper;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MTKMenuHelper {
    private static final int MAX_VALUE = 2147483647;

    /**
     * 64個以上のアイテムを扱う時向けのmoveItemStackToです。
     * @param slots Menuクラスで追加したslotたち。
     * @param stack 移動させたいアイテム。
     * @param slotIdStart 移動先のスロットの始まり。
     * @param slotIdEnd 移動先のスロットの終わり、数値自身は含まない。ex) 39を入れた場合38までの参照となる。
     * @param direction スロットを探す順番を逆にするか否か。ex) trueの場合は slotIdEnd -> slotIdStart, falseの場合は slotIdStart -> slotIdEnd。
     * @return アイテムが対象の場所に移動したか。(1個でも移動すればtrueになる)
     */
    public static boolean moveItemStackTo(NonNullList<Slot> slots, ItemStack stack, int slotIdStart, int slotIdEnd, boolean direction) {
        boolean flag = false;
        int i = slotIdStart;
        if (direction) {
            i = slotIdEnd - 1;
        }

        Slot slot1;
        ItemStack itemstack;
        if (stack.isStackable()) {
            while(!stack.isEmpty()) {
                if (direction) {
                    if (i < slotIdStart) {
                        break;
                    }
                } else if (i >= slotIdEnd) {
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

                if (direction) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (direction) {
                i = slotIdEnd - 1;
            } else {
                i = slotIdStart;
            }

            while(true) {
                if (direction) {
                    if (i < slotIdStart) {
                        break;
                    }
                } else if (i >= slotIdEnd) {
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

                if (direction) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
    }
}
