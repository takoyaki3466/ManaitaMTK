package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.api.interfaces.ISaveLoad;
import com.takoy3466.manaitamtk.api.helper.MTKMenuHelper;
import com.takoy3466.manaitamtk.util.slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.util.slot.MTKSlot;
import com.takoy3466.manaitamtk.util.slot.MTKSlotItemHandler;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.init.MenusInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MTKBackpackMenu extends AbstractContainerMenu implements ISaveLoad {
    private final int containerRows = 6;
    private final MTKItemStackHandler stackHandler;
    private final ItemStack stack;

    public MTKBackpackMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, ItemStack.EMPTY);
    }

    public MTKBackpackMenu(int id, Inventory playerInventory, ItemStack stack) {
        super(MenusInit.MTK_BACKPACK.get(), id);
        stackHandler = new MTKItemStackHandler(54) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                saveAdditional(stack.getOrCreateTag(), stackHandler);
            }
        };
        this.stack = stack;
        int i = (this.containerRows - 4) * 18;
        // 自スロット
        for (int j = 0; j < this.containerRows; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new MTKSlotItemHandler(this.stackHandler, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new MTKSlot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i) {
                    @Override
                    public boolean mayPickup(Player player) {
                        ItemStack stack = this.getItem();
                        return !(stack.getItem() == ItemsInit.MTK_BACKPACK.get());
                    }

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return !(stack.getItem() == ItemsInit.MTK_BACKPACK.get());
                    }
                });
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i) {
                @Override
                public boolean mayPickup(Player player) {
                    ItemStack stack = this.getItem();
                    return !(stack.getItem() == ItemsInit.MTK_BACKPACK.get());
                }

                @Override
                public boolean mayPlace(ItemStack stack) {
                    return !(stack.getItem() == ItemsInit.MTK_BACKPACK.get());
                }
            });
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.saveAdditional(this.stack.getTag(), stackHandler);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack stack1 = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack stack2 = slot.getItem();
            stack1 = stack2.copy();
            if (i < this.containerRows * 9) {
                if (!this.moveItemStackTo(stack2, this.containerRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack2, 0, this.containerRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (stack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack1;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int i1, int i2, boolean b) {
        return MTKMenuHelper.moveItemStackTo(this.slots, stack, i1, i2, b);
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public int getContainerRows() {
        return this.containerRows;
    }

    public MTKItemStackHandler getStackHandler() {
        return stackHandler;
    }
}
