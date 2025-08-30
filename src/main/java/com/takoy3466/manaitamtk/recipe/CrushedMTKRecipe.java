package com.takoy3466.manaitamtk.recipe;

import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.regi.ManaitaMTKItems;
import com.takoy3466.manaitamtk.regi.ManaitaMTKSerializers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

@SuppressWarnings("NullableProblems")
public class CrushedMTKRecipe implements CraftingRecipe {
    private final ResourceLocation resourceLocation;

    public CrushedMTKRecipe(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean source = false;
        boolean item = false;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == ManaitaMTKItems.CRUSHED_MTK.get()) {
                    if (!source) {source = true;}
                    else {
                        if (item) {return false;}
                        item = true;
                    }
                } else {
                    if (item) {return false;}
                    item = true;
                }
            }
        }
        return source && item;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        int source = 0;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStackInSlot = container.getItem(i);
            if (!itemStackInSlot.isEmpty() && itemStackInSlot.getItem() != ManaitaMTKItems.CRUSHED_MTK.get()) {
                itemStack = itemStackInSlot;
            }

            if (!itemStackInSlot.isEmpty() && itemStackInSlot.getItem() == ManaitaMTKItems.CRUSHED_MTK.get()) {
                ++source;
            }
        }

        ItemStack result;
        if (source == 2) {
            result = new ItemStack(ManaitaMTKItems.CRUSHED_MTK.get());
            //result.setCount(64);
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get());
            return result;
        } else if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            result = itemStack.copy();
            //result.setCount(64);
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get());
            return result;
        }
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ManaitaMTKItems.CRUSHED_MTK.get().asItem().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return this.resourceLocation;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ManaitaMTKSerializers.CRUSHED_MTK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }
}
