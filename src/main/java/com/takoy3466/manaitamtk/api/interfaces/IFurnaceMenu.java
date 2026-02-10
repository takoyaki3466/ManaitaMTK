package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.world.item.ItemStack;

public interface IFurnaceMenu {

    boolean canSmelt(ItemStack stack);


    boolean isFuel(ItemStack stack);

    boolean isLit();

    int getBurnProgress();

    int getLitProgress();
}
