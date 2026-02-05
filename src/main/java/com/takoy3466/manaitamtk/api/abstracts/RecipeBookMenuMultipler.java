package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.ItemStack;

public abstract class RecipeBookMenuMultipler<CONTAINER extends Container> extends RecipeBookMenu<CONTAINER> implements IMTKMultiple {
    protected final MTKTier mtkTier;
    private final int multiple;
    private final boolean isHasMTKTier;
    
    public RecipeBookMenuMultipler(MenuType<?> type, int id, MTKTier mtkTier) {
        super(type, id);
        this.mtkTier = mtkTier;
        this.multiple = 1;
        this.isHasMTKTier = true;
    }

    public RecipeBookMenuMultipler(MenuType<?> type, int id, int multiple) {
        super(type, id);
        this.mtkTier = null;
        this.multiple = multiple;
        this.isHasMTKTier = false;
    }

    @Override
    public String getMTKName() {
        if (isHasMTKTier) {
            return getMTKTier().getName();
        }else return "";
    }

    @Override
    public MTKTier getMTKTier() {
        if (isHasMTKTier) {
            return this.mtkTier;
        }else return MTKTier.DEFAULT;
    }

    @Override
    public int getMultiple() {
        if (isHasMTKTier) {
            return getMTKTier().getMultiple();
        }else return this.multiple;
    }

    public void multipler(ItemStack stack) {
        if (getMultiple() == 1) {
            return;
        }
        this.multipler(stack, getMultiple());
    }
}
