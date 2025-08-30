package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.block.Slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.item.tooptip.MTKBackPackTooltip;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MTKBackPack extends Item {
    private final MTKItemStackHandler handler = new MTKItemStackHandler(54);
    private NonNullList<ItemStack> nonNullList = NonNullList.withSize(54, ItemStack.EMPTY);
    public MTKBackPack() {
        super(new Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new MenuProvider() {

                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.translatable("item.manaitamtk.mtk_backpack");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        MTKBackpackMenu menu = new MTKBackpackMenu(id, playerInventory, stack);
                        if (stack.hasTag()) {
                            menu.load(stack.getOrCreateTag(), menu.getStackHandler());
                        }

                        return menu;
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        boolean hasStack = false;
        for (ItemStack stack1 : this.nonNullList) {
            if (!stack1.isEmpty()) {
                hasStack = true;
                break;
            }
        }
        if (!hasStack) {
            list.add(Component.translatable("item.manaitamtk.mtk_backpack.hover_text"));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i1, boolean b) {
        if (MTKBackPackTooltip.nonNullList == null) return;
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains(ManaitaMTK.MOD_ID)) {

            CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);

            if (MTKTag.contains("MTKContainer") && tag.contains("itemCount")) {

                ListTag listTagCount = tag.getList("itemCount", Tag.TAG_INT); // 3
                this.handler.deserializeNBT(MTKTag.getCompound("MTKContainer"));

                for (int i = 0; i < this.handler.getSlots(); i++) {
                    int countInt = listTagCount.getInt(i);
                    ItemStack stack1 = this.handler.getStackInSlot(i);
                    stack1.setCount(countInt);
                    this.handler.setStackInSlot(i, stack1);
                }

                for (int i = 0; i < this.handler.getSlots(); i++) {
                    ItemStack slotStack = this.handler.getStackInSlot(i);
                    if (!slotStack.isEmpty()) {
                        this.nonNullList.set(i, slotStack);
                    }
                }
                MTKBackPackTooltip.updateNonNullList(this.nonNullList);
            }
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains(ManaitaMTK.MOD_ID)) {

            CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);

            if (MTKTag.contains("MTKContainer") && tag.contains("itemCount")) {

                ListTag listTagCount = tag.getList("itemCount", Tag.TAG_INT); // 3
                this.handler.deserializeNBT(MTKTag.getCompound("MTKContainer"));

                for (int i = 0; i < this.handler.getSlots(); i++) {
                    int countInt = listTagCount.getInt(i);
                    ItemStack stack1 = this.handler.getStackInSlot(i);
                    stack1.setCount(countInt); this.handler.setStackInSlot(i, stack1);
                }

                for (int i = 0; i < this.handler.getSlots(); i++) {
                    ItemStack slotStack = this.handler.getStackInSlot(i);
                    if (!slotStack.isEmpty()) {
                        this.nonNullList.set(i, slotStack);
                    }
                }

                return Optional.of(new MTKBackPackTooltip(this.nonNullList));
            }
        }
        return Optional.empty();
    }
}
