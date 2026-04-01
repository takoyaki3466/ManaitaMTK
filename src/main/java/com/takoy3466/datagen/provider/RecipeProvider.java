package com.takoy3466.datagen.provider;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.core.registry.tiered.TieredBlockRegistryObject;
import com.takoy3466.manaitamtk.core.registry.tiered.TieredRegistryObject;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

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

        addPortableFurnace(consumer, ItemsInit.PORTABLE_WOOD_FURNACE, ItemsInit.PORTABLE_WOOD_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_STONE_FURNACE, ItemsInit.PORTABLE_STONE_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_IRON_FURNACE, ItemsInit.PORTABLE_IRON_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_GOLD_FURNACE, ItemsInit.PORTABLE_GOLD_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_DIAMOND_FURNACE, ItemsInit.PORTABLE_DIAMOND_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_MTK_FURNACE, ItemsInit.PORTABLE_MTK_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_GODMTK_FURNACE, ItemsInit.PORTABLE_GODMTK_CRAFTING_TABLE);
        addPortableFurnace(consumer, ItemsInit.PORTABLE_BREAK_FURNACE, ItemsInit.PORTABLE_BREAK_CRAFTING_TABLE);

        addMultiFurnace(consumer, BlocksInit.WOOD_MULTI_FURNACE, ItemsInit.PORTABLE_WOOD_FURNACE);
        addMultiFurnace(consumer, BlocksInit.STONE_MULTI_FURNACE, ItemsInit.PORTABLE_STONE_FURNACE);
        addMultiFurnace(consumer, BlocksInit.IRON_MULTI_FURNACE, ItemsInit.PORTABLE_IRON_FURNACE);
        addMultiFurnace(consumer, BlocksInit.GOLD_MULTI_FURNACE, ItemsInit.PORTABLE_GOLD_FURNACE);
        addMultiFurnace(consumer, BlocksInit.DIAMOND_MULTI_FURNACE, ItemsInit.PORTABLE_DIAMOND_FURNACE);
        addMultiFurnace(consumer, BlocksInit.MTK_MULTI_FURNACE, ItemsInit.PORTABLE_MTK_FURNACE);
        addMultiFurnace(consumer, BlocksInit.GODMTK_MULTI_FURNACE, ItemsInit.PORTABLE_GODMTK_FURNACE);
        addMultiFurnace(consumer, BlocksInit.BREAK_MULTI_FURNACE, ItemsInit.PORTABLE_BREAK_FURNACE);
    }

    private void addPortableFurnace(Consumer<FinishedRecipe> consumer, TieredRegistryObject<Item, MTKTier> target, TieredRegistryObject<Item, MTKTier> ingredient) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.get())
                .requires(ingredient.get())
                .requires(Blocks.FURNACE)
                .unlockedBy("hasItem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsInit.ITEM_MTK.get()))
                .save(consumer);
    }

    private void addMultiFurnace(Consumer<FinishedRecipe> consumer, TieredBlockRegistryObject<MTKTier> target, TieredRegistryObject<Item, MTKTier> ingredient) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, target.getItem())
                .requires(ingredient.get())
                .unlockedBy("hasItem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsInit.ITEM_MTK.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient.get())
                .requires(target.getBlock())
                .unlockedBy("hasItem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsInit.ITEM_MTK.get()))
                .save(consumer, new ResourceLocation(ManaitaMTK.MOD_ID, target.getItem().getDescriptionId() + "_reverse"));
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
