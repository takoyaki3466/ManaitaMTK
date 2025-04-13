package com.takoy3466.ManaitaMTK.item;

import com.takoy3466.ManaitaMTK.DoubleCraftingTableEnum;
import com.takoy3466.ManaitaMTK.Menu.DoubleCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableDoubleCraftingTable extends Item {
    private final int magnification;
    private final String componentName;

    public PortableDoubleCraftingTable(Properties properties, int magnification, String componentName) {
        super(properties);
        this.magnification = magnification;
        this.componentName = componentName;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        super.use(level, player, interactionHand);

        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.literal("craft " + componentName);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        return new DoubleCraftingTableMenu(id, playerInventory, ContainerLevelAccess.create(level, player.blockPosition()), magnification);
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(interactionHand), level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal(componentName)
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
