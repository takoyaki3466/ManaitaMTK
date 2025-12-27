package com.takoy3466.manaitamtk.util;

import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class MTKContainerHelper {
    private static final String TAG_COUNT = "itemCount";
    private static final String TAG_HANDLER = "MTKhandler";

    public static <T extends ItemStackHandler> void saveHandler(CompoundTag tag, T handler) {
        ListTag listTagCount = new ListTag();
        CompoundTag MTKTag = new CompoundTag();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!handler.getStackInSlot(i).isEmpty()) {
                listTagCount.add(IntTag.valueOf(stack.getCount()));
            }else listTagCount.add(IntTag.valueOf(0));
        }
        tag.put(TAG_COUNT, listTagCount);
        MTKTag.put(TAG_HANDLER, handler.serializeNBT());
        tag.put(ManaitaMTK.MOD_ID, MTKTag);
    }

    public static <T extends ItemStackHandler> void loadHandler(CompoundTag tag, T handler) {
        ListTag listTagCount = tag.getList(TAG_COUNT, Tag.TAG_INT); // 3
        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);
        handler.deserializeNBT(MTKTag.getCompound(TAG_HANDLER));
        for (int i = 0; i < handler.getSlots(); i++) {
            int countInt = listTagCount.getInt(i);
            ItemStack stack = handler.getStackInSlot(i);
            stack.setCount(countInt);
            handler.setStackInSlot(i, stack);
        }
    }

    public static Container containerFromStack(ItemStack... stacks) {
        Container container = new SimpleContainer(stacks.length);
        for (ItemStack stack : stacks) {
            container.setItem(0, stack != null ? stack : ItemStack.EMPTY);
        }
        return container;
    }

    public static Container containerFromStack(ItemStack stack) {
        Container container = new SimpleContainer(1);
        container.setItem(0, stack != null ? stack : ItemStack.EMPTY);
        return container;
    }

    public static <T extends ItemStackHandler> void clearContent(T handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            handler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    public static <T extends ItemStackHandler> void fillStackedContents(StackedContents stackedContents, T handler) {

        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            stackedContents.accountStack(stack);
        }
    }

    public static <T extends ItemStackHandler> void fillStackedContents(StackedContents stackedContents, ItemStack stack) {
        stackedContents.accountStack(stack);
    }
}
