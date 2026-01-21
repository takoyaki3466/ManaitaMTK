package com.takoy3466.manaitamtk.api.registry.tiered;

import com.takoy3466.manaitamtk.api.registry.MTKDeferredRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TieredBlockRegister<TIER> {
    private final MTKDeferredRegister<Block, Item> REGISTER;
    public TieredBlockRegister(String modId) {
        this.REGISTER = MTKDeferredRegister.create(ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS, modId);
    }

    public static <T> TieredBlockRegister<T> create(String modId) {
        return new TieredBlockRegister<>(modId);
    }

    public TieredBlockRegistryObject<TIER> register(String name, Supplier<Block> supplier, Item.Properties properties, TIER tier) {
        RegistryObject<Block> block = REGISTER.registerFront(name, supplier);
        RegistryObject<Item> item = REGISTER.registerBehind(name, () -> new BlockItem(block.get(), properties));
        return TieredBlockRegistryObject.of(REGISTER.getMTKRegistry(block, item), tier);
    }

    public MTKDeferredRegister<Block, Item> getRegister() {
        return REGISTER;
    }

    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
