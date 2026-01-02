package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.apiMTK.abstracts.AbstractMenuItem;
import com.takoy3466.manaitamtk.menu.MTKCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableDoubleCraftingTable extends AbstractMenuItem {
    private final int magnification;

    public PortableDoubleCraftingTable(Properties properties, int magnification) {
        super(properties);
        this.magnification = magnification;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        super.use(level, player, interactionHand);
        return this.openMenu(level, player, "craft x" + magnification, interactionHand);
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

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new MTKCraftingTableMenu(id, inv, this.createAccess(player.level(), player.blockPosition()), magnification);
    }
}
