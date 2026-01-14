package com.takoy3466.manaitamtk.init.tab;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.registry.register.TabRegister;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class Tabsinit {
    public static final TabRegister TABS = TabRegister.create(ManaitaMTK.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MTK_MAIN = TABS.register(
            "mtk_main", ItemsInit.ITEM_MTK, Component.translatable("itemGroup.mtk_main"), ManaitaTabItems.items);
}
