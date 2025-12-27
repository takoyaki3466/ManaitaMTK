package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface IItemHasMenuProviderMTK<T extends AbstractContainerMenu> {
    T setMenu(int id, Inventory inventory, Player player, ItemStack stack);

     default MenuProvider getMenuProvider(String displayName, @Nullable ItemStack stack) {
        return this.getMenuProvider(Component.literal(displayName), stack);
    }

    default MenuProvider getMenuProvider(Component component, @Nullable ItemStack stack) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return component;
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                return setMenu(id, inventory, player, stack);
            }
        };
    }
}
