package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.MTKEnum;
import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.block.BlockMTKFurnace;
import com.takoy3466.ManaitaMTK.block.BlockManaita;
import com.takoy3466.ManaitaMTK.block.BlockManaitaBase;
import com.takoy3466.ManaitaMTK.block.blockEntity.MTKFurnaceBlockEntity;
import com.takoy3466.ManaitaMTK.block.blockEntity.MTKFurnaceBlockEntityBase;
import com.takoy3466.ManaitaMTK.block.example.MTKChest;
import com.takoy3466.ManaitaMTK.block.BlockMTKCraftingTable;
import com.takoy3466.ManaitaMTK.block.example.MTKChestEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKBlocks {
    public static class Blocks{
        //Blockの追加
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ManaitaMTK.MOD_ID);


        public static final RegistryObject<Block> BLOCK_MANAITA = BLOCKS.register("block_manaita", BlockManaita::new);
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

        public static final RegistryObject<Block> MTK_CHEST = BLOCKS.register("mtk_chest", MTKChest::new);

        public static final RegistryObject<Block> WOOD_MTK_FURNACE = FurnaceRegister(MTKEnum.WOOD);
        public static final RegistryObject<Block> STONE_MTK_FURNACE = FurnaceRegister(MTKEnum.STONE);
        public static final RegistryObject<Block> IRON_MTK_FURNACE = FurnaceRegister(MTKEnum.IRON);
        public static final RegistryObject<Block> GOLD_MTK_FURNACE = FurnaceRegister(MTKEnum.GOLD);
        public static final RegistryObject<Block> DIAMOND_MTK_FURNACE = FurnaceRegister(MTKEnum.DIAMOND);
        public static final RegistryObject<Block> MTK_MTK_FURNACE = FurnaceRegister(MTKEnum.MTK);
        public static final RegistryObject<Block> GODMTK_MTK_FURNACE = FurnaceRegister(MTKEnum.GODMTK);
        public static final RegistryObject<Block> BREAK_MTK_FURNACE = FurnaceRegister(MTKEnum.BREAK);

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

    public static class BlockItems{
        //Block_Itemの追加
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);
        public static final RegistryObject<Item> BLOCK_MANAITA = BLOCK_ITEMS.register("block_manaita", () -> new BlockItem(Blocks.BLOCK_MANAITA.get(), new Item.Properties().fireResistant().rarity(Rarity.COMMON)));
        public static final RegistryObject<Item> BLOCK_MANAITA_DIAMOND = BLOCK_ITEMS.register("block_manaita_diamond", () -> new BlockItem(Blocks.BLOCK_MANAITA_DIAMOND.get(), new Item.Properties().fireResistant().rarity(Rarity.COMMON)));
        public static final RegistryObject<Item> BLOCK_MANAITA_DIRT = BLOCK_ITEMS.register("block_manaita_dirt", () -> new BlockItem(Blocks.BLOCK_MANAITA_DIRT.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
        public static final RegistryObject<Item> BLOCK_MANAITA_GLASS = BLOCK_ITEMS.register("block_manaita_glass", () -> new BlockItem(Blocks.BLOCK_MANAITA_GLASS.get(), new Item.Properties().fireResistant().rarity(Rarity.RARE)));

        public static final RegistryObject<Item> WOOD_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.WOOD.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.WOOD_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> STONE_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.STONE.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.STONE_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> IRON_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.IRON.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.IRON_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> GOLD_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.GOLD.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.GOLD_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> DIAMOND_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.DIAMOND.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.DIAMOND_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> MTK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.MTK.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.MTK_CRAFTING_TABLE.get(), new Item.Properties()));
        public static final RegistryObject<Item> GODMTK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.GODMTK.getBlockname() + "_crafting_table", () -> new BlockItem(ManaitaMTKBlocks.Blocks.GODMTK_CRAFTING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
        public static final RegistryObject<Item> BREAK_CRAFTING_TABLE = BLOCK_ITEMS.register(MTKEnum.BREAK.getBlockname() + "_crafting_table", () -> new BlockItem(Blocks.BREAK_CRAFTING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));

        public static final RegistryObject<Item> MTK_CHEST = BLOCK_ITEMS.register("mtk_chest", () -> new BlockItem(Blocks.MTK_CHEST.get(), new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> WOOD_MTK_FURNACE = BLOCK_ITEMS.register("wood_mtk_furnace", () -> new BlockItem(Blocks.WOOD_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> STONE_MTK_FURNACE = BLOCK_ITEMS.register("stone_mtk_furnace", () -> new BlockItem(Blocks.STONE_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> IRON_MTK_FURNACE = BLOCK_ITEMS.register("iron_mtk_furnace", () -> new BlockItem(Blocks.IRON_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> GOLD_MTK_FURNACE = BLOCK_ITEMS.register("gold_mtk_furnace", () -> new BlockItem(Blocks.GOLD_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> DIAMOND_MTK_FURNACE = BLOCK_ITEMS.register("diamond_mtk_furnace", () -> new BlockItem(Blocks.DIAMOND_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> MTK_MTK_FURNACE = BLOCK_ITEMS.register("mtk_mtk_furnace", () -> new BlockItem(Blocks.MTK_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> GODMTK_MTK_FURNACE = BLOCK_ITEMS.register("godmtk_mtk_furnace", () -> new BlockItem(Blocks.GODMTK_MTK_FURNACE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> BREAK_MTK_FURNACE = BLOCK_ITEMS.register("break_mtk_furnace", () -> new BlockItem(Blocks.BREAK_MTK_FURNACE.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    }

    public static class BlockEntities{
        //block_entityの追加
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ManaitaMTK.MOD_ID);

        public static final RegistryObject<BlockEntityType<MTKChestEntity>> MTK_CHEST = BLOCK_ENTITIES.register("mtk_chest",
                () -> BlockEntityType.Builder.of(MTKChestEntity::new, Blocks.MTK_CHEST.get()).build(null));

        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityWood>> MTK_FURNACE_WOOD = BLOCK_ENTITIES.register("mtk_furnace_wood",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityWood(pos, state, MTKEnum.WOOD), Blocks.WOOD_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityStone>> MTK_FURNACE_STONE = BLOCK_ENTITIES.register("mtk_furnace_stone",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityStone(pos, state, MTKEnum.STONE), Blocks.STONE_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityIron>> MTK_FURNACE_IRON = BLOCK_ENTITIES.register("mtk_furnace_iron",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityIron(pos, state, MTKEnum.IRON), Blocks.IRON_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityGold>> MTK_FURNACE_GOLD = BLOCK_ENTITIES.register("mtk_furnace_gold",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityGold(pos, state, MTKEnum.GOLD), Blocks.GOLD_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityDiamond>> MTK_FURNACE_DIAMOND = BLOCK_ENTITIES.register("mtk_furnace_diamond",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityDiamond(pos, state, MTKEnum.DIAMOND), Blocks.DIAMOND_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityMTK>> MTK_FURNACE_MTK = BLOCK_ENTITIES.register("mtk_furnace_mtk",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityMTK(pos, state, MTKEnum.MTK), Blocks.MTK_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityGODMTK>> MTK_FURNACE_GODMTK = BLOCK_ENTITIES.register("mtk_furnace_god",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityGODMTK(pos, state, MTKEnum.GODMTK), Blocks.GODMTK_MTK_FURNACE.get()).build(null));
        public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityBreak>> MTK_FURNACE_BREAK = BLOCK_ENTITIES.register("mtk_furnace_break",
                () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityBreak(pos, state, MTKEnum.BREAK), Blocks.BREAK_MTK_FURNACE.get()).build(null));
    }
}