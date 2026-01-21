package com.takoy3466.manaitamtk.api.registry.register;

import com.takoy3466.manaitamtk.api.registry.MTKRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TabRegister extends MTKRegister<CreativeModeTab> {

    public TabRegister(String modId) {
        super(Registries.CREATIVE_MODE_TAB, modId);
    }

    public static TabRegister create(String modId) {
        return new TabRegister(modId);
    }

    public RegistryObject<CreativeModeTab> register(String name, Supplier<CreativeModeTab> supplier) {
        return this.REIGSTER.register(name, supplier);
    }

    public RegistryObject<CreativeModeTab> register(String name, RegistryObject<Item> iconItem, Component title, RegistryObject<? extends Item>[] displayItems) {
        return register(name, () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(iconItem.get()))
                .title(title)
                .displayItems((itemDisplayParameters, output) -> {
                    for (RegistryObject<? extends Item> item : displayItems) {
                        output.accept(item.get());
                    }
                }).build());
    }
}
