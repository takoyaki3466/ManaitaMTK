package com.takoy3466.ManaitaMTK.main;

import com.takoy3466.ManaitaMTK.regi.*;
import com.takoy3466.ManaitaMTK.regi.tab.ManaitaMTKTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("manaitamtk")
public class ManaitaMTK {

    public static final String MOD_ID = "manaitamtk";

    public ManaitaMTK(){
        //最初に読み込む所
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ManaitaMTKTabs.MOD_TABS.register(bus);

        ManaitaMTKItems.ITEMS.register(bus);

        ManaitaMTKBlocks.Blocks.BLOCKS.register(bus);
        ManaitaMTKCraftingTables.BLOCKS.register(bus);

        ManaitaMTKBlocks.BlockItems.BLOCK_ITEMS.register(bus);
        ManaitaMTKCraftingTables.BlockItems.BLOCK_ITEMS.register(bus);

        ManaitaMTKEntities.ENTITY.register(bus);

        ManaitaMTKMenu.MENU_TYPE.register(bus);
    }
}
