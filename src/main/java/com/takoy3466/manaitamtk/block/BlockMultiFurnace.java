package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.block.abstracts.AbstractBlockMultiFurnace;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.init.MTKTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockMultiFurnace {
    public static class Wood extends AbstractBlockMultiFurnace {
        public Wood() {
            super(MTKTiers.WOOD);
        }
        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            System.out.println(this.getClass().getName() + " create BlockEntity");
            return BlockEntitiesInit.WOOD_MULTI_FURNACE.get().create(pos, state);
        }
    }

    public static class Stone extends AbstractBlockMultiFurnace {
        public Stone() {
            super(MTKTiers.STONE);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.STONE_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class Iron extends AbstractBlockMultiFurnace {
        public Iron() {
            super(MTKTiers.IRON);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.IRON_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class Gold extends AbstractBlockMultiFurnace {
        public Gold() {
            super(MTKTiers.GOLD);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.GOLD_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class Diamond extends AbstractBlockMultiFurnace {
        public Diamond() {
            super(MTKTiers.DIAMOND);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.DIAMOND_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class MTK extends AbstractBlockMultiFurnace {
        public MTK() {
            super(MTKTiers.MTK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.MTK_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class GodMTK extends AbstractBlockMultiFurnace {
        public GodMTK() {
            super(MTKTiers.GODMTK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.GODMTK_MULTI_FURNACE.get().create(pos, state);
        }
    }
    public static class Break extends AbstractBlockMultiFurnace {
        public Break() {
            super(MTKTiers.BREAK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return BlockEntitiesInit.BREAK_MULTI_FURNACE.get().create(pos, state);
        }
    }
}
