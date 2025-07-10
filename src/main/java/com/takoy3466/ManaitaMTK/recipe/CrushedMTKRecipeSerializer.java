package com.takoy3466.ManaitaMTK.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class CrushedMTKRecipeSerializer implements RecipeSerializer<CrushedMTKRecipe> {
    @Override
    public CrushedMTKRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        return new CrushedMTKRecipe(resourceLocation);
    }

    @Override
    public @Nullable CrushedMTKRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        return new CrushedMTKRecipe(resourceLocation);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, CrushedMTKRecipe crushedMTKRecipe) {

    }
}
