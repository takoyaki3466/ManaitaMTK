package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public interface IHasMenuProvider<T extends AbstractContainerMenu> {
    T setMenu(int id, Inventory inv, Player player, ItemStack stack);

    default MenuProvider getMenuProvider(Component component, ItemStack stack) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return component;
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
                return setMenu(id, inv, player, stack);
            }
        };
    }

    default MenuProvider getMenuProvider(String displayName, ItemStack stack) {
        return getMenuProvider(Component.literal(displayName), stack);
    }

    default MenuProvider getMenuProvider(String displayName, Player player, InteractionHand hand) {
        return getMenuProvider(Component.literal(displayName), player.getItemInHand(hand));
    }

    default InteractionResultHolder<ItemStack> openMenu(Level level, Player player, Component component, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this.getMenuProvider(component, stack));
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }
        return InteractionResultHolder.pass(stack);
    }

    default InteractionResultHolder<ItemStack> openMenu(Level level, Player player, String displayName, InteractionHand hand) {
        return this.openMenu(level, player, Component.literal(displayName), hand);
    }

    default ContainerLevelAccess createAccess(Level level, BlockPos pos) {
         return ContainerLevelAccess.create(level, pos);
    }

    default ContainerLevelAccess createAccess(Player player) {
         return createAccess(player.level(), player.blockPosition());
    }
}
