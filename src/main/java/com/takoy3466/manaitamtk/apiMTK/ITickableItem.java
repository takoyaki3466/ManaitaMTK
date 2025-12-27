package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ITickableItem {

    void tick(ItemStack stack, Level level);
}
