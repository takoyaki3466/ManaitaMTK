package com.takoy3466.ManaitaMTK.block.example;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.block.Slot.MTKItemStackHandler;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MTKChestEntity extends BlockEntity implements MenuProvider {

    private final MTKItemStackHandler handler = new MTKItemStackHandler(1) {
        // 中身が変わったら更新する
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    public MTKChestEntity(BlockPos pos, BlockState state) {
        super(ManaitaMTKBlocks.BlockEntities.MTK_CHEST.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("テストチェスト");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MTKChestMenu(id, inventory, this.getBlockPos());
    }

    public MTKItemStackHandler getHandler() {
        return this.handler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag MTKTag = new CompoundTag();
        MTKTag.put("MTKContainer", this.handler.serializeNBT());
        tag.put(ManaitaMTK.MOD_ID, MTKTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag MTKTag = new CompoundTag();
        this.handler.deserializeNBT(MTKTag.getCompound("MTKContainer"));
    }
}
