package com.takoy3466.manaitamtk.api.registry.register;

import com.takoy3466.manaitamtk.api.registry.MTKRegister;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MenuRegister extends MTKRegister<MenuType<?>> {

    public MenuRegister(String modId) {
        super(ForgeRegistries.MENU_TYPES, modId);
    }

    public static MenuRegister create(String modId) {
        return new MenuRegister(modId);
    }

    public <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, Supplier<MenuType<T>> supplier) {
        return this.REIGSTER.register(name, supplier);
    }

    public <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return register(name, () -> IForgeMenuType.create(factory));
    }
}
