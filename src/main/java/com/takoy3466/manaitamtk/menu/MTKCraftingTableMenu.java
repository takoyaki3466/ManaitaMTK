package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.abstracts.RecipeBookMenuMultipler;
import com.takoy3466.manaitamtk.api.interfaces.IMultipleRecipeResult;
import com.takoy3466.manaitamtk.util.slot.MTKSlot;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MTKCraftingTableMenu extends RecipeBookMenuMultipler<CraftingContainer> implements IMultipleRecipeResult {
    private final CraftingContainer craftSlots;
    private final ResultContainer resultSlots;
    private final ContainerLevelAccess access;
    private final Player player;

    public MTKCraftingTableMenu(int id, Inventory playerInventory, ContainerLevelAccess access, int multiple) {
        super(MenuType.CRAFTING, id, multiple);
        this.craftSlots = new TransientCraftingContainer(this, 3, 3);
        this.resultSlots = new ResultContainer();
        this.access = access;
        this.player = playerInventory.player;
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new MTKSlot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new MTKSlot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new MTKSlot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    public MTKCraftingTableMenu(int id, Inventory playerInventory, ContainerLevelAccess access, MTKTier mtkTier) {
        super(MenuType.CRAFTING, id, mtkTier);
        this.craftSlots = new TransientCraftingContainer(this, 3, 3);
        this.resultSlots = new ResultContainer();
        this.access = access;
        this.player = playerInventory.player;
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new MTKSlot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new MTKSlot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new MTKSlot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    protected void slotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftingContainer, ResultContainer resultContainer) {
        if (!level.isClientSide()) {
            ServerPlayer sPlayer = (ServerPlayer)player;
            ItemStack stack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optionalRecipe = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingContainer, level);
            if (optionalRecipe.isPresent()) {
                CraftingRecipe recipe = optionalRecipe.get();
                if (resultContainer.setRecipeUsed(level, sPlayer, recipe)) {
                    ItemStack result = recipe.assemble(craftingContainer, level.registryAccess());
                    if (result.isItemEnabled(level.enabledFeatures())) {
                        stack = result;
                        this.multipler(stack);
                    }
                }
            }

            resultContainer.setItem(0, stack);
            menu.setRemoteSlot(0, stack);
            sPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, stack));
        }
    }

    @Override
    public void slotsChanged(@NotNull Container container) {
        this.access.execute((level, pos) -> {
            // CrushedMTKの処理を持ってきた
            if (this.multipleMatche((CraftingContainer) container)){
                setItem(0,1, this.multipleAssemble((CraftingContainer) container, getMultiple()));
                this.resultSlots.setChanged();
            } else {
                slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots);
            }
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
            for (int i = 0; i < this.craftSlots.getContainerSize(); i++) {
                ItemStack stack = this.craftSlots.removeItemNoUpdate(i);
                if (!stack.isEmpty()) {
                    player.getInventory().placeItemBackInInventory(stack);
                }
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack stack = ItemStack.EMPTY;
        Slot $$3 = this.slots.get(i);
        if ($$3.hasItem()) {
            ItemStack stack1 = $$3.getItem();
            stack = stack1.copy();
            if (i == 0) {
                this.access.execute((level, pos) -> stack1.getItem().onCraftedBy(stack1, level, player));
                if (!this.moveItemStackTo(stack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                $$3.onQuickCraft(stack1, stack);
            } else if (i >= 10 && i < 46) {
                if (!this.moveItemStackTo(stack1, 1, 10, false)) {
                    if (i < 37) {
                        if (!this.moveItemStackTo(stack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(stack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(stack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                $$3.setByPlayer(ItemStack.EMPTY);
            } else {
                $$3.setChanged();
            }

            if (stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            $$3.onTake(player, stack1);
            if (i == 0) {
                player.drop(stack1, false);
            }
        }

        return stack;
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents itemHelper) {
        this.craftSlots.fillStackedContents(itemHelper);
    }

    @Override
    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super CraftingContainer> recipe) {
        return recipe.matches(this.craftSlots, this.player.level());
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    @Override
    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int index) {
        return index != getResultSlotIndex();
    }
}