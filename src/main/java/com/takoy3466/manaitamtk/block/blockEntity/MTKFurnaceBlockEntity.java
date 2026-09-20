package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.block.blockEntity.abstracts.AbstractMTKFurnaceBlockEntity;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.init.MTKTiers;
import com.takoy3466.manaitamtk.menu.MTKFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MTKFurnaceBlockEntity {
    public static class Wood extends AbstractMTKFurnaceBlockEntity {
        public Wood(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_WOOD.get(), pos, state, MTKTiers.WOOD);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Wood(id, inventory, getBlockPos());
        }
    }

    public static class Stone extends AbstractMTKFurnaceBlockEntity {
        public Stone(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_STONE.get(), pos, state, MTKTiers.STONE);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Stone(id, inventory, getBlockPos());
        }
    }

    public static class Iron extends AbstractMTKFurnaceBlockEntity {
        public Iron(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_IRON.get(), pos, state, MTKTiers.IRON);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Iron(id, inventory, getBlockPos());
        }
    }

    public static class Gold extends AbstractMTKFurnaceBlockEntity {
        public Gold(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_GOLD.get(), pos, state, MTKTiers.GOLD);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Gold(id, inventory, getBlockPos());
        }
    }

    public static class Diamond extends AbstractMTKFurnaceBlockEntity {
        public Diamond(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_DIAMOND.get(), pos, state, MTKTiers.DIAMOND);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Diamond(id, inventory, getBlockPos());
        }
    }

    public static class MTK extends AbstractMTKFurnaceBlockEntity {
        public MTK(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_MTK.get(), pos, state, MTKTiers.MTK);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Mtk(id, inventory, getBlockPos());
        }
    }

    public static class GodMTK extends AbstractMTKFurnaceBlockEntity {
        public GodMTK(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_GODMTK.get(), pos, state, MTKTiers.GODMTK);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.GodMtk(id, inventory, getBlockPos());
        }
    }

    public static class Break extends AbstractMTKFurnaceBlockEntity {
        public Break(BlockPos pos, BlockState state) {
            super(BlockEntitiesInit.MTK_FURNACE_BREAK.get(), pos, state, MTKTiers.BREAK);
        }

        @Override
        public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
            return new MTKFurnaceMenu.Break(id, inventory, getBlockPos());
        }
    }
}
