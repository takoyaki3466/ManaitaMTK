package com.takoy3466.manaitamtk;

import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.init.*;
import com.takoy3466.manaitamtk.init.tab.ManaitaMTKTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("manaitamtk")
public class ManaitaMTK {

    public static final String MOD_ID = "manaitamtk";

    public ManaitaMTK(){
        //最初に読み込む所
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ManaitaMTKBlocks.Blocks.BLOCKS.register(bus);

        ManaitaMTKItems.ITEMS.register(bus);

        ManaitaMTKBlocks.BlockItems.BLOCK_ITEMS.register(bus);

        ManaitaMTKBlocks.BlockEntities.BLOCK_ENTITIES.register(bus);
        ManaitaMTKEntities.ENTITY.register(bus);


        ManaitaMTKMenus.MENU_TYPES.register(bus);

        ManaitaMTKSerializers.SERIALIZERS.register(bus);

        ManaitaMTKEnchantments.ENCHANTMENTS.register(bus);

        ManaitaMTKTabs.MOD_TABS.register(bus);
        MinecraftForge.EVENT_BUS.register(MTKSubscribeEvent.class);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MTKConfig.SPEC, "manaitamtk.toml");
    }
}
