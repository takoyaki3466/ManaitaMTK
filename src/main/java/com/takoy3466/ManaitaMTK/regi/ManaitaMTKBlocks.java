package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.block.BlockManaita;
import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKBlocks {

    public static class Blocks{
        //Blockの追加
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ManaitaMTK.MOD_ID);

        public static final RegistryObject<Block> BLOCK_MANAITA = BLOCKS.register("block_manaita", BlockManaita::new);
    }

    public static class BlockItems{
        //Block_Itemの追加
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

        public static final RegistryObject<Item> BLOCK_MANAITA = BLOCK_ITEMS.register("block_manaita"
                , () -> new BlockItem(Blocks.BLOCK_MANAITA.get(), new Item.Properties()
                        .fireResistant()
                        .rarity(Rarity.EPIC)
                ));
    }
}
