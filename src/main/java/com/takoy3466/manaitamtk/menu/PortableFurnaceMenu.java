package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.PortableFurnaceData;
import com.takoy3466.manaitamtk.api.capability.interfaces.IPortableFurnace;
import com.takoy3466.manaitamtk.api.helper.MTKMenuHelper;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import com.takoy3466.manaitamtk.api.interfaces.ISaveLoad;
import com.takoy3466.manaitamtk.util.slot.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class PortableFurnaceMenu extends AbstractContainerMenu implements IHasCapability {
    private final IPortableFurnace<MTKItemStackHandler> furnace;
    private final ContainerData containerData;
    private final Player player;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final ItemStack stack;

    @SuppressWarnings("unchecked")
    public PortableFurnaceMenu(int id, Inventory playerInventory, ItemStack stack) {
        super(MenuType.FURNACE, id);
        this.player = playerInventory.player;
        LazyOptional<IPortableFurnace> lazyOptional = stack.getCapability(MTKCapabilities.PORTABLE_FURNACE);
        this.furnace = lazyOptional.orElseThrow(() -> new IllegalStateException("IPortableFurnaceがないよ！"));
        this.containerData = new PortableFurnaceData(furnace);
        this.recipeType = RecipeType.SMELTING;
        this.stack = stack;

        this.addSlot(new MTKSlotItemHandler(this.furnace.gethandler(), 0, 56, 17)); // importSlot
        this.addSlot(new MTKFurnaceFuelHandler(this, this.furnace.gethandler(), 1, 56, 53)); // fuelSlot
        this.addSlot(new MTKFurnaceResultSlot(this.player, this.furnace.gethandler(), 2, 116, 35)); // resultSlot

        this.addDataSlots(this.containerData);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new MTKSlot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new MTKSlot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
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

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int i, int i1, boolean b) {
        return MTKMenuHelper.moveItemStackTo(this.slots, stack, i, i1, b);
    }

    private boolean canSmelt(ItemStack stack) {
        return this.player.level().getRecipeManager().getRecipeFor(this.recipeType, new SimpleContainer(stack), this.player.level()).isPresent();
    }

    public boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, this.recipeType) > 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.execute(MTKCapabilities.PORTABLE_FURNACE, stack, INBTSerializable::serializeNBT);
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
}
