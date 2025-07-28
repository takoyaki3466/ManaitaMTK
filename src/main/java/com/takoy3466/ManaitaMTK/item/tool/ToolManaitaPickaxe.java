package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ToolManaitaPickaxe extends PickaxeItem {
    public static int range;
    public ToolManaitaPickaxe() {
        super(MTKTierList.MTK_TIER, 2147483647, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        if (range == 1 || range == 0) {
            return super.mineBlock(stack, world, blockstate, pos, entity);
        }else {
            RangeBreak.control(world, pos.getX(), pos.getY(), pos.getZ(), entity, range);
            return true;
        }
    }

    public void getRangeTag(ItemStack stack) {
        // NBTから "Range" を読み取る (なければ1を読み込む)
        CompoundTag tag = stack.getOrCreateTag();
        range = tag.getInt("Range");
        if (range <= 0) range = 1;
    }

    //ピカピカさせる
    @Override
    public boolean isFoil(ItemStack stack) {
        getRangeTag(stack);
        return range != 1;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        getRangeTag(stack);
        list.add(Component.literal("MODE : " + range + " x " + range)
                .withStyle(ChatFormatting.GRAY));
    }
}
