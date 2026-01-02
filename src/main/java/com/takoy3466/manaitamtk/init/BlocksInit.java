package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.apiMTK.register.MTKDeferredRegister;
import com.takoy3466.manaitamtk.apiMTK.register.MTKRegistryObject;
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
    private static final List<MTKEnum> epicList = List.of(MTKEnum.DIRT, MTKEnum.GLASS, MTKEnum.GODMTK, MTKEnum.BREAK);


    //Blockの追加
    public static final MTKDeferredRegister<Block, Item> BLOCK = MTKDeferredRegister.create(ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

    public static MTKRegistryObject<Block, Item> register(String name, Supplier<Block> supplier, Item.Properties properties) {
        RegistryObject<Block> block = BLOCK.registerFront(name, supplier);
        RegistryObject<Item> item = BLOCK.registerBehind(name, () -> new BlockItem(block.get(), properties));
        return BLOCK.registerAll(block, item);
    }

    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA = register("block_manaita", BlockManaita::new, COMMON);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_DIAMOND = BlockManaitaRegister(MTKEnum.DIAMOND);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_DIRT = BlockManaitaRegister(MTKEnum.DIRT);
    public static final MTKRegistryObject<Block, Item> BLOCK_MANAITA_GLASS = BlockManaitaRegister(MTKEnum.GLASS);

    public static final MTKRegistryObject<Block, Item> WOOD_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.WOOD);
    public static final MTKRegistryObject<Block, Item> STONE_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.STONE);
    public static final MTKRegistryObject<Block, Item> IRON_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.IRON);
    public static final MTKRegistryObject<Block, Item> GOLD_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.GOLD);
    public static final MTKRegistryObject<Block, Item> DIAMOND_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.DIAMOND);
    public static final MTKRegistryObject<Block, Item> MTK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.MTK);
    public static final MTKRegistryObject<Block, Item> GODMTK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.GODMTK);
    public static final MTKRegistryObject<Block, Item> BREAK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.BREAK);

    public static final MTKRegistryObject<Block, Item> MTK_CHEST = register("mtk_chest", BlockMTKChest::new, COMMON);

    public static final MTKRegistryObject<Block, Item> WOOD_MTK_FURNACE = FurnaceRegister(MTKEnum.WOOD);
    public static final MTKRegistryObject<Block, Item> STONE_MTK_FURNACE = FurnaceRegister(MTKEnum.STONE);
    public static final MTKRegistryObject<Block, Item> IRON_MTK_FURNACE = FurnaceRegister(MTKEnum.IRON);
    public static final MTKRegistryObject<Block, Item> GOLD_MTK_FURNACE = FurnaceRegister(MTKEnum.GOLD);
    public static final MTKRegistryObject<Block, Item> DIAMOND_MTK_FURNACE = FurnaceRegister(MTKEnum.DIAMOND);
    public static final MTKRegistryObject<Block, Item> MTK_MTK_FURNACE = FurnaceRegister(MTKEnum.MTK);
    public static final MTKRegistryObject<Block, Item> GODMTK_MTK_FURNACE = FurnaceRegister(MTKEnum.GODMTK);
    public static final MTKRegistryObject<Block, Item> BREAK_MTK_FURNACE = FurnaceRegister(MTKEnum.BREAK);

    public static final MTKRegistryObject<Block, Item> AUTO_WORKBENCH_MTK = register("auto_workbench_mtk", BlockAutoWorkbenchMTK::new, EPIC);

    //型の作成
    public static MTKRegistryObject<Block, Item> BlockManaitaRegister(MTKEnum mtkEnum) {
        return register("block_manaita_" + mtkEnum.getComponent(), () -> new BlockManaitaBase(mtkEnum.getMag()), epicList.contains(mtkEnum) ? EPIC : COMMON);

    }

    public static MTKRegistryObject<Block, Item> CraftingTableRegister(MTKEnum mtkEnum) {
        return register(mtkEnum.getComponent() + "_crafting_table", () -> new BlockMTKCraftingTable(mtkEnum.getMag()), epicList.contains(mtkEnum) ? EPIC : COMMON);
    }

    public static MTKRegistryObject<Block, Item> FurnaceRegister(MTKEnum mtkEnum) {
        return register(mtkEnum.getComponent() + "_mtk_furnace", () -> new BlockMTKFurnace(mtkEnum), epicList.contains(mtkEnum) ? EPIC : COMMON);
    }
}
