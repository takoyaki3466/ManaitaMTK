package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.provider.WoodReverseProvider;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaAxe extends AxeItem implements IHasCapability {
    public ToolManaitaAxe() {
        super(MTKToolTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant()//燃える耐性を付ける
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && player.isSteppingCarefully()) {
            this.execute(MTKCapabilities.WOOD_REVERSE, context, iWoodReverse -> iWoodReverse.woodReverse(context));
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.manaita_axe.hover_text").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.setCapability(MTKCapabilities.WOOD_REVERSE, WoodReverseProvider::new);
    }
}
