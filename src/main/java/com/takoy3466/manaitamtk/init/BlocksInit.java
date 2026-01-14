package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.registry.register.BlockRegister;
import com.takoy3466.manaitamtk.api.registry.BlockRegistryObject;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredBlockRegister;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredBlockRegistryObject;
import com.takoy3466.manaitamtk.block.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

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
    public static final BlockRegister BLOCKS = BlockRegister.create(ManaitaMTK.MOD_ID);

    public static final TieredBlockRegister<MTKTier> TIER_BLOCKS = TieredBlockRegister.create(ManaitaMTK.MOD_ID);


    public static final BlockRegistryObject BLOCK_MANAITA = BLOCKS.register("block_manaita", BlockManaita::new, COMMON);
    public static final BlockRegistryObject BLOCK_MANAITA_DIAMOND = blockManaitaRegister(MTKTiers.DIAMOND);
    public static final BlockRegistryObject BLOCK_MANAITA_DIRT = blockManaitaRegister(MTKTiers.DIRT);
    public static final BlockRegistryObject BLOCK_MANAITA_GLASS = blockManaitaRegister(MTKTiers.GLASS);

    public static final TieredBlockRegistryObject<MTKTier> WOOD_CRAFTING_TABLE = craftingTableRegister(MTKTiers.WOOD);
    public static final TieredBlockRegistryObject<MTKTier> STONE_CRAFTING_TABLE = craftingTableRegister(MTKTiers.STONE);
    public static final TieredBlockRegistryObject<MTKTier> IRON_CRAFTING_TABLE = craftingTableRegister(MTKTiers.IRON);
    public static final TieredBlockRegistryObject<MTKTier> GOLD_CRAFTING_TABLE = craftingTableRegister(MTKTiers.GOLD);
    public static final TieredBlockRegistryObject<MTKTier> DIAMOND_CRAFTING_TABLE = craftingTableRegister(MTKTiers.DIAMOND);
    public static final TieredBlockRegistryObject<MTKTier> MTK_CRAFTING_TABLE = craftingTableRegister(MTKTiers.MTK);
    public static final TieredBlockRegistryObject<MTKTier> GODMTK_CRAFTING_TABLE = craftingTableRegister(MTKTiers.GODMTK);
    public static final TieredBlockRegistryObject<MTKTier> BREAK_CRAFTING_TABLE = craftingTableRegister(MTKTiers.BREAK);

    public static final BlockRegistryObject MTK_CHEST = BLOCKS.register("mtk_chest", BlockMTKChest::new, COMMON);

    public static final TieredBlockRegistryObject<MTKTier> WOOD_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Wood::new, MTKTiers.WOOD);
    public static final TieredBlockRegistryObject<MTKTier> STONE_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Stone::new, MTKTiers.STONE);
    public static final TieredBlockRegistryObject<MTKTier> IRON_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Iron::new, MTKTiers.IRON);
    public static final TieredBlockRegistryObject<MTKTier> GOLD_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Gold::new, MTKTiers.GOLD);
    public static final TieredBlockRegistryObject<MTKTier> DIAMOND_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Diamond::new, MTKTiers.DIAMOND);
    public static final TieredBlockRegistryObject<MTKTier> MTK_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.MTK::new, MTKTiers.MTK);
    public static final TieredBlockRegistryObject<MTKTier> GODMTK_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.GodMTK::new, MTKTiers.GODMTK);
    public static final TieredBlockRegistryObject<MTKTier> BREAK_MTK_FURNACE = furnaceRegister(BlockMTKFurnace.Break::new, MTKTiers.BREAK);

    public static final BlockRegistryObject AUTO_WORKBENCH_MTK = BLOCKS.register("auto_workbench_mtk", BlockAutoWorkbenchMTK::new, EPIC);



    //型の作成
    public static BlockRegistryObject blockManaitaRegister(MTKTier mtkTier) {
        return BLOCKS.register("block_manaita_" + mtkTier.getName(), () -> new BlockManaitaBase(mtkTier), isContain(mtkTier));

    }

    public static TieredBlockRegistryObject<MTKTier> craftingTableRegister(MTKTier mtkTier) {
        return TIER_BLOCKS.register(mtkTier.getName() + "_crafting_table", () -> new BlockMTKCraftingTable(mtkTier), isContain(mtkTier), mtkTier);
    }

    public static TieredBlockRegistryObject<MTKTier> furnaceRegister(Supplier<Block> supplier, MTKTier mtkTier) {
        return TIER_BLOCKS.register(mtkTier.getName() + "_mtk_furnace", supplier, isContain(mtkTier), mtkTier);
    }
}
