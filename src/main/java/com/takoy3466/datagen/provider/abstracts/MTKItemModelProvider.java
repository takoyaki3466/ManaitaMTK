package com.takoy3466.datagen.provider.abstracts;

import com.takoy3466.manaitamtk.api.registry.MTKRegistryObject;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("UnusedReturnValue")
public abstract class MTKItemModelProvider extends ItemModelProvider {

    public MTKItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    protected ItemModelBuilder basicItem(RegistryObject<Item> item) {
        return basicItem(item.get());
    }

    protected ItemModelBuilder basicItem(MTKRegistryObject<Block, Item> block) {
        return basicItem(block.getBehind());
    }

    protected <TIER> ItemModelBuilder basicItem(TieredRegistryObject<Item, TIER> item) {
        return basicItem(item.getRegistry());
    }
}
