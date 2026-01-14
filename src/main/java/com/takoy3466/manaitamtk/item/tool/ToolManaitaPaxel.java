package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.interfaces.IRangeBreak;
import com.takoy3466.manaitamtk.api.capability.provider.RangeBreakProvider;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaPaxel extends TieredItem implements IHasCapability {
    private final Component PRESS_TO_SHIFT = Component.translatable("util.manaitamtk.press_to_shift").withStyle(ChatFormatting.GRAY);
    private final Component DESCRIPTION = Component.translatable("item.manaitamtk.manaita_paxel_hover_text");

    public ToolManaitaPaxel() {
        super(MTKToolTierList.MTK_TIER, new Item.Properties().fireResistant().rarity(Rarity.EPIC));
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
    @SuppressWarnings("deprecation")
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
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity livingEntity) {
        if (this.getRange(stack) > 1 && livingEntity instanceof ServerPlayer player) {
            this.execute(MTKCapabilities.RANGE_BREAK, stack, iRangeBreak -> iRangeBreak.rangeBreak(level, pos.getX(), pos.getY(), pos.getZ(), player, iRangeBreak.getRange()));
            return true;
        }
        return super.mineBlock(stack, level, state, pos, livingEntity);
    }

    public Integer getRange(ItemStack stack) {
        LazyOptional<IRangeBreak> lazyOptional = this.getLazyOptional(MTKCapabilities.RANGE_BREAK, stack);
        if (this.isUsage(lazyOptional)) {
            return this.getInterface(lazyOptional).getRange();
        }else return 1;
    }

    //ピカピカさせる
    @Override
    public boolean isFoil(ItemStack stack) {
        return this.getRange(stack) != 1;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        int range = this.getRange(stack);
        list.add(Component.literal("MODE : " + range + " x " + range)
                .withStyle(ChatFormatting.GRAY));
        if (level == null) return;
        if (level.isClientSide()) {
            if (Screen.hasShiftDown()) {
                list.add(this.DESCRIPTION);
            }else {
                list.add(this.PRESS_TO_SHIFT);
            }
        }
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return setCapability(MTKCapabilities.RANGE_BREAK, RangeBreakProvider::new);
    }
}