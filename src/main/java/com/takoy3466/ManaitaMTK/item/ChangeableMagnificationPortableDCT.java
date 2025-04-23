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
    private int magnification = 1;

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
                        return new DoubleCraftingTableMenu(id, playerInventory, ContainerLevelAccess.create(level, player.blockPosition()), magnification);
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;
        InteractionHand hand = player.getUsedItemHand();

        if (world.isClientSide){
            if (MTKKeyMapping.MagnificationKey.consumeClick()) {
                player.startUsingItem(hand);
                modeChange(stack);
                switch (modeName(stack)){
                    case "1x" -> magnification = 1;
                    case "2x" -> magnification = 2;
                    case "8x" -> magnification = 8;
                    case "64x" -> magnification = 64;
                    default -> magnification = 1;
                }
                player.displayClientMessage(Component.literal("MODE :" + modeName(stack)), true);
            }
        }
    }


    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
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

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("MODE : " + modeName(stack))
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
