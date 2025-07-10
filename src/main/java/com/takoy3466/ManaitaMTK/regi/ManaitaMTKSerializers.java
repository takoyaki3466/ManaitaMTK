package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.recipe.CrushedMTKRecipe;
import com.takoy3466.ManaitaMTK.recipe.CrushedMTKRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ManaitaMTK.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CrushedMTKRecipe>> CRUSHED_MTK_SERIALIZER =
            SERIALIZERS.register("crushed_mtk_serializer", CrushedMTKRecipeSerializer::new);
}
