package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class ToolManaitaAxe extends AxeItem {
    // 自動生成される逆マップ（Stripped → 原木）
    private static final HashMap<Block, Block> REVERSE_STRIPPABLES = new HashMap<>();

    static {
        // AxeItem.STRIPPABLES は Map<Block, Block>形式（原木 → はげ木）
        for (HashMap.Entry<Block, Block> entry : AxeItem.STRIPPABLES.entrySet()) {
            Block originalBlock = entry.getKey();
            Block strippedBlock = entry.getValue();
            // はげ木 → 原木
            REVERSE_STRIPPABLES.put(strippedBlock, originalBlock);
        }
    }

    public ToolManaitaAxe() {
        super(MTKTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant()//燃える耐性を付ける
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (player != null && player.isSteppingCarefully()) {
            Block strippedBlock = REVERSE_STRIPPABLES.get(block);
            if (strippedBlock == null) return InteractionResult.PASS;

            BlockState strippedState = strippedBlock.defaultBlockState();

            // 向きを保持（RotatedPillarBlock だけ）
            if (strippedState.hasProperty(RotatedPillarBlock.AXIS)) {
                strippedState = strippedState.setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
            level.setBlock(pos, strippedState, 1 + 2 + 8);
            context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.manaita_axe.hover_text").withStyle(ChatFormatting.GRAY));
    }
}
