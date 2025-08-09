package com.takoy3466.ManaitaMTK.block.example;

import com.takoy3466.ManaitaMTK.block.Slot.MTKItemStackHandler;
import com.takoy3466.ManaitaMTK.block.Slot.MTKSlotItemHandler;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKBlocks;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKMenus;
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
    private final MTKChestEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final MTKItemStackHandler handler;

    public MTKChestMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        this(id, playerInventory, buf.readBlockPos());
    }

    public MTKChestMenu(int id, Inventory playerInventory, BlockPos pos) {
        super(ManaitaMTKMenus.MTK_CHEST.get(), id);
        BlockEntity be = playerInventory.player.level().getBlockEntity(pos);
        if (be instanceof MTKChestEntity chestEntity){
            this.blockEntity = chestEntity;
        } else {
            throw new IllegalStateException(be.getClass().getCanonicalName() + "と MTKChestEntity クラスは違うよ！");
        }

        this.handler = chestEntity.getHandler();
        this.levelAccess = ContainerLevelAccess.create(chestEntity.getLevel(), chestEntity.getBlockPos());

        // 自スロット
        this.addSlot(new MTKSlotItemHandler(this.handler, 0, 80, 35));

        // プレイヤーインベントリ
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // ホットバー
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack original = slot.getItem();
            ItemStack copy = original.copy();

            if (i == 0) {
                // チェスト → プレイヤー
                if (!this.moveItemStackTo(original, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // プレイヤー → チェスト
                if (!this.moveItemStackTo(original, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            return copy;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess, player, ManaitaMTKBlocks.Blocks.MTK_CHEST.get());
    }

    public MTKChestEntity getBlockEntity() {
        return this.blockEntity;
    }
}
