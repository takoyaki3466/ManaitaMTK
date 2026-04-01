package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.init.MenusInit;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMultiFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MultiFurnaceMenu {
    public static class Wood extends AbstractMultiFurnaceMenu {
        public Wood(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.WOOD_MULTI_FURNACE.get(), id, inv, pos);
        }

        public Wood(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.WOOD_MULTI_FURNACE.get(), id, inv, buf);
        }
    }
    public static class Stone extends AbstractMultiFurnaceMenu {

        public Stone(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.STONE_MULTI_FURNACE.get(), id, inv, buf);
        }

        public Stone(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.STONE_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class Iron extends AbstractMultiFurnaceMenu {

        public Iron(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.IRON_MULTI_FURNACE.get(), id, inv, buf);
        }

        public Iron(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.IRON_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class Gold extends AbstractMultiFurnaceMenu {

        public Gold(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.GOLD_MULTI_FURNACE.get(), id, inv, buf);
        }

        public Gold(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.GOLD_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class Diamond extends AbstractMultiFurnaceMenu {

        public Diamond(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.DIAMOND_MULTI_FURNACE.get(), id, inv, buf);
        }

        public Diamond(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.DIAMOND_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class MTK extends AbstractMultiFurnaceMenu {

        public MTK(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.MTK_MULTI_FURNACE.get(), id, inv, buf);
        }

        public MTK(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.MTK_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class GodMTK extends AbstractMultiFurnaceMenu {

        public GodMTK(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.GODMTK_MULTI_FURNACE.get(), id, inv, buf);
        }

        public GodMTK(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.GODMTK_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
    public static class Break extends AbstractMultiFurnaceMenu {

        public Break(int id, Inventory inv, FriendlyByteBuf buf) {
            super(MenusInit.BREAK_MULTI_FURNACE.get(), id, inv, buf);
        }

        public Break(int id, Inventory inv, BlockPos pos) {
            super(MenusInit.BREAK_MULTI_FURNACE.get(), id, inv, pos);
        }
    }
}
