package com.takoy3466.manaitamtk.api.registry.tiered;

import com.takoy3466.manaitamtk.api.registry.BlockRegistryObject;
import com.takoy3466.manaitamtk.api.registry.MTKRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ClassCanBeRecord")
public class TieredBlockRegistryObject<TIER> {
    private final BlockRegistryObject object;
    private final TIER tier;

    public TieredBlockRegistryObject(BlockRegistryObject object, TIER tier) {
        this.object = object;
        this.tier = tier;
    }

    public static <T> TieredBlockRegistryObject<T> of(BlockRegistryObject object, T tier) {
        return new TieredBlockRegistryObject<>(object, tier);
    }

    public static <T> TieredBlockRegistryObject<T> of(MTKRegistryObject<Block, Item> object, T tier) {
        return new TieredBlockRegistryObject<>(BlockRegistryObject.of(object), tier);
    }

    public RegistryObject<Block> getFront() {
        return object.getFront();
    }

    public RegistryObject<Item> getBehind() {
        return object.getBehind();
    }

    public Block getBlock() {
        return object.getBlock();
    }

    public Item getItem() {
        return object.getItem();
    }

    public BlockRegistryObject getObject() {
        return object;
    }

    public TIER getTier() {
        return tier;
    }
}
