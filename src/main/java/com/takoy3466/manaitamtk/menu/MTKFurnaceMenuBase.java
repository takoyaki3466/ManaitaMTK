package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.util.slot.MTKFurnaceFuelSlot;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntityBase;
import com.takoy3466.manaitamtk.init.MenusInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;

public class MTKFurnaceMenuBase extends RecipeBookMenu<Container> {
    private final ContainerData containerData;
    private final Level level;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipeBookType recipeBookType;

    public final MTKFurnaceBlockEntityBase blockEntity;

    public MTKFurnaceMenuBase(int id, Inventory playerInventory, FriendlyByteBuf buf, MTKEnum mtkEnum) {
        this(id, playerInventory, buf.readBlockPos(), mtkEnum);
    }

    public MTKFurnaceMenuBase(int id, Inventory playerInventory, BlockPos pos, MTKEnum mtkEnum) {
        super(switch (mtkEnum) {
            case WOOD -> MenusInit.MTK_FURNACE_WOOD.get();
            case STONE -> MenusInit.MTK_FURNACE_STONE.get();
            case IRON -> MenusInit.MTK_FURNACE_IRON.get();
            case GOLD -> MenusInit.MTK_FURNACE_GOLD.get();
            case DIAMOND -> MenusInit.MTK_FURNACE_DIAMOND.get();
            case MTK -> MenusInit.MTK_FURNACE_MTK.get();
            case GODMTK -> MenusInit.MTK_FURNACE_GODMTK.get();
            case BREAK -> MenusInit.MTK_FURNACE_BREAK.get();
            default -> null;
        }, id);
        this.recipeType = RecipeType.SMELTING;
        this.recipeBookType = RecipeBookType.FURNACE;
        this.level = playerInventory.player.level();

        BlockEntity be = playerInventory.player.level().getBlockEntity(pos);
        if (be instanceof MTKFurnaceBlockEntityBase furnaceBlockEntity){
            this.blockEntity = furnaceBlockEntity;
        } else {
            throw new IllegalStateException(be.getClass().getCanonicalName() + "と MTKFurnaceBlockEntityBase クラスは違うよ！");
        }

        this.containerData = blockEntity.dataAccess;

        this.addSlot(new Slot(blockEntity, 0, 56, 17));
        this.addSlot(new MTKFurnaceFuelSlot(this, blockEntity, 1, 56, 53));
        this.addSlot(new FurnaceResultSlot(playerInventory.player, blockEntity, 2, 116, 35));
        
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addDataSlots(containerData);
    }

    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        if (this.blockEntity instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible)this.blockEntity).fillStackedContents(stackedContents);
        }

    }

    public void clearCraftingContent() {
        this.getSlot(0).set(ItemStack.EMPTY);
        this.getSlot(2).set(ItemStack.EMPTY);
    }

    public boolean recipeMatches(Recipe<? super Container> recipe) {
        return recipe.matches(this.blockEntity, this.level);
    }

    public int getResultSlotIndex() {
        return 2;
    }

    public int getGridWidth() {
        return 1;
    }

    public int getGridHeight() {
        return 1;
    }

    public int getSize() {
        return 3;
    }

    public RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    public boolean shouldMoveToInventory(int i) {
        return i != 1;
    }

    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
    }

    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (i == 2) {
                if (!this.moveItemStackTo(stack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack1, stack);
            } else if (i != 1 && i != 0) {
                if (this.canSmelt(stack1)) {
                    if (!this.moveItemStackTo(stack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(stack1)) {
                    if (!this.moveItemStackTo(stack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (i >= 3 && i < 30) {
                    if (!this.moveItemStackTo(stack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (i >= 30 && i < 39 && !this.moveItemStackTo(stack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack1, 3, 39, false)) {
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
        }

        return stack;
    }

    private boolean canSmelt(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor(this.recipeType, new SimpleContainer(new ItemStack[]{stack}), this.level).isPresent();
    }

    public boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, this.recipeType) > 0;
    }

    public boolean isLit() {
        return this.containerData.get(0) > 0;
    }

    public int getBurnProgress() {
        int progress = this.containerData.get(2);
        int total = this.containerData.get(3);
        return total != 0 ? progress * 24 / total : 0;
    }

    public int getLitProgress() {
        int litTime = this.containerData.get(0);
        int litDuration = this.containerData.get(1);
        return litDuration != 0 ? litTime * 13 / litDuration : 0;
    }

    public MTKFurnaceBlockEntityBase getBlockEntity() {
        return this.blockEntity;
    }
}
