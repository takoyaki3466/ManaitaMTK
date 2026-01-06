package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class AbstractBlockMultipler extends Block implements IMTKMultiple {
    protected final MTKTier mtkTier;

    public AbstractBlockMultipler(Properties properties, MTKTier mtkTier) {
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
}
