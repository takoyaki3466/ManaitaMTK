package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ISpreadGrow {

    void spreadGrow(Level level, BlockPos pos, int radius);

    default void spreadGrow(UseOnContext context, int radius) {
        spreadGrow(context.getLevel(), context.getClickedPos(), radius);
    }
}
