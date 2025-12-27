package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.util.MTKMenuHelper;
import com.takoy3466.manaitamtk.util.slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.util.slot.MTKSlotItemHandler;
import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.MenusInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MTKChestMenu extends AbstractContainerMenu {
    private final int MAX_VALUE = 2147483647;
    private final MTKChestBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final MTKItemStackHandler handler;
    private final int containerRows;

    public MTKChestMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        this(id, playerInventory, buf.readBlockPos());
    }

    public MTKChestMenu(int id, Inventory playerInventory, BlockPos pos) {
        super(MenusInit.MTK_CHEST.get(), id);
        BlockEntity be = playerInventory.player.level().getBlockEntity(pos);
        if (be instanceof MTKChestBlockEntity chestEntity){
            this.blockEntity = chestEntity;
        } else {
            throw new IllegalStateException(be.getClass().getCanonicalName() + "と MTKChestBlockEntity クラスは違うよ！");
        }

        this.handler = chestEntity.getItemHandler();
        this.levelAccess = ContainerLevelAccess.create(chestEntity.getLevel(), chestEntity.getBlockPos());

        this.containerRows = 6;
        int i = (this.containerRows - 4) * 18;
        int j;
        int k;
        // 自スロット
        for(j = 0; j < this.containerRows; ++j) {
            for(k = 0; k < 9; ++k) {
                this.addSlot(new MTKSlotItemHandler(this.handler, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        // プレイヤーインベントリ
        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        // ホットバー
        for(j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
        }
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
        return stillValid(this.levelAccess, player, BlocksInit.Blocks.MTK_CHEST.get());
    }

    public MTKChestBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public int getContainerRows() {
        return this.containerRows;
    }
}
