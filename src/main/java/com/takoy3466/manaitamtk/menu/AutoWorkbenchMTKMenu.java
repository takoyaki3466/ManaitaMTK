package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.block.blockEntity.AutoWorkbenchMTKBlockEntity;
import com.takoy3466.manaitamtk.init.BlocksInit;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AutoWorkbenchMTKMenu extends RecipeBookMenu<Container> {
    private final CraftingContainer craftSlots;
    private final AutoWorkbenchMTKBlockEntity blockEntity;
    private final ResultContainer resultSlots;
    private final ContainerLevelAccess access;
    private final Player player;
    private CraftingRecipe recipe;

    public AutoWorkbenchMTKMenu(int id, Inventory playerInventory, BlockPos pos) {
        super(MenuType.CRAFTING, id);
        BlockEntity be = playerInventory.player.level().getBlockEntity(pos);
        if (be instanceof AutoWorkbenchMTKBlockEntity autoCraftingMTKBlockEntity){
            this.blockEntity = autoCraftingMTKBlockEntity;
        } else {
            throw new IllegalStateException(be.getClass().getCanonicalName() + "と AutoWorkbenchMTKBlockEntity クラスは違うよ！");
        }
        this.player = playerInventory.player;
        this.access = ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos());

        this.craftSlots = new TransientCraftingContainer(this, 3, 3, this.blockEntity.getItems());
        this.resultSlots = new ResultContainer();
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35){
            @Override
            public boolean mayPickup(Player player) {
                return false;
            }
        });

        this.slotsChanged(this.resultSlots);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    protected void slotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player player, CraftingContainer craftingContainer, ResultContainer resultContainer) {
        if (!level.isClientSide) {
            ServerPlayer sPlayer = (ServerPlayer)player;
            ItemStack stack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optionalRecipe = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingContainer, level);
            if (optionalRecipe.isPresent()) {
                CraftingRecipe recipe = optionalRecipe.get();
                this.blockEntity.setRecipe(recipe);
                this.recipe = recipe;
                this.blockEntity.setContainer(craftingContainer);
                if (resultContainer.setRecipeUsed(level, sPlayer, recipe)) {
                    ItemStack result = recipe.assemble(craftingContainer, level.registryAccess());
                    if (result.isItemEnabled(level.enabledFeatures())) {
                        stack = result;
                        stack.setCount(stack.getCount());
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
        this.access.execute((level, pos) -> slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots));
        if (recipe != null) {
            this.resultSlots.setItem(0, this.recipe.getResultItem(this.player.level().registryAccess()));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (i == 0) {
                this.access.execute((level, pos) -> stack1.getItem().onCraftedBy(stack1, level, player));
                if (!this.moveItemStackTo(stack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack1, stack);
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
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack1);
            if (i == 0) {
                player.drop(stack1, false);
            }
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, BlocksInit.Blocks.AUTO_WORKBENCH_MTK.get());
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        if (this.blockEntity instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible)this.blockEntity).fillStackedContents(stackedContents);
        }
    }

    @Override
    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultSlots.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super Container> recipe) {
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

    public AutoWorkbenchMTKBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
