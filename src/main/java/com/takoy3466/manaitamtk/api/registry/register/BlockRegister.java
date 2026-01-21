package com.takoy3466.manaitamtk.api.registry.register;

import com.takoy3466.manaitamtk.api.registry.BlockRegistryObject;
import com.takoy3466.manaitamtk.api.registry.MTKDeferredRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegister {
    private final MTKDeferredRegister<Block, Item> REGISTER;
    public BlockRegister(String modId) {
        this.REGISTER = MTKDeferredRegister.create(ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS, modId);
    }

    public static BlockRegister create(String modId) {
        return new BlockRegister(modId);
    }

    public BlockRegistryObject register(String name, Supplier<Block> supplier, Item.Properties properties) {
        RegistryObject<Block> block = REGISTER.registerFront(name, supplier);
        RegistryObject<Item> item = REGISTER.registerBehind(name, () -> new BlockItem(block.get(), properties));
        return BlockRegistryObject.of(REGISTER.getMTKRegistry(block, item));
    }

    public MTKDeferredRegister<Block, Item> getRegister() {
        return REGISTER;
    }

    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
