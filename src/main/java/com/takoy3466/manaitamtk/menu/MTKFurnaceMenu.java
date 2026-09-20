package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.init.MenusInit;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMTKFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class MTKFurnaceMenu {

    public static class Wood extends AbstractMTKFurnaceMenu {
        public Wood(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_WOOD.get(), id, playerInventory, buf);
        }

        public Wood(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_WOOD.get(), id, playerInventory, pos);
        }
    }
    public static class Stone extends AbstractMTKFurnaceMenu {
        public Stone(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_STONE.get(), id, playerInventory, buf);
        }

        public Stone(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_STONE.get(), id, playerInventory, pos);
        }
    }
    public static class Iron extends AbstractMTKFurnaceMenu {
        public Iron(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_IRON.get(), id, playerInventory, buf);
        }

        public Iron(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_IRON.get(), id, playerInventory, pos);
        }
    }
    public static class Gold extends AbstractMTKFurnaceMenu {
        public Gold(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_GOLD.get(), id, playerInventory, buf);
        }

        public Gold(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_GOLD.get(), id, playerInventory, pos);
        }
    }
    public static class Diamond extends AbstractMTKFurnaceMenu {
        public Diamond(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_DIAMOND.get(), id, playerInventory, buf);
        }

        public Diamond(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_DIAMOND.get(), id, playerInventory, pos);
        }
    }
    public static class Mtk extends AbstractMTKFurnaceMenu {
        public Mtk(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_MTK.get(), id, playerInventory, buf);
        }

        public Mtk(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_MTK.get(), id, playerInventory, pos);
        }
    }
    public static class GodMtk extends AbstractMTKFurnaceMenu {
        public GodMtk(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_GODMTK.get(), id, playerInventory, buf);
        }

        public GodMtk(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_GODMTK.get(), id, playerInventory, pos);
        }
    }
    public static class Break extends AbstractMTKFurnaceMenu {
        public Break(int id, Inventory playerInventory, FriendlyByteBuf buf) {
            super(MenusInit.MTK_FURNACE_BREAK.get(), id, playerInventory, buf);
        }

        public Break(int id, Inventory playerInventory, BlockPos pos) {
            super(MenusInit.MTK_FURNACE_BREAK.get(), id, playerInventory, pos);
        }
    }
}
