package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import com.takoy3466.manaitamtk.api.interfaces.ITickableBlockEntity;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFurnaceMultipler extends AbstractFurnaceBlock implements IMTKMultiple {
    protected final MTKTier mtkTier;


    public AbstractFurnaceMultipler(Properties properties, MTKTier mtkTier) {
        super(properties);
        this.mtkTier = mtkTier;
    }

    public MenuProvider createMenuProvider(BlockPos pos, BlockState state) {
        if (newBlockEntity(pos, state) instanceof MenuProvider provider) {
            return provider;
        }
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return ITickableBlockEntity.getTickerHelper(level);
        }
        return null;
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
