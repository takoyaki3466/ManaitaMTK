package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.MTKTiers;
import com.takoy3466.manaitamtk.api.abstracts.AbstractItemMultipler;
import com.takoy3466.manaitamtk.api.interfaces.IHasMenuProvider;
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

public class PortableDoubleCraftingTable extends AbstractItemMultipler implements IHasMenuProvider {

    public PortableDoubleCraftingTable(Properties properties, MTKTier mtkTier) {
        super(properties, mtkTier);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        super.use(level, player, interactionHand);
        return this.openMenu(level, player, "craft x" + getMultiple(), interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        if (getMTKTier() == MTKTiers.BREAK) {
            list.add(Component.literal(" x33554431 !!")
                    .withStyle(ChatFormatting.RED));
        }else {
            list.add(Component.literal("x" + getMTKName())
                    .withStyle(ChatFormatting.WHITE));
        }
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new MTKCraftingTableMenu(id, inv, this.createAccess(player.level(), player.blockPosition()), getMTKTier());
    }
}
