package com.takoy3466.manaitamtk.api.interfaces;

import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;

public interface IMultipleRecipeResult {

    default boolean multipleMatche(CraftingContainer container) {
        boolean source = false;
        boolean item = false;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == ItemsInit.CRUSHED_MTK.get()) {
                    if (!source) {source = true;}
                    else {
                        if (item) {return false;}
                        item = true;
                    }
                } else {
                    if (item) {return false;}
                    item = true;
                }
            }
        }
        return source && item;
    }

    default ItemStack multipleAssemble(CraftingContainer container, int magnification) {
        ItemStack empty = ItemStack.EMPTY;
        int source = 0;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && stack.getItem() != ItemsInit.CRUSHED_MTK.get()) {
                empty = stack;
            }

            if (!stack.isEmpty() && stack.getItem() == ItemsInit.CRUSHED_MTK.get()) {
                ++source;
            }
        }

        ItemStack result;
        if (source == 2) {
            result = new ItemStack(ItemsInit.CRUSHED_MTK.get());
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get() * magnification);
            return result;

        } else if (empty.isEmpty()) {
            return ItemStack.EMPTY;

        } else {
            result = empty.copy();
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get() * magnification);
            return result;

        }
    }
}
