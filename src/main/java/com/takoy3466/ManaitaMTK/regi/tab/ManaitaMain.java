package com.takoy3466.ManaitaMTK.regi.tab;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKBlocks;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ManaitaMain {

    public static final Item[] items = {
            //上から順番にタブに追加される
            Items.STONE,

            ManaitaMTKBlocks.BlockItems.BLOCK_MANAITA.get(),

            ManaitaMTKItems.ITEM_MTK.get(),

            ManaitaMTKItems.MANAITA_PICKAXE.get(),
            ManaitaMTKItems.MANAITA_AXE.get(),
            ManaitaMTKItems.MANAITA_SHOVEL.get(),
            ManaitaMTKItems.MANAITA_SWORD.get(),
            ManaitaMTKItems.MANAITA_PAXEL.get(),

            ManaitaMTKItems.HELMET_MANAITA.get(),
            ManaitaMTKItems.CHESTPLATE_MANAITA.get(),
            ManaitaMTKItems.LEGINS_MANAITA.get(),
            ManaitaMTKItems.BOOTS_MANAITA.get()
    };
}
