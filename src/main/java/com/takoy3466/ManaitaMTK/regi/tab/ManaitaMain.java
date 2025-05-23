package com.takoy3466.ManaitaMTK.regi.tab;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKBlocks;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKCraftingTables;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ManaitaMain {

    public static final Item[] items = {
            //上から順番にタブに追加される
            ManaitaMTKBlocks.BlockItems.BLOCK_MANAITA.get(),
            ManaitaMTKBlocks.BlockItems.BLOCK_MANAITA_DIAMOND.get(),
            ManaitaMTKBlocks.BlockItems.BLOCK_MANAITA_DIRT.get(),
            ManaitaMTKBlocks.BlockItems.BLOCK_MANAITA_GLASS.get(),

            ManaitaMTKCraftingTables.BlockItems.WOOD_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.STONE_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.IRON_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.GOLD_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.DIAMOND_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.MTK_CRAFTING_TABLE.get(),
            ManaitaMTKCraftingTables.BlockItems.GODMTK_CRAFTING_TABLE.get(),

            ManaitaMTKItems.ITEM_MTK.get(),
            //ManaitaMTKItems.ZIKOKENZIYOKU.get(),
            //ManaitaMTKItems.CRUSHED_MTK.get(),

            ManaitaMTKItems.MANAITA_PICKAXE.get(),
            ManaitaMTKItems.MANAITA_AXE.get(),
            ManaitaMTKItems.MANAITA_SHOVEL.get(),
            ManaitaMTKItems.MANAITA_SWORD.get(),
            ManaitaMTKItems.MANAITA_PAXEL.get(),
            ManaitaMTKItems.MANAITA_BOW.get(),

            ManaitaMTKItems.PORTABLE_WOOD_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_STONE_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_IRON_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_GOLD_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_DIAMOND_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_MTK_CRAFTING_TABLE.get(),
            ManaitaMTKItems.PORTABLE_GODMTK_CRAFTING_TABLE.get(),

            ManaitaMTKItems.CHANGEABLE_PORTABLE_DCT.get(),

            ManaitaMTKItems.WOOD_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.STONE_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.IRON_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.GOLD_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.DIAMOND_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.MTK_DOUBLE_BLOCK_MTK.get(),
            ManaitaMTKItems.GODMTK_DOUBLE_BLOCK_MTK.get(),

            ManaitaMTKItems.HELMET_MANAITA.get(),
            ManaitaMTKItems.CHESTPLATE_MANAITA.get(),
            ManaitaMTKItems.LEGINS_MANAITA.get(),
            ManaitaMTKItems.BOOTS_MANAITA.get()
    };
}
