package com.takoy3466.manaitamtk.api.registry;

import net.minecraftforge.registries.RegistryObject;

public class MTKRegistryObject<FRONT, BEHIND> {
    private final RegistryObject<FRONT> front;
    private final RegistryObject<BEHIND> behind;

    public MTKRegistryObject(RegistryObject<FRONT> front, RegistryObject<BEHIND> behind) {
        this.front = front;
        this.behind = behind;
    }

    public static <T, U> MTKRegistryObject<T, U> of(RegistryObject<T> front, RegistryObject<U> behind) {
        return new MTKRegistryObject<>(front, behind);
    }

    public RegistryObject<FRONT> getRegistryFront() {
        return this.front;
    }

    public RegistryObject<BEHIND> getRegistryBehind() {
        return this.behind;
    }

    public FRONT getFront() {
        return this.front.get();
    }

    public BEHIND getBehind() {
        return this.behind.get();
    }
}
