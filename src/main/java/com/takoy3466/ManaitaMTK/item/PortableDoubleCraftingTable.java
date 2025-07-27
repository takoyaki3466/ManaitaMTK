package com.takoy3466.ManaitaMTK.item;

import com.takoy3466.ManaitaMTK.MTKEnum;
import com.takoy3466.ManaitaMTK.block.menu.MTKCraftingTableMenu;
import net.minecraft.ChatFormatting;
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

    public PortableDoubleCraftingTable(Properties properties, int magnification) {
        super(properties);
        this.magnification = magnification;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        super.use(level, player, interactionHand);

        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.literal("craft x" + magnification);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        return new MTKCraftingTableMenu(id, playerInventory, ContainerLevelAccess.create(level, player.blockPosition()), magnification);
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(interactionHand), level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        if (magnification == MTKEnum.BREAK.getMag()) {
            list.add(Component.literal(" x33554431 !!")
                    .withStyle(ChatFormatting.RED));
        }else {
            list.add(Component.literal("x" + magnification)
                    .withStyle(ChatFormatting.WHITE));
        }
    }
}
