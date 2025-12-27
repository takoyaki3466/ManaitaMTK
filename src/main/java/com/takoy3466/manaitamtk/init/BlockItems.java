package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockItems {
    //Block_Itemの追加
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);
    public static final RegistryObject<Item> BLOCK_MANAITA = BLOCK_ITEMS.register("block_manaita", () -> new BlockItem(BlocksInit.Blocks.BLOCK_MANAITA.get(), new Item.Properties().fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BLOCK_MANAITA_DIAMOND = BLOCK_ITEMS.register("block_manaita_diamond", () -> new BlockItem(BlocksInit.Blocks.BLOCK_MANAITA_DIAMOND.get(), new Item.Properties().fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BLOCK_MANAITA_DIRT = BLOCK_ITEMS.register("block_manaita_dirt", () -> new BlockItem(BlocksInit.Blocks.BLOCK_MANAITA_DIRT.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BLOCK_MANAITA_GLASS = BLOCK_ITEMS.register("block_manaita_glass", () -> new BlockItem(BlocksInit.Blocks.BLOCK_MANAITA_GLASS.get(), new Item.Properties().fireResistant().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WOOD_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.WOOD.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.WOOD_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.STONE.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.STONE_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.IRON.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.IRON_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.GOLD.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.GOLD_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.DIAMOND.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.DIAMOND_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MTK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.MTK.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.MTK_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GODMTK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.GODMTK.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.GODMTK_CRAFTING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BREAK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.BREAK.getBlockname() + "_crafting_table", () -> new BlockItem(BlocksInit.Blocks.BREAK_CRAFTING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MTK_CHEST = BLOCK_ITEMS.register("mtk_chest", () -> new BlockItem(BlocksInit.Blocks.MTK_CHEST.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOOD_MTK_FURNACE = BLOCK_ITEMS.register("wood_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.WOOD_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> STONE_MTK_FURNACE = BLOCK_ITEMS.register("stone_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.STONE_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> IRON_MTK_FURNACE = BLOCK_ITEMS.register("iron_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.IRON_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GOLD_MTK_FURNACE = BLOCK_ITEMS.register("gold_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.GOLD_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIAMOND_MTK_FURNACE = BLOCK_ITEMS.register("diamond_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.DIAMOND_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MTK_MTK_FURNACE = BLOCK_ITEMS.register("mtk_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.MTK_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GODMTK_MTK_FURNACE = BLOCK_ITEMS.register("godmtk_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.GODMTK_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> BREAK_MTK_FURNACE = BLOCK_ITEMS.register("break_mtk_furnace", () -> new BlockItem(BlocksInit.Blocks.BREAK_MTK_FURNACE.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> AUTO_WORKBENCH_MTK = BLOCK_ITEMS.register("auto_workbench_mtk", () -> new BlockItem(BlocksInit.Blocks.AUTO_WORKBENCH_MTK.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC)));

}
