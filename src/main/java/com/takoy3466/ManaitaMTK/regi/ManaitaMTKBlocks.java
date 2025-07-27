package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.MTKEnum;
import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.block.BlockManaita;
import com.takoy3466.ManaitaMTK.block.BlockManaitaBase;
import com.takoy3466.ManaitaMTK.block.MTKCraftingTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
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

        //型の作成
        public static RegistryObject<Block> BlockManaitaRegister(MTKEnum tableEnum){
            return BLOCKS.register("block_manaita_" + tableEnum.getBlockname(), () -> new BlockManaitaBase(tableEnum.getMag()));
        }
        public static RegistryObject<Block> CraftingTableRegister(MTKEnum tableEnum){
            return BLOCKS.register(tableEnum.getBlockname() + "_crafting_table", () -> new MTKCraftingTableBlock(tableEnum.getMag()));
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


    }

    public static class BlockEntities{
        //block_entityの追加
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ManaitaMTK.MOD_ID);

        private static <T extends BlockEntity> BlockEntityType<T> set (BlockEntityType.BlockEntitySupplier<T> entity, Block block){
            return BlockEntityType.Builder.of(entity, block).build(null);
        }
    }
}
