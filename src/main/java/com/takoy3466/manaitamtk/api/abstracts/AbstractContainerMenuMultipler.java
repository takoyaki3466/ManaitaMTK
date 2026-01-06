package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractContainerMenuMultipler extends AbstractContainerMenu implements IMTKMultiple {
    protected final MTKTier mtkTier;

    protected AbstractContainerMenuMultipler(@Nullable MenuType<?> type, int id, MTKTier mtkTier) {
        super(type, id);
        this.mtkTier = mtkTier;
    }

    @Override
    public String getMTKName() {
        return getMTKTier().getName();
    }

    @Override
    public MTKTier getMTKTier() {
        return this.mtkTier;
    }

    @Override
    public int getMultiple() {
        return getMTKTier().getMultiple();
    }

    public void multipler(ItemStack stack) {
        this.multipler(stack, getMultiple());
    }
}
