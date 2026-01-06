package com.takoy3466.manaitamtk.util.slot;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MTKFurnaceResultSlot extends SlotItemHandler {
    private final int MAX_VALUE = 2147483647;
    private final Player player;
    private int removeCount;

    public <T extends ItemStackHandler> MTKFurnaceResultSlot(Player player, T handler, int slotId, int x, int y) {
        super(handler, slotId, x, y);
        this.player = player;
    }

    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    public ItemStack remove(int i) {
        if (this.hasItem()) {
            this.removeCount += Math.min(i, this.getItem().getCount());
        }

        return super.remove(i);
    }

    @Override
    public int getMaxStackSize() {
        return MAX_VALUE;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : this.MAX_VALUE;
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }

    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(player, stack);
    }

    protected void onQuickCraft(ItemStack stack, int i) {
        this.removeCount += i;
        this.checkTakeAchievements(stack);
    }

    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        Player player = this.player;
        if (player instanceof ServerPlayer serverplayer) {
            Container container = this.container;
            if (container instanceof AbstractFurnaceBlockEntity abstractfurnaceblockentity) {
                abstractfurnaceblockentity.awardUsedRecipesAndPopExperience(serverplayer);
            }
        }

        this.removeCount = 0;
        ForgeEventFactory.firePlayerSmeltedEvent(this.player, stack);
    }
}
