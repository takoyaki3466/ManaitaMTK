package com.takoy3466.manaitamtk.api.registry.tiered;

import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ClassCanBeRecord")
public class TieredRegistryObject<OBJECT, TIER> {
    private final RegistryObject<OBJECT> registry;
    private final TIER tier;

    public TieredRegistryObject(RegistryObject<OBJECT> registry, TIER tier) {
        this.registry = registry;
        this.tier = tier;
    }

    public static <U, V> TieredRegistryObject of(RegistryObject<U> registry, V tier) {
        return new TieredRegistryObject(registry, tier);
    }

    public OBJECT get() {
        return registry.get();
    }

    public RegistryObject<OBJECT> getRegistry() {
        return registry;
    }

    public TIER getTier() {
        return tier;
    }
}
