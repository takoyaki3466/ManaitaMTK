package com.takoy3466.manaitamtk.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

public abstract class MTKRegister<T> {
    protected final DeferredRegister<T> REIGSTER;

    public MTKRegister(IForgeRegistry<T> registry, String modId) {
        this.REIGSTER = DeferredRegister.create(registry, modId);
    }

    public MTKRegister(ResourceKey<? extends Registry<T>> key, String modId) {
        this.REIGSTER = DeferredRegister.create(key, modId);
    }

    public void register(IEventBus bus) {
        REIGSTER.register(bus);
    }
}
