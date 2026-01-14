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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ToolManaitaPickaxe extends PickaxeItem implements IHasCapability {
    private final Component PRESS_TO_SHIFT = Component.translatable("util.manaitamtk.press_to_shift").withStyle(ChatFormatting.GRAY);
    private final Component DESCRIPTION = Component.translatable("item.manaitamtk.manaita_pickaxe_hover_text");

    public ToolManaitaPickaxe() {
        super(MTKToolTierList.MTK_TIER, 2147483647, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();

        if (player != null && !level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            this.execute(MTKCapabilities.RANGE_BREAK, context,
                    iRangeBreak -> iRangeBreak.rangeBreak(level, pos.getX(), pos.getY(), pos.getZ(), player, getRange(serverPlayer.getItemInHand(hand)))
            );

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return super.useOn(context);
    }

    public int getRange(ItemStack stack) {
        LazyOptional<IRangeBreak> lazyOptional = this.getLazyOptional(MTKCapabilities.RANGE_BREAK, stack);
        if (this.isUsage(lazyOptional)) {
            return this.getInterface(lazyOptional).getRange();
        }else return 1;
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return this.getRange(stack) != 1;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        int range = this.getRange(stack);
        list.add(Component.literal("MODE : " + range + " x " + range)
                .withStyle(ChatFormatting.GRAY));
        if (level == null) {
            return;
        }
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
        return this.setCapability(MTKCapabilities.RANGE_BREAK, RangeBreakProvider::new);
    }
}
