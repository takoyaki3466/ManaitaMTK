package com.takoy3466.ManaitaMTK.item;

import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.Menu.DoubleCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChangeableMagnificationPortableDCT extends Item {

    public ChangeableMagnificationPortableDCT() {
        super(new Properties()
                .rarity(Rarity.EPIC)
        );
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.literal("craft " + modeName(stack));
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        return new DoubleCraftingTableMenu(id, playerInventory, ContainerLevelAccess.create(level, player.blockPosition()), modeMagnification(stack));
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        Player player = (Player) entity;
        InteractionHand hand = player.getUsedItemHand();

        if (MTKKeyMapping.MagnificationKey.consumeClick()) {
            player.startUsingItem(hand);
            modeChange(stack);
            player.displayClientMessage(Component.literal("MODE :" + modeName(stack)), true);
        }
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        if (modeNumber(stack) != 0){
            return true;
        }else return false;
    }
    
    private void modeChange(ItemStack stack) {

        if (stack.getTag() == null) {
            stack.setTag(new CompoundTag());
        }
        stack.getTag().putInt("mode",modeNumber(stack) < 3 ? modeNumber(stack) + 1 : 0);
    }
    
    public int modeNumber (ItemStack stack) {

        if(stack.getTag() == null){
            return 0;
        }
        return stack.getTag().getInt("mode");
    }
    
    private String modeName(ItemStack stack){
        return switch (modeNumber(stack)){
            case 0 -> "1x";
            case 1 -> "2x";
            case 2 -> "8x";
            case 3 -> "64x";
            default -> "nothing";
        };
    }
    
    private int modeMagnification(ItemStack stack){
        return switch (modeNumber(stack)){
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 8;
            case 3 -> 64;
            default -> 1;
        };
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("MODE : " + modeName(stack))
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
