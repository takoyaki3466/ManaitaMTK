package com.takoy3466.manaitamtk.core.registry;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class MTKBlockEntityType {

    public static final class Builder<T extends BlockEntity> {
        private final MTKBlockEntityType.BlockEntitySupplier<? extends T> factory;
        final Set<Block> validBlocks;

        private Builder(MTKBlockEntityType.BlockEntitySupplier<? extends T> supplier, Set<Block> blocks) {
            this.factory = supplier;
            this.validBlocks = blocks;
        }

        public static <T extends BlockEntity> MTKBlockEntityType.Builder<T> of(MTKBlockEntityType.BlockEntitySupplier<? extends T> supplier, Block... blocks) {
            return new MTKBlockEntityType.Builder(supplier, ImmutableSet.copyOf(blocks));
        }

        public BlockEntityType<T> build(MTKTier mtkTier, Type<?> type) {
            return new BlockEntityType<>((pos, state) -> factory.create(pos, state, mtkTier), this.validBlocks, type);
        }
    }

    @FunctionalInterface
    public interface BlockEntitySupplier<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state, MTKTier mtkTier);
    }
}
