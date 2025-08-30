package com.takoy3466.manaitamtk.menu;

import com.takoy3466.manaitamtk.block.Slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.block.Slot.MTKSlotItemHandler;
import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.regi.ManaitaMTKBlocks;
import com.takoy3466.manaitamtk.regi.ManaitaMTKMenus;
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
    private final MTKChestBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final MTKItemStackHandler handler;
    private final int containerRows;

    public MTKChestMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        this(id, playerInventory, buf.readBlockPos());
    }

    public MTKChestMenu(int id, Inventory playerInventory, BlockPos pos) {
        super(ManaitaMTKMenus.MTK_CHEST.get(), id);
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
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (i < this.containerRows * 9) {
                if (!this.moveItemStackTo(stack1, this.containerRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack1, 0, this.containerRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int i1, int i2, boolean bool) {
        final int MAX_VALUE = 2100000000;
        // 移動元のアイテム, スロットはじめ, スロット終わり, true->後ろから前|false->前から後ろ
        boolean flag = false; // 動いたかどうか
        int i = i1; // 開始スロット
        if (bool) {
            i = i2 - 1;
        }

        Slot slot1;
        ItemStack itemstack;
        // スタック可能なアイテムを重ねられるだけ重ねるコーナー
        if (stack.isStackable()) {
            while(!stack.isEmpty()) {
                if (bool) {
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;// 範囲外になったら break。
                }

                slot1 = this.slots.get(i);
                itemstack = slot1.getItem();// 対象スロットの中身をゲット
                if (!itemstack.isEmpty() && ItemStack.isSameItemSameTags(stack, itemstack)) {// 同じアイテム & 同じ NBT 等のときのみ結合
                    long sum = (long) itemstack.getCount() + (long) stack.getCount();

                    int j = (int) sum;
                    int maxSize = MAX_VALUE;
                    if (j <= maxSize) {
                        // 全部入れる
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot1.setChanged();
                        flag = true;
                    } else if (sum > MAX_VALUE) {
                        int shrink = MAX_VALUE - itemstack.getCount();
                        if (shrink > 0) {
                            // 入れられるだけ入れる
                            stack.shrink(shrink);
                            itemstack.setCount(maxSize);
                            slot1.setChanged();
                            flag = true;
                        }
                    }
                }

                // 次スロットへ行く
                if (bool) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        // 数が余っていれば隣の空きスロットへ入れるコーナー
        if (!stack.isEmpty()) {
            if (bool) {
                i = i2 - 1;
            } else {
                i = i1;
            }

            while(true) {
                if (bool) {
                    // スロットの範囲外ならおわり
                    if (i < i1) {
                        break;
                    }
                } else if (i >= i2) {
                    break;
                }

                slot1 = this.slots.get(i);
                itemstack = slot1.getItem();
                // スロットが空 & スロットにアイテムを置けるか
                if (itemstack.isEmpty() && slot1.mayPlace(stack)) {
                    if (stack.getCount() > slot1.getMaxStackSize()) {
                        slot1.setByPlayer(stack.split(slot1.getMaxStackSize()));
                    } else {
                        slot1.setByPlayer(stack.split(stack.getCount()));
                    }

                    slot1.setChanged();
                    flag = true;
                    break;// // 1スロットだけで終了
                }

                // 次スロットへ行く
                if (bool) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        // 変更のありなしを返す
        return flag;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess, player, ManaitaMTKBlocks.Blocks.MTK_CHEST.get());
    }

    public MTKChestBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public int getContainerRows() {
        return this.containerRows;
    }
}
