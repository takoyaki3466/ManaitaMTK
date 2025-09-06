package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.block.Slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.block.Slot.MTKSlotItemHandler;
import com.takoy3466.manaitamtk.init.ManaitaMTKItems;
import com.takoy3466.manaitamtk.init.ManaitaMTKMenus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MTKBackpackMenu extends AbstractContainerMenu {
    private final int MAX_VALUE = 2147483647;
    private final int containerRows = 6;
    private final MTKItemStackHandler stackHandler;
    private final ItemStack stack;

    public MTKBackpackMenu(int id, Inventory inv) {
        this(id, inv, ItemStack.EMPTY);
    }

    public MTKBackpackMenu(int id, Inventory playerInventory, ItemStack stack) {
        super(ManaitaMTKMenus.MTK_BACKPACK.get(), id);
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
        for(int j = 0; j < this.containerRows; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new MTKSlotItemHandler(this.stackHandler, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i){
                    @Override
                    public boolean mayPickup(Player player) {
                        ItemStack stack = this.getItem();
                        return !(stack.getItem() == ManaitaMTKItems.MTK_BACKPACK.get());
                    }

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return !(stack.getItem() == ManaitaMTKItems.MTK_BACKPACK.get());
                    }
                });
            }
        }
        for(int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i){
                @Override
                public boolean mayPickup(Player player) {
                    ItemStack stack = this.getItem();
                    return !(stack.getItem() == ManaitaMTKItems.MTK_BACKPACK.get());
                }

                @Override
                public boolean mayPlace(ItemStack stack) {
                    return !(stack.getItem() == ManaitaMTKItems.MTK_BACKPACK.get());
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
        boolean flag = false;
        int i = i1;
        if (b) {
            i = i2 - 1;
        }

        Slot slot1;
        ItemStack itemstack;
        if (stack.isStackable()) {
            while(!stack.isEmpty()) {
                if (b) {
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;
                }

                slot1 = this.slots.get(i);
                itemstack = slot1.getItem();
                if (!itemstack.isEmpty() && ItemStack.isSameItemSameTags(stack, itemstack)) {
                    long itemStackCount = itemstack.getCount();
                    long stackCount = stack.getCount();

                    long j = itemStackCount + stackCount;
                    int maxSize = this.MAX_VALUE;
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount((int) j);
                        slot1.setChanged();
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot1.setChanged();
                        flag = true;
                    }
                }

                if (b) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (b) {
                i = i2 - 1;
            } else {
                i = i1;
            }

            while(true) {
                if (b) {
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;
                }

                slot1 = this.slots.get(i);
                itemstack = slot1.getItem();
                if (itemstack.isEmpty() && slot1.mayPlace(stack)) {
                    if (stack.getCount() > slot1.getMaxStackSize()) {
                        slot1.setByPlayer(stack.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.setByPlayer(stack.split(stack.getCount()));
                    }

                    slot1.setChanged();
                    flag = true;
                    break;
                }

                if (b) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void saveAdditional(CompoundTag tag, MTKItemStackHandler handler) {
        ListTag listTagCount = new ListTag();
        CompoundTag MTKTag = new CompoundTag();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!handler.getStackInSlot(i).isEmpty()) {
                listTagCount.add(IntTag.valueOf(stack.getCount()));
            }else listTagCount.add(IntTag.valueOf(0));
        }
        tag.put("itemCount", listTagCount);
        MTKTag.put("MTKContainer", handler.serializeNBT());
        tag.put(ManaitaMTK.MOD_ID, MTKTag);
    }
    
    public void load(CompoundTag tag, MTKItemStackHandler handler) {
        ListTag listTagCount = tag.getList("itemCount", Tag.TAG_INT); // 3
        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);
        handler.deserializeNBT(MTKTag.getCompound("MTKContainer"));
        for (int i = 0; i < handler.getSlots(); i++) {
            int countInt = listTagCount.getInt(i);
            ItemStack stack = handler.getStackInSlot(i);
            stack.setCount(countInt);
            handler.setStackInSlot(i, stack);
        }
    }

    public int getContainerRows() {
        return this.containerRows;
    }

    public MTKItemStackHandler getStackHandler() {
        return stackHandler;
    }
}
