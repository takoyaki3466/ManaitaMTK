package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.DoubleCraftingTableEnum;
import com.takoy3466.ManaitaMTK.block.*;
import com.takoy3466.ManaitaMTK.block.craftingmanaita.DoubleCraftingTableBlock;
import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
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
        public static final RegistryObject<Block> BLOCK_MANAITA_DIAMOND = BLOCKS.register("block_manaita_diamond", BlockManaitaDiamond::new);
        public static final RegistryObject<Block> BLOCK_MANAITA_DIRT = BLOCKS.register("block_manaita_dirt", BlockManaitaDITR::new);
        public static final RegistryObject<Block> BLOCK_MANAITA_GLASS = BLOCKS.register("block_manaita_glass", BlockManaitaGLASS::new);
    }

    public static class BlockItems{
        //Block_Itemの追加
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

        public static final RegistryObject<Item> BLOCK_MANAITA = BLOCK_ITEMS.register("block_manaita"
                , () -> new BlockItem(Blocks.BLOCK_MANAITA.get(), new Item.Properties()
                        .fireResistant()
                        .rarity(Rarity.COMMON)
                ));

        public static final RegistryObject<Item> BLOCK_MANAITA_DIAMOND = BLOCK_ITEMS.register("block_manaita_diamond"
                , () -> new BlockItem(Blocks.BLOCK_MANAITA_DIAMOND.get(), new Item.Properties()
                        .fireResistant()
                        .rarity(Rarity.COMMON)
                ));

        public static final RegistryObject<Item> BLOCK_MANAITA_DIRT = BLOCK_ITEMS.register("block_manaita_dirt"
                , () -> new BlockItem(Blocks.BLOCK_MANAITA_DIRT.get(), new Item.Properties()
                        .fireResistant()
                        .rarity(Rarity.EPIC)
                ));

        public static final RegistryObject<Item> BLOCK_MANAITA_GLASS = BLOCK_ITEMS.register("block_manaita_glass"
                , () -> new BlockItem(Blocks.BLOCK_MANAITA_GLASS.get(), new Item.Properties()
                        .fireResistant()
                        .rarity(Rarity.RARE)
                ));
    }

    public static class BlockEntities{
        //block_entityの追加
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ManaitaMTK.MOD_ID);
    }
}
