package com.takoy3466.ManaitaMTK;

import com.takoy3466.ManaitaMTK.item.armor.FlyWalkAndInvincible;
import com.takoy3466.ManaitaMTK.config.MTKConfig;
import com.takoy3466.ManaitaMTK.regi.*;
import com.takoy3466.ManaitaMTK.regi.tab.ManaitaMTKTabs;
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

        ManaitaMTKItems.ITEMS.register(bus);

        ManaitaMTKBlocks.Blocks.BLOCKS.register(bus);

        ManaitaMTKBlocks.BlockItems.BLOCK_ITEMS.register(bus);

        ManaitaMTKBlocks.BlockEntities.BLOCK_ENTITIES.register(bus);
        ManaitaMTKEntities.ENTITY.register(bus);


        ManaitaMTKMenus.MENU_TYPE.register(bus);

        ManaitaMTKSerializers.SERIALIZERS.register(bus);

        ManaitaMTKEnchantments.ENCHANTMENTS.register(bus);

        ManaitaMTKTabs.MOD_TABS.register(bus);

        MinecraftForge.EVENT_BUS.register(FlyWalkAndInvincible.class);
        MinecraftForge.EVENT_BUS.register(MTKSubscribeEvent.class);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MTKConfig.SPEC, "manaitamtk.toml");
    }
}
