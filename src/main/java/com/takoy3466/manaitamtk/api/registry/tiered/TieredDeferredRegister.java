package com.takoy3466.manaitamtk.api.registry.tiered;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public class TieredDeferredRegister<OBJECT, TIER> {
    private final DeferredRegister<OBJECT> REGISTER;

    public TieredDeferredRegister(IForgeRegistry<OBJECT> registry, String modId) {
        this.REGISTER = DeferredRegister.create(registry, modId);
    }

    public static <T, U> TieredDeferredRegister<T, U> create(IForgeRegistry<T> registry, String modId) {
        return new TieredDeferredRegister<>(registry, modId);
    }

    public TieredRegistryObject<OBJECT, TIER> register(String name, Supplier<OBJECT> supplier, TIER tier) {
        return TieredRegistryObject.of(REGISTER.register(name, supplier), tier);
    }

    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
