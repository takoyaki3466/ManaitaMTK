package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaSword extends SwordItem {

    public ToolManaitaSword() {
        super(MTKTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity player) {
        // 攻撃された敵を即死させる
        if (!entity.level().isClientSide) {
            entity.setHealth(0.0f);
        }
        return super.hurtEnemy(stack, entity, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.manaita_sword_hover_text")
                .withStyle(ChatFormatting.GRAY));
    }
}
