package com.takoy3466.manaitamtk.util.tooptip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class MTKBackPackTooltip implements TooltipComponent {
    public NonNullList<ItemStack> nonNullList;

    public MTKBackPackTooltip(NonNullList<ItemStack> stacks) {
        nonNullList = stacks;
    }

    public NonNullList<ItemStack> getNonNullList() {
        return nonNullList;
    }
}
