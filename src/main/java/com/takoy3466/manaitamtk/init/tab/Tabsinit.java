package com.takoy3466.manaitamtk.init.tab;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.registry.register.TabRegister;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class Tabsinit {
    public static final TabRegister TABS = TabRegister.create(ManaitaMTK.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MTK_MAIN = TABS.register(
            "mtk_main", ItemsInit.ITEM_MTK, Component.translatable("itemGroup.mtk_main"), ManaitaTabItems.items);
}
