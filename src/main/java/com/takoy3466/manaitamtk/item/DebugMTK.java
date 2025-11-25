package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DebugMTK extends Item {
    private final int MAX_VALUE = 2147483647;

    public DebugMTK() {
        super(new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack mtk = new ItemStack(ItemsInit.ITEM_MTK::get);
        mtk.setCount(this.MAX_VALUE);

        if (player.isSteppingCarefully()) {
            if (player.getInventory().getFreeSlot() >= 1) {
                player.getInventory().setItem(player.getInventory().getFreeSlot(), mtk);
            }
        }


        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("シフト右クリックで空いたスロット1つに21億個のItemMTKを与える(デバッグ用)"));
    }
}
