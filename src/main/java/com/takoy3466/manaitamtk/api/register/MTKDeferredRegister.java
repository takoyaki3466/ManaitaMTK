package com.takoy3466.manaitamtk.api.register;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MTKDeferredRegister<FRONT, BEHIND> {
    private final DeferredRegister<FRONT> FRONT_REGISTER;
    private final DeferredRegister<BEHIND> BEHIND_REGISTER;


    public MTKDeferredRegister(IForgeRegistry<FRONT> blockReg, IForgeRegistry<BEHIND> itemReg, String modid) {
        this.FRONT_REGISTER = DeferredRegister.create(blockReg, modid);
        this.BEHIND_REGISTER = DeferredRegister.create(itemReg, modid);
    }

    public static <T, U> MTKDeferredRegister<T, U> create(IForgeRegistry<T> blockReg, IForgeRegistry<U> itemReg, String modid) {
        return new MTKDeferredRegister<>(blockReg, itemReg, modid);
    }

    public RegistryObject<FRONT> registerFront(String name, Supplier<FRONT> supplier) {
        return FRONT_REGISTER.register(name, supplier);
    }

    public RegistryObject<BEHIND> registerBehind(String name, Supplier<BEHIND> supplier) {
        return BEHIND_REGISTER.register(name, supplier);
    }

    public MTKRegistryObject<FRONT, BEHIND> registerAll(RegistryObject<FRONT> front, RegistryObject<BEHIND> behind) {
        return new MTKRegistryObject<>(front, behind);
    }

    public void register(IEventBus bus) {
        this.FRONT_REGISTER.register(bus);
        this.BEHIND_REGISTER.register(bus);
    }
}
