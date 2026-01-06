package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractItemMultipler extends Item implements IMTKMultiple {
    protected final MTKTier mtkTier;


    public AbstractItemMultipler(Properties properties, MTKTier mtkTier) {
        super(properties);
        this.mtkTier = mtkTier;
    }

    @Override
    public MTKTier getMTKTier() {
        return this.mtkTier;
    }

    @Override
    public int getMultiple() {
        return getMTKTier().getMultiple();
    }

    @Override
    public String getMTKName() {
        return getMTKTier().getName();
    }

    public void multipler(ItemStack stack) {
        this.multipler(stack, getMultiple());
    }

    @Override
    public void multipler(ItemStack stack, int multiple) {
        IMTKMultiple.super.multipler(stack, multiple);
    }
}
