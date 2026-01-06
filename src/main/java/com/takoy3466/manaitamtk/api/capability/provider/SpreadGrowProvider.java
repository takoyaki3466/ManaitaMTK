package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.ISpreadGrow;
import com.takoy3466.manaitamtk.util.ToolUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class SpreadGrowProvider implements ISpreadGrow {

    @Override
    public void spreadGrow(Level level, BlockPos pos, int radius) {
        ToolUtil.spreadGrow(level, pos, radius);
    }
}
