package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.MTKBlockList;
import com.takoy3466.manaitamtk.api.capability.interfaces.IWoodReverse;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class WoodReverseProvider implements IWoodReverse {
    // 自動生成される逆マップ（Stripped → 原木）
    private static final HashMap<Block, Block> REVERSE_STRIPPABLES = new HashMap<>();

    static {
        for (HashMap.Entry<Block, Block> entry : MTKBlockList.STRIPPABLES.entrySet()) {
            Block originalBlock = entry.getKey();
            Block strippedBlock = entry.getValue();
            // はげ木 → 原木
            REVERSE_STRIPPABLES.put(strippedBlock, originalBlock);
        }
    }

    @Override
    public void woodReverse(Level level, BlockPos pos, Player player, ItemStack stack, InteractionHand hand) {
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        Block strippedBlock = REVERSE_STRIPPABLES.get(block);
        if (strippedBlock == null) {
            return;
        }

        BlockState strippedState = strippedBlock.defaultBlockState();

        // 向きを保持（RotatedPillarBlock だけ）
        if (strippedState.hasProperty(RotatedPillarBlock.AXIS)) {
            strippedState = strippedState.setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
        }
        level.setBlock(pos, strippedState, 1 + 2 + 8);
        level.playSound(player, pos, SoundType.WOOD.getPlaceSound(), SoundSource.BLOCKS, 1, 1);
        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
    }
}
