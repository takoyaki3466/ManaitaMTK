package com.takoy3466.manaitamtk.api.helper;

import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class MTKContainerHelper {
    public static final String TAG_COUNT = "itemCount";
    public static final String TAG_HANDLER = "MTKhandler";

    /**
     * 1スロットにアイテムが64個以上ある時に安全に保存するために使います。
     * @param tag 保存先のタグ
     * @param handler 保存対象のHandler
     * @param <T> ItemStackHandlerを継承しているものを使用してください
     */
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

    /**
     * 1スロットにアイテムが64個以上ある時に安全にロードするために使います。
     * @param tag ロードに用いるタグ
     * @param handler ロード対象のHandler
     * @param <T> ItemStackHandlerを継承しているものを使用してください
     */
    public static <T extends ItemStackHandler> void loadHandler(CompoundTag tag, T handler) {
        if (!(tag.contains(TAG_COUNT) || tag.contains(ManaitaMTK.MOD_ID))) {
            return;
        }
        ListTag listTagCount = tag.getList(TAG_COUNT, Tag.TAG_INT); // 3
        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);
        if (!(MTKTag.contains(TAG_HANDLER))){
            return;
        }
        handler.deserializeNBT(MTKTag.getCompound(TAG_HANDLER));
        for (int i = 0; i < handler.getSlots(); i++) {
            int countInt = listTagCount.getInt(i);
            ItemStack stack = handler.getStackInSlot(i);
            stack.setCount(countInt);
            handler.setStackInSlot(i, stack);
        }
    }
}
