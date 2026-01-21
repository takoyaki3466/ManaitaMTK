package com.takoy3466.datagen.provider;

import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    private final List<Item> furnaceList = List.of(
            Items.FURNACE,
            BlocksInit.WOOD_MTK_FURNACE.getItem(),
            BlocksInit.STONE_MTK_FURNACE.getItem(),
            BlocksInit.IRON_MTK_FURNACE.getItem(),
            BlocksInit.GOLD_MTK_FURNACE.getItem(),
            BlocksInit.DIAMOND_MTK_FURNACE.getItem(),
            BlocksInit.MTK_MTK_FURNACE.getItem(),
            BlocksInit.GODMTK_MTK_FURNACE.getItem(),
            BlocksInit.BREAK_MTK_FURNACE.getItem()
    );

    public RecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        for (int i = 0; i < this.furnaceList.size() - 1; i++) {
            addFurnaceRecipe(consumer, this.furnaceList.get(i + 1), this.furnaceList.get(i));
        }
    }

    private void addCraftTableRecipe(Consumer<FinishedRecipe> consumer, ItemLike target, ItemLike ingredient, ItemLike aroundItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, target)
                .pattern("###")
                .pattern("CCC")
                .pattern("###")
                .define('C', ingredient)
                .define('#', aroundItem)
                .unlockedBy("hasItem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsInit.ITEM_MTK.get()))
                .save(consumer);
    }

    private void addFurnaceRecipe(Consumer<FinishedRecipe> consumer, ItemLike target, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, target)
                .pattern("#C#")
                .define('C', ingredient)
                .define('#', ItemsInit.ITEM_MTK.get())
                .unlockedBy("hasItem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsInit.ITEM_MTK.get()))
                .save(consumer);
    }
}
