package com.takoy3466.ManaitaMTK.Menu;

import com.takoy3466.ManaitaMTK.block.craftingmanaita.DoubleCraftingTableBlock;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
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

            if (recipe.isPresent()) {
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
}