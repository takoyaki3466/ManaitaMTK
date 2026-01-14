package com.takoy3466.manaitamtk;

import com.takoy3466.manaitamtk.api.MTKBlockList;
import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.eventSubscriber.MTKSubscribeEvent;
import com.takoy3466.manaitamtk.init.*;
import com.takoy3466.manaitamtk.init.tab.Tabsinit;
import com.takoy3466.manaitamtk.network.MTKNetwork;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.logging.Logger;

@Mod("manaitamtk")
public class ManaitaMTK {

    public static final String MOD_ID = "manaitamtk";
    public static Logger LOGGER = Logger.getLogger(MOD_ID);

    public ManaitaMTK(){
        //最初に読み込む所
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlocksInit.BLOCKS.register(bus);
        BlocksInit.TIER_BLOCKS.register(bus);

        ItemsInit.ITEMS.register(bus);
        ItemsInit.TIER_ITEM.register(bus);

        BlockEntitiesInit.BLOCK_ENTITIES.register(bus);
        EntitiesInit.ENTITY.register(bus);


        MenusInit.MENUS.register(bus);

        SerializersInit.SERIALIZERS.register(bus);

        EnchantmentsInit.ENCHANTMENTS.register(bus);

        Tabsinit.TABS.register(bus);
        MinecraftForge.EVENT_BUS.register(MTKSubscribeEvent.class);

        MTKNetwork.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MTKConfig.SPEC, "manaitamtk.toml");

        MTKBlockList.init();
    }
}
