package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.init.MTKTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MTKFurnaceBlockEntity {
    public static class Wood extends AbstractMTKFurnaceBlockEntity {
        public Wood(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_WOOD.get(), pos, state, MTKTiers.WOOD);
        }
    }

    public static class Stone extends AbstractMTKFurnaceBlockEntity {
        public Stone(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_STONE.get(), pos, state, MTKTiers.STONE);
        }
    }

    public static class Iron extends AbstractMTKFurnaceBlockEntity {
        public Iron(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_IRON.get(), pos, state, MTKTiers.IRON);
        }
    }

    public static class Gold extends AbstractMTKFurnaceBlockEntity {
        public Gold(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_GOLD.get(), pos, state, MTKTiers.GOLD);
        }
    }

    public static class Diamond extends AbstractMTKFurnaceBlockEntity {
        public Diamond(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_DIAMOND.get(), pos, state, MTKTiers.DIAMOND);
        }
    }

    public static class MTK extends AbstractMTKFurnaceBlockEntity {
        public MTK(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_MTK.get(), pos, state, MTKTiers.MTK);
        }
    }

    public static class GodMTK extends AbstractMTKFurnaceBlockEntity {
        public GodMTK(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_GODMTK.get(), pos, state, MTKTiers.GODMTK);
        }
    }

    public static class Break extends AbstractMTKFurnaceBlockEntity {
        public Break(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_BREAK.get(), pos, state, MTKTiers.BREAK);
        }
    }
}
