package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.block.Slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import com.takoy3466.manaitamtk.regi.ManaitaMTKBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MTKChestBlockEntity extends BlockEntity implements MenuProvider {

    private final MTKItemStackHandler itemHandler = new MTKItemStackHandler(54) {
        // 中身が変わったら更新する
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    // LazyOptionalでitemHandlerをラップ
    private final LazyOptional<IItemHandler> itemHandlerLazy = LazyOptional.of(() -> itemHandler);

    public MTKChestBlockEntity(BlockPos pos, BlockState state) {
        super(ManaitaMTKBlocks.BlockEntities.MTK_CHEST.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.manaitamtk.mtk_chest");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MTKChestMenu(id, inventory, this.getBlockPos());
    }

    public MTKItemStackHandler getItemHandler() {
        return this.itemHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        // 64個以上は保存してくれないから渋々個別で保存
        ListTag listTagCount = new ListTag();
        CompoundTag MTKTag = new CompoundTag();
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            ItemStack stack = this.itemHandler.getStackInSlot(i);
            if (!this.itemHandler.getStackInSlot(i).isEmpty()) {
                listTagCount.add(IntTag.valueOf(stack.getCount()));
            }else listTagCount.add(IntTag.valueOf(0));
        }
        tag.put("itemCount", listTagCount);
        MTKTag.put("MTKContainer", this.itemHandler.serializeNBT());
        tag.put(ManaitaMTK.MOD_ID, MTKTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ListTag listTagCount = tag.getList("itemCount", Tag.TAG_INT); // 3
        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);
        this.itemHandler.deserializeNBT(MTKTag.getCompound("MTKContainer"));
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            int countInt = listTagCount.getInt(i);
            ItemStack stack = this.itemHandler.getStackInSlot(i);
            stack.setCount(countInt);
            this.itemHandler.setStackInSlot(i, stack);
        }
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazy.cast();
        }
        return super.getCapability(cap, side);
    }

    // BlockEntityが無効化されたときにLazyOptionalを閉じる
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazy.invalidate();
    }
}
