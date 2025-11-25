package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.apiMTK.ITickableBlockEntity;
import com.takoy3466.manaitamtk.menu.MTKFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MTKFurnaceBlockEntity {
    public static class FurnaceEntityWood extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityWood(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuWood(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityStone extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityStone(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuStone(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityIron extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityIron(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuIron(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityGold extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityGold(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuGold(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityDiamond extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityDiamond(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuDiamond(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityMTK extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityMTK(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuMTK(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityGODMTK extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityGODMTK(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuGODMTK(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
    public static class FurnaceEntityBreak extends MTKFurnaceBlockEntityBase implements ITickableBlockEntity {
        private final MTKEnum mtkEnum;
        public FurnaceEntityBreak(BlockPos pos, BlockState state, MTKEnum mtkEnum) {
            super(pos, state, mtkEnum);
            this.mtkEnum = mtkEnum;
        }
        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new MTKFurnaceMenu.FurnaceMenuBreak(id, inv, getBlockPos(), this.mtkEnum);
        }
    }
}
