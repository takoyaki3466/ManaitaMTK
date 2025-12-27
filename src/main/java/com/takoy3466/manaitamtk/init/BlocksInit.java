package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.block.*;
import com.takoy3466.manaitamtk.block.BlockMTKChest;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksInit {
    public static class Blocks{
        //Blockの追加
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ManaitaMTK.MOD_ID);

        public static final RegistryObject<Block> BLOCK_MANAITA = BLOCKS.register("block_manaita",BlockManaita::new);
        public static final RegistryObject<Block> BLOCK_MANAITA_DIAMOND = BlockManaitaRegister(MTKEnum.DIAMOND);
        public static final RegistryObject<Block> BLOCK_MANAITA_DIRT = BlockManaitaRegister(MTKEnum.DIRT);
        public static final RegistryObject<Block> BLOCK_MANAITA_GLASS = BlockManaitaRegister(MTKEnum.GLASS);

        public static final RegistryObject<Block> WOOD_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.WOOD);
        public static final RegistryObject<Block> STONE_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.STONE);
        public static final RegistryObject<Block> IRON_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.IRON);
        public static final RegistryObject<Block> GOLD_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.GOLD);
        public static final RegistryObject<Block> DIAMOND_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.DIAMOND);
        public static final RegistryObject<Block> MTK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.MTK);
        public static final RegistryObject<Block> GODMTK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.GODMTK);
        public static final RegistryObject<Block> BREAK_CRAFTING_TABLE = CraftingTableRegister(MTKEnum.BREAK);

        public static final RegistryObject<Block> MTK_CHEST = BLOCKS.register("mtk_chest", BlockMTKChest::new);

        public static final RegistryObject<Block> WOOD_MTK_FURNACE = FurnaceRegister(MTKEnum.WOOD);
        public static final RegistryObject<Block> STONE_MTK_FURNACE = FurnaceRegister(MTKEnum.STONE);
        public static final RegistryObject<Block> IRON_MTK_FURNACE = FurnaceRegister(MTKEnum.IRON);
        public static final RegistryObject<Block> GOLD_MTK_FURNACE = FurnaceRegister(MTKEnum.GOLD);
        public static final RegistryObject<Block> DIAMOND_MTK_FURNACE = FurnaceRegister(MTKEnum.DIAMOND);
        public static final RegistryObject<Block> MTK_MTK_FURNACE = FurnaceRegister(MTKEnum.MTK);
        public static final RegistryObject<Block> GODMTK_MTK_FURNACE = FurnaceRegister(MTKEnum.GODMTK);
        public static final RegistryObject<Block> BREAK_MTK_FURNACE = FurnaceRegister(MTKEnum.BREAK);

        public static final RegistryObject<Block> AUTO_WORKBENCH_MTK = BLOCKS.register("auto_workbench_mtk", BlockAutoWorkbenchMTK::new);


        //型の作成
        public static RegistryObject<Block> BlockManaitaRegister(MTKEnum mtkEnum) {
            return BLOCKS.register("block_manaita_" + mtkEnum.getBlockname(), () -> new BlockManaitaBase(mtkEnum.getMag()));
        }
        public static RegistryObject<Block> CraftingTableRegister(MTKEnum mtkEnum) {
            return BLOCKS.register(mtkEnum.getBlockname() + "_crafting_table", () -> new BlockMTKCraftingTable(mtkEnum.getMag()));
        }
        public static RegistryObject<Block> FurnaceRegister(MTKEnum mtkEnum) {
            return BLOCKS.register(mtkEnum.getBlockname() + "_mtk_furnace", () -> new BlockMTKFurnace(mtkEnum));
        }
    }

}