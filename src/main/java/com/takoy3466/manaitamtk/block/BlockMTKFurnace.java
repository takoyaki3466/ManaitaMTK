package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntity;
import com.takoy3466.manaitamtk.init.MTKTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockMTKFurnace {

    public static class Wood extends AbstractBlockMTKFurnace {
        public Wood() {
            super(MTKTiers.WOOD);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Wood(pos, state);
        }
    }

    public static class Stone extends AbstractBlockMTKFurnace {
        public Stone() {
            super(MTKTiers.STONE);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Stone(pos, state);
        }
    }

    public static class Iron extends AbstractBlockMTKFurnace {
        public Iron() {
            super(MTKTiers.IRON);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Iron(pos, state);
        }
    }

    public static class Gold extends AbstractBlockMTKFurnace {
        public Gold() {
            super(MTKTiers.GOLD);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Gold(pos, state);
        }
    }

    public static class Diamond extends AbstractBlockMTKFurnace {
        public Diamond() {
            super(MTKTiers.DIAMOND);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Diamond(pos, state);
        }
    }

    public static class MTK extends AbstractBlockMTKFurnace {
        public MTK() {
            super(MTKTiers.MTK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.MTK(pos, state);
        }
    }

    public static class GodMTK extends AbstractBlockMTKFurnace {
        public GodMTK() {
            super(MTKTiers.GODMTK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.GodMTK(pos, state);
        }
    }

    public static class Break extends AbstractBlockMTKFurnace {
        public Break() {
            super(MTKTiers.BREAK);
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MTKFurnaceBlockEntity.Break(pos, state);
        }
    }
}

