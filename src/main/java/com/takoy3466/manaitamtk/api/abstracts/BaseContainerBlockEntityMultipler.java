package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseContainerBlockEntityMultipler extends BaseContainerBlockEntity implements IMTKMultiple {
    protected final MTKTier mtkTier;

    protected BaseContainerBlockEntityMultipler(BlockEntityType<?> type, BlockPos pos, BlockState state, MTKTier mtkTier) {
        super(type, pos, state);
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
