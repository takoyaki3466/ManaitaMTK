package com.takoy3466.ManaitaMTK.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class DoubleBlockMTK extends Item {
    private final int magnification;

    public DoubleBlockMTK(Properties properties, int magnification) {
        super(properties);
        this.magnification = magnification;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {

        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.SUCCESS;
        }
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        Item item = level.getBlockState(pos).getBlock().asItem();
        ItemStack result = new ItemStack(item, magnification);

        if (context.getPlayer().getInventory().getFreeSlot() >= 1) {
            player.getInventory().add(result);
        } else Block.popResource(level, pos, result);
        return InteractionResult.SUCCESS;
    }
}
