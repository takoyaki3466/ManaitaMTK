package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IWoodReverse {

    void woodReverse(Level level, BlockPos pos, Player player, ItemStack stack, InteractionHand hand);

    default void woodReverse(UseOnContext context) {
        woodReverse(context.getLevel(), context.getClickedPos(), context.getPlayer(), context.getItemInHand(), context.getHand());
    }

}
