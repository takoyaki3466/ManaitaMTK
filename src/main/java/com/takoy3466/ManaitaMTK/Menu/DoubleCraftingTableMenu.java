package com.takoy3466.ManaitaMTK.Menu;

import com.takoy3466.ManaitaMTK.config.MTKConfig;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DoubleCraftingTableMenu extends CraftingMenu {
    private final ContainerLevelAccess access;
    private final CraftingContainer craftingContainer;
    private final ResultContainer resultContainer;
    private final int magnification;

    public DoubleCraftingTableMenu(int id, Inventory playerInventory, ContainerLevelAccess access, int mag) {
        super(id, playerInventory, access);

        this.access = access;
        this.craftingContainer = new TransientCraftingContainer(this, 3, 3);
        this.resultContainer = new ResultContainer();
        magnification = mag;
    }

    @Override
    public void slotsChanged(@NotNull Container container) {
        this.access.execute((level, pos) -> {
            if (level.isClientSide()) {
                return;
            }
            // レシピマネージャーの取得
            RecipeManager recipeManager = level.getRecipeManager();
            // this.craftingContainer.setChanged();

            // クラフトレシピの検索
            Optional<CraftingRecipe> recipe = recipeManager.getRecipeFor(RecipeType.CRAFTING, (CraftingContainer) container, level);

            // CrushedMTKの処理を持ってきた
            if (matchesD((CraftingContainer) container)){
                setItem(0,1, assembleD((CraftingContainer) container));
                this.resultContainer.setChanged();
            }
            else if (recipe.isPresent()) {
                ItemStack result = recipe.get().assemble(this.craftingContainer, level.registryAccess());

                if (!result.isEmpty()) {
                    result.setCount(result.getCount() * magnification);// クラフト結果をn倍にする
                    this.resultContainer.setItem(0, result);
                    setItem(0,1, result);
                    this.resultContainer.setChanged();

                } else {
                    this.resultContainer.setItem(0, ItemStack.EMPTY);
                    setItem(0, 0, ItemStack.EMPTY);
                    this.resultContainer.setChanged();
                }

            } else {
                this.resultContainer.setItem(0, ItemStack.EMPTY);
                setItem(0, 0, ItemStack.EMPTY);
                this.resultContainer.setChanged();
            }
            this.broadcastChanges();
        });
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> {
            for (int i = 0; i < this.craftingContainer.getContainerSize(); i++) {
                ItemStack stack = this.craftingContainer.removeItemNoUpdate(i);
                if (!stack.isEmpty()) {
                    player.getInventory().placeItemBackInInventory(stack);
                }
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return super.quickMoveStack(player, i);
    }

    public boolean matchesD(CraftingContainer container) {
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

    // * magnificationを追加してちょっと変えてる
    public ItemStack assembleD(CraftingContainer container) {
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
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get() * magnification);
            return result;
        } else if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            result = itemStack.copy();
            //result.setCount(64);
            result.setCount(MTKConfig.CRUSHED_MTK_MAGNIFICATION.get() * magnification);
            return result;
        }
    }
}