package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface ITickableBlockEntity {

    void serverTick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        if (level.isClientSide()) return null;

        return (level1, pos, state, blockEntity) -> ((ITickableBlockEntity) blockEntity).serverTick();
    }
}
