package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.DoubleCraftingTableEnum;
import com.takoy3466.ManaitaMTK.block.craftingmanaita.DoubleCraftingTableBlock;
import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKCraftingTables {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ManaitaMTK.MOD_ID);

    public static final RegistryObject<Block> WOOD_CRAFTING_TABLE;
    public static final RegistryObject<Block> STONE_CRAFTING_TABLE;
    public static final RegistryObject<Block> IRON_CRAFTING_TABLE;
    public static final RegistryObject<Block> GOLD_CRAFTING_TABLE;
    public static final RegistryObject<Block> DIAMOND_CRAFTING_TABLE;
    public static final RegistryObject<Block> MTK_CRAFTING_TABLE;
    public static final RegistryObject<Block> GODMTK_CRAFTING_TABLE;

    public static RegistryObject<Block> CraftingTableRegister(DoubleCraftingTableEnum tableEnum){
        return BLOCKS.register(tableEnum.getBlockname() + "_crafting_table",
                () -> new DoubleCraftingTableBlock(Block.Properties.of(), tableEnum.getMag(), tableEnum.getComponentName())
        );
    }

    static {
        WOOD_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.WOOD);
        STONE_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.STONE);
        IRON_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.IRON);
        GOLD_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.GOLD);
        DIAMOND_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.DIAMOND);
        MTK_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.MTK);
        GODMTK_CRAFTING_TABLE = CraftingTableRegister(DoubleCraftingTableEnum.GODMTK);
    }

    public static class BlockItems{
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

        public static final RegistryObject<Item> WOOD_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.WOOD.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.WOOD_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> STONE_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.STONE.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.STONE_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> IRON_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.IRON.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.IRON_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> GOLD_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.GOLD.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.GOLD_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> DIAMOND_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.DIAMOND.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.DIAMOND_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> MTK_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.MTK.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.MTK_CRAFTING_TABLE.get(), new Item.Properties()));

        public static final RegistryObject<Item> GODMTK_CRAFTING_TABLE = BLOCK_ITEMS.register(DoubleCraftingTableEnum.GODMTK.getBlockname() + "_crafting_table",
                () -> new BlockItem(ManaitaMTKCraftingTables.GODMTK_CRAFTING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    }
}
