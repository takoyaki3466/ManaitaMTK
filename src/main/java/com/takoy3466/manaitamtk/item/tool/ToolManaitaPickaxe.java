package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.apiMTK.interfaces.IItemhasTag;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.util.ToolUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ToolManaitaPickaxe extends PickaxeItem implements IItemhasTag<Integer> {
    private final Component PRESS_TO_SHIFT = Component.translatable("util.manaitamtk.press_to_shift").withStyle(ChatFormatting.GRAY);
    private final Component DESCRIPTION = Component.translatable("item.manaitamtk.manaita_pickaxe_hover_text");

    public ToolManaitaPickaxe() {
        super(MTKTierList.MTK_TIER, 2147483647, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        LivingEntity entity = context.getPlayer();

        if (stack.getItem() != ItemsInit.MANAITA_PICKAXE.get() || entity == null) return super.useOn(context);

        ToolUtil.RangeBreak.control(level, pos.getX(), pos.getY(), pos.getZ(), entity, this.getTag(stack));
        return InteractionResult.SUCCESS;
    }

    @Override
    public Integer getTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int range = tag.getInt("Range");
        return Math.max(range, 1);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.getTag(stack) != 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        int range = this.getTag(stack);
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
}
