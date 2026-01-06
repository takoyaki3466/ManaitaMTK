package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.register.MTKDeferredRegister;
import com.takoy3466.manaitamtk.api.register.MTKRegistryObject;
import com.takoy3466.manaitamtk.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class BlocksInit {
    private static final Item.Properties COMMON = new Item.Properties().fireResistant().rarity(Rarity.COMMON);
    private static final Item.Properties EPIC = new Item.Properties().fireResistant().rarity(Rarity.EPIC);
    private static final List<MTKTier> epicList = List.of(MTKTiers.DIRT, MTKTiers.GLASS, MTKTiers.GODMTK, MTKTiers.BREAK);

    private static Item.Properties isContain(MTKTier mtkTier) {
        return epicList.contains(mtkTier) ? EPIC : COMMON;
    }


    //Blockの追加
    public static final MTKDeferredRegister<Block, Item> BLOCK = MTKDeferredRegister.create(ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

    public static MTKRegistryObject<Block, Item> register(String name, Supplier<Block> supplier, Item.Properties properties) {
        RegistryObject<Block> block = BLOCK.registerFront(name, supplier);
        RegistryObject<Item> item = BLOCK.registerBehind(name, () -> new BlockItem(block.get(), properties));
        return BLOCK.registerAll(block, item);
    }

    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA = register("block_manaita", BlockManaita::new, COMMON);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_DIAMOND = BlockManaitaRegister(MTKTiers.DIAMOND);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_DIRT = BlockManaitaRegister(MTKTiers.DIRT);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_GLASS = BlockManaitaRegister(MTKTiers.GLASS);

    public static final MTKRegistryObject<Block, Item> WOOD_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.WOOD);
    public static final MTKRegistryObject<Block, Item> STONE_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.STONE);
    public static final MTKRegistryObject<Block, Item> IRON_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.IRON);
    public static final MTKRegistryObject<Block, Item> GOLD_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.GOLD);
    public static final MTKRegistryObject<Block, Item> DIAMOND_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.DIAMOND);
    public static final MTKRegistryObject<Block, Item> MTK_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.MTK);
    public static final MTKRegistryObject<Block, Item> GODMTK_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.GODMTK);
    public static final MTKRegistryObject<Block, Item> BREAK_CRAFTING_TABLE = CraftingTableRegister(MTKTiers.BREAK);

    public static final MTKRegistryObject<Block, Item> MTK_CHEST = register("mtk_chest", BlockMTKChest::new, COMMON);

    public static final MTKRegistryObject<Block, Item> WOOD_MTK_FURNACE = FurnaceRegister(MTKTiers.WOOD);
    public static final MTKRegistryObject<Block, Item> STONE_MTK_FURNACE = FurnaceRegister(MTKTiers.STONE);
    public static final MTKRegistryObject<Block, Item> IRON_MTK_FURNACE = FurnaceRegister(MTKTiers.IRON);
    public static final MTKRegistryObject<Block, Item> GOLD_MTK_FURNACE = FurnaceRegister(MTKTiers.GOLD);
    public static final MTKRegistryObject<Block, Item> DIAMOND_MTK_FURNACE = FurnaceRegister(MTKTiers.DIAMOND);
    public static final MTKRegistryObject<Block, Item> MTK_MTK_FURNACE = FurnaceRegister(MTKTiers.MTK);
    public static final MTKRegistryObject<Block, Item> GODMTK_MTK_FURNACE = FurnaceRegister(MTKTiers.GODMTK);
    public static final MTKRegistryObject<Block, Item> BREAK_MTK_FURNACE = FurnaceRegister(MTKTiers.BREAK);

    public static final MTKRegistryObject<Block, Item> AUTO_WORKBENCH_MTK = register("auto_workbench_mtk", BlockAutoWorkbenchMTK::new, EPIC);



    //型の作成
    public static MTKRegistryObject<Block, Item> BlockManaitaRegister(MTKTier mtkTier) {
        return register("block_manaita_" + mtkTier.getName(), () -> new BlockManaitaBase(mtkTier), isContain(mtkTier));

    }

    public static MTKRegistryObject<Block, Item> CraftingTableRegister(MTKTier mtkTier) {
        return register(mtkTier.getName() + "_crafting_table", () -> new BlockMTKCraftingTable(mtkTier), isContain(mtkTier));
    }

    public static MTKRegistryObject<Block, Item> FurnaceRegister(MTKTier mtkTier) {
        return register(mtkTier.getName() + "_mtk_furnace", () -> new BlockMTKFurnace(mtkTier), isContain(mtkTier));
    }
}
