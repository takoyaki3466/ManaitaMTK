package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface IMTKFurnace extends IMTKMultiple {

    int getLitTime();
    void setLitTime(int litTime);

    int getLitDuration();
    void setLitDuration(int litDuration);

    int getCookingProgress();
    void setCookingProgress(int cookingProgress);

    int getCookingTotalTime();
    void setCookingTotalTime(int totalTime);

    NonNullList<ItemStack> getItems();
    void setItems(NonNullList<ItemStack> items);

    RecipeType<?> getRecipeType();

    ContainerData getContainerData();

    boolean isEmpty();

    ItemStack getItem(int slotId);

    void setItem(int slotId, ItemStack stack);

    void setRecipesUsed(@Nullable Recipe<?> recipe);

    void fillStackedContents(StackedContents stackedContents);

    RecipeType<?> getRecipeTypeFromStack(ItemStack ingredientStack);

    void saveAdditional(CompoundTag tag);
    void load(CompoundTag tag);

    void tick();

    boolean isLit();
    int getLitProgress();
    int getBurnProgress();
}
