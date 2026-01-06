package com.takoy3466.manaitamtk.api.register;

import net.minecraftforge.registries.RegistryObject;

public class MTKRegistryObject<FRONT, BEHIND> {
    private final RegistryObject<FRONT> front;
    private final RegistryObject<BEHIND> behind;

    public MTKRegistryObject(RegistryObject<FRONT> front, RegistryObject<BEHIND> behind) {
        this.front = front;
        this.behind = behind;
    }

    public FRONT getBlock() {
        return this.front.get();
    }

    public BEHIND getItem() {
        return this.behind.get();
    }
}
