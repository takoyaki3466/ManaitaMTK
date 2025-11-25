package com.takoy3466.manaitamtk;

import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.init.*;
import com.takoy3466.manaitamtk.init.tab.Tabsinit;
import com.takoy3466.manaitamtk.network.MTKNetwork;
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

        BlocksInit.Blocks.BLOCKS.register(bus);

        ItemsInit.ITEMS.register(bus);

        BlocksInit.BlockItems.BLOCK_ITEMS.register(bus);

        BlocksInit.BlockEntities.BLOCK_ENTITIES.register(bus);
        EntitiesInit.ENTITY.register(bus);


        MenusInit.MENU_TYPES.register(bus);

        SerializersInit.SERIALIZERS.register(bus);

        EnchantmentsInit.ENCHANTMENTS.register(bus);

        Tabsinit.MOD_TABS.register(bus);
        MinecraftForge.EVENT_BUS.register(MTKSubscribeEvent.class);

        MTKNetwork.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MTKConfig.SPEC, "manaitamtk.toml");
    }
}
