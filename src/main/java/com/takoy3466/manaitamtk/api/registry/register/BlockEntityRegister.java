package com.takoy3466.manaitamtk.api.registry.register;

import com.takoy3466.manaitamtk.api.registry.BlockRegistryObject;
import com.takoy3466.manaitamtk.api.registry.MTKRegister;
import com.takoy3466.manaitamtk.api.registry.MTKRegistryObject;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredBlockRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockEntityRegister extends MTKRegister<BlockEntityType<?>> {

    public BlockEntityRegister(String modId) {
        super(ForgeRegistries.BLOCK_ENTITY_TYPES, modId);
    }

    public static BlockEntityRegister create(String modId) {
        return new BlockEntityRegister(modId);
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, Supplier<BlockEntityType<T>> supplier) {
        return this.REIGSTER.register(name, supplier);
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, RegistryObject<Block> block) {
        return register(name, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, MTKRegistryObject<Block, Item> block) {
        return register(name, supplier, block.getRegistryFront());
    }

    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, BlockRegistryObject block) {
        return register(name, supplier, block.getRegistry().getRegistryFront());
    }

    public <T extends BlockEntity, TIER> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, TieredBlockRegistryObject<TIER> block) {
        return register(name, supplier, block.getObject().getRegistry().getRegistryFront());
    }
}
