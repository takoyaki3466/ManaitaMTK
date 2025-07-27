package com.takoy3466.ManaitaMTK.regi.tab;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKTabs {

    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ManaitaMTK.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MTK_MAIN = MOD_TABS.register("mtk_main",
            ()-> {return CreativeModeTab.builder()
                    .icon(()->new ItemStack(ManaitaMTKItems.ITEM_MTK.get()))
                    .title(Component.translatable("itemGroup.mtk_main"))
                    .displayItems((pram,output)->{
                        for(Item item: ManaitaTabItems.items){
                            output.accept(item);
                        }
                    })
                    .build();
    });
}
