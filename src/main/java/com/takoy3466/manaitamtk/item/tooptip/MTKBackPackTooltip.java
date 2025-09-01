package com.takoy3466.manaitamtk.item.tooptip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class MTKBackPackTooltip implements TooltipComponent {
    public static NonNullList<ItemStack> nonNullList;

    public MTKBackPackTooltip(NonNullList<ItemStack> stacks) {
        nonNullList = stacks;
    }

    public static void updateNonNullList(NonNullList<ItemStack> stacks) {
        for (int i = 0; i < nonNullList.size(); i++) {
            nonNullList.set(i, i < stacks.size() ? stacks.get(i) : ItemStack.EMPTY);
        }
    }

    public NonNullList<ItemStack> getNonNullList() {
        return nonNullList;
    }

    public static NonNullList<ItemStack> getNonNullListStatic() {
        return nonNullList;
    }
}
