package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.block.blockEntity.abstracts.AbstractMultiFurnaceBlockEntity;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.menu.MultiFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class MultiFurnaceBlockEntity {
    public static class Wood extends AbstractMultiFurnaceBlockEntity {
        public Wood(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.WOOD_MULTI_FURNACE.get(), pos, state, mtkTier);
        }
        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            System.out.println(this.getClass().getName() + " create Menu");
            return new MultiFurnaceMenu.Wood(i, inventory, getBlockPos());
        }
    }
    public static class Stone extends AbstractMultiFurnaceBlockEntity {

        public Stone(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.STONE_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.Stone(i, inventory, getBlockPos());
        }
    }
    public static class Iron extends AbstractMultiFurnaceBlockEntity {

        public Iron(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.IRON_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.Iron(i, inventory, getBlockPos());
        }
    }
    public static class Gold extends AbstractMultiFurnaceBlockEntity {

        public Gold(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.GOLD_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.Gold(i, inventory, getBlockPos());
        }
    }
    public static class Diamond extends AbstractMultiFurnaceBlockEntity {

        public Diamond(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.DIAMOND_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.Diamond(i, inventory, getBlockPos());
        }
    }
    public static class MTK extends AbstractMultiFurnaceBlockEntity {

        public MTK(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.MTK_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.MTK(i, inventory, getBlockPos());
        }
    }
    public static class GodMTK extends AbstractMultiFurnaceBlockEntity {

        public GodMTK(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.GODMTK_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.GodMTK(i, inventory, getBlockPos());
        }
    }
    public static class Break extends AbstractMultiFurnaceBlockEntity {

        public Break(BlockPos pos, BlockState state, MTKTier mtkTier) {
            super(BlockEntitiesInit.BREAK_MULTI_FURNACE.get(), pos, state, mtkTier);
        }

        @Override
        protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
            return new MultiFurnaceMenu.Break(i, inventory, getBlockPos());
        }
    }
}
