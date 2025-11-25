package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.world.item.ItemStack;

public class ItemFlag {
    boolean flag;
    ItemStack stack;
    public ItemFlag(boolean flag, ItemStack stack) {
        this.flag = flag;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }
}
