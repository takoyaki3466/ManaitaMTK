package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.interfaces.IHasMenuProvider;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.util.slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.util.tooptip.MTKBackPackTooltip;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class MTKBackPack extends Item implements IHasMenuProvider {
    private final MTKItemStackHandler handler = new MTKItemStackHandler(54);
    private final Component COMPONENT = Component.translatable("item.manaitamtk.mtk_backpack");
    public MTKBackPack() {
        super(new Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.openMenu(level, player, COMPONENT, hand);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(ManaitaMTK.MOD_ID)) return Optional.empty();

        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);

        if (!MTKTag.contains("MTKSimpleContainer") || !tag.contains("itemCount")) return Optional.empty();

        ListTag listTagCount = tag.getList("itemCount", Tag.TAG_INT); // 3
        this.handler.deserializeNBT(MTKTag.getCompound("MTKSimpleContainer"));

        NonNullList<ItemStack> nonNullList = NonNullList.withSize(this.handler.getSlots(), ItemStack.EMPTY);

        for (int i = 0; i < this.handler.getSlots(); i++) {
            int countInt = listTagCount.getInt(i);
            ItemStack stack1 = this.handler.getStackInSlot(i);
            stack1.setCount(countInt); this.handler.setStackInSlot(i, stack1);
        }

        for (int i = 0; i < this.handler.getSlots(); i++) {
            ItemStack slotStack = this.handler.getStackInSlot(i);
            if (!slotStack.isEmpty()) {
                nonNullList.set(i, slotStack);
            }else nonNullList.set(i, ItemStack.EMPTY);
        }

        return Optional.of(new MTKBackPackTooltip(nonNullList));
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        MTKBackpackMenu menu = new MTKBackpackMenu(id, inv, stack);
        if (stack.hasTag()) {
            menu.load(stack.getOrCreateTag(), menu.getStackHandler());
        }
        return menu;
    }
}
