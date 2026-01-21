package com.takoy3466.manaitamtk.init.tab;

import com.takoy3466.manaitamtk.init.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaTabItems {

    public static final RegistryObject<? extends Item>[] items = new RegistryObject[] {
            //上から順番にタブに追加される
            BlocksInit.BLOCK_MANAITA.getBehind(),
            BlocksInit.BLOCK_MANAITA_DIAMOND.getBehind(),
            BlocksInit.BLOCK_MANAITA_DIRT.getBehind(),
            BlocksInit.BLOCK_MANAITA_GLASS.getBehind(),

            BlocksInit.WOOD_CRAFTING_TABLE.getBehind(),
            BlocksInit.STONE_CRAFTING_TABLE.getBehind(),
            BlocksInit.IRON_CRAFTING_TABLE.getBehind(),
            BlocksInit.GOLD_CRAFTING_TABLE.getBehind(),
            BlocksInit.DIAMOND_CRAFTING_TABLE.getBehind(),
            BlocksInit.MTK_CRAFTING_TABLE.getBehind(),
            BlocksInit.GODMTK_CRAFTING_TABLE.getBehind(),
            BlocksInit.BREAK_CRAFTING_TABLE.getBehind(),

            BlocksInit.WOOD_MTK_FURNACE.getBehind(),
            BlocksInit.STONE_MTK_FURNACE.getBehind(),
            BlocksInit.IRON_MTK_FURNACE.getBehind(),
            BlocksInit.GOLD_MTK_FURNACE.getBehind(),
            BlocksInit.DIAMOND_MTK_FURNACE.getBehind(),
            BlocksInit.MTK_MTK_FURNACE.getBehind(),
            BlocksInit.GODMTK_MTK_FURNACE.getBehind(),
            BlocksInit.BREAK_MTK_FURNACE.getBehind(),

            ItemsInit.ITEM_MTK,
            ItemsInit.CRUSHED_MTK,

            ItemsInit.MANAITA_PICKAXE,
            ItemsInit.MANAITA_AXE,
            ItemsInit.MANAITA_SHOVEL,
            ItemsInit.MANAITA_SWORD,
            ItemsInit.MANAITA_PAXEL,
            ItemsInit.MANAITA_BOW,
            ItemsInit.MANAITA_HOE,

            ItemsInit.PORTABLE_WOOD_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_STONE_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_IRON_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_GOLD_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_DIAMOND_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_MTK_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_GODMTK_CRAFTING_TABLE.getRegistry(),
            ItemsInit.PORTABLE_BREAK_CRAFTING_TABLE.getRegistry(),

            ItemsInit.CHANGEABLE_PORTABLE_DCT,

            ItemsInit.WOOD_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.STONE_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.IRON_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.GOLD_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.DIAMOND_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.MTK_DOUBLE_BLOCK_MTK.getRegistry(),
            ItemsInit.GODMTK_DOUBLE_BLOCK_MTK.getRegistry(),

            ItemsInit.PORTABLE_WOOD_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_STONE_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_IRON_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_GOLD_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_DIAMOND_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_MTK_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_GODMTK_FURNACE.getRegistry(),
            ItemsInit.PORTABLE_BREAK_FURNACE.getRegistry(),

            ItemsInit.HELMET_MANAITA,
            ItemsInit.CHESTPLATE_MANAITA,
            ItemsInit.LEGINS_MANAITA,
            ItemsInit.BOOTS_MANAITA,

            BlocksInit.MTK_CHEST.getBehind(),
            ItemsInit.MTK_BACKPACK,

            BlocksInit.AUTO_WORKBENCH_MTK.getBehind(),

            ItemsInit.DEBUG_MTK,
    };
}