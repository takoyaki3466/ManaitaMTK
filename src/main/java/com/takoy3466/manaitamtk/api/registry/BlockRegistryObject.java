package com.takoy3466.manaitamtk.api.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistryObject {
    private final MTKRegistryObject<Block, Item> REGISTRY;

    public BlockRegistryObject(RegistryObject<Block> block, RegistryObject<Item> item) {
        this.REGISTRY = MTKRegistryObject.of(block, item);
    }

    public BlockRegistryObject(MTKRegistryObject<Block, Item> registryObject) {
        this.REGISTRY = registryObject;
    }

    public static BlockRegistryObject of(MTKRegistryObject<Block, Item> registryObject) {
        return new BlockRegistryObject(registryObject);
    }

    public MTKRegistryObject<Block, Item> getRegistry() {
        return REGISTRY;
    }

    public RegistryObject<Block> getFront() {
        return REGISTRY.getRegistryFront();
    }

    public RegistryObject<Item> getBehind() {
        return REGISTRY.getRegistryBehind();
    }

    public Block getBlock() {
        return REGISTRY.getFront();
    }

    public Item getItem() {
        return REGISTRY.getBehind();
    }
}
