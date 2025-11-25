package com.takoy3466.manaitamtk.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.item.Item;

import java.util.List;

public class ToolManaitaPaxel extends TieredItem {

    public ToolManaitaPaxel() {
        super(MTKTierList.MTK_TIER, new Item.Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.DIGGER;
    }

    //適正ツールの設定
    @Override
    public boolean isCorrectToolForDrops(BlockState blockstate) {
        return true;
    }

    //採掘速度の設定
    @Override
    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
        return 2147483647f;
    }

    //範囲破壊呼び出し
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        int range = this.getRangeTag(stack);
        if (world.isClientSide()) {
            return super.mineBlock(stack, world, blockstate, pos, entity);
        }

        if (range == 1 || range == 0) {
            return super.mineBlock(stack, world, blockstate, pos, entity);
        }else {
            RangeBreak.control(world, pos.getX(), pos.getY(), pos.getZ(), entity, range);
            return true;
        }
    }

    public int getRangeTag(ItemStack stack) {
        // NBTから "Range" を読み取る (なければ1を読み込む)
        CompoundTag tag = stack.getOrCreateTag();
        int range = tag.getInt("Range");
        return Math.max(range, 1);
    }

    //ピカピカさせる
    @Override
    public boolean isFoil(ItemStack stack) {
        return this.getRangeTag(stack) != 1;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        int range = this.getRangeTag(stack);
        list.add(Component.literal("MODE : " + range + " x " + range)
                .withStyle(ChatFormatting.GRAY));
    }
}