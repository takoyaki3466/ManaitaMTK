package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.provider.SpreadGrowProvider;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import com.takoy3466.manaitamtk.config.MTKConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaHoe extends HoeItem implements IHasCapability {
    private final Component hoverText = Component.translatable("item.manaitamtk.manaita_hoe.hover_text");

    public ToolManaitaHoe() {
        super(MTKToolTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        int radius = MTKConfig.CROP_GROWTH_RADIUS.get();
        if (player == null) return null;

        if (!level.isClientSide() && player.isSteppingCarefully()) {
            this.execute(MTKCapabilities.SPREAD_GROW, context, iSpreadGrow -> iSpreadGrow.spreadGrow(context, radius));
            // サウンド
            level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }else super.useOn(context);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("radius is " + MTKConfig.CROP_GROWTH_RADIUS.get())
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.setCapability(MTKCapabilities.SPREAD_GROW, SpreadGrowProvider::new);
    }
}
