package com.takoy3466.ManaitaMTK.item.tool;

import com.takoy3466.ManaitaMTK.config.MTKConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ToolManaitaHoe extends HoeItem {
    private final Component hoverText = Component.translatable("item.manaitamtk.manaita_hoe.hover_text");

    public ToolManaitaHoe() {
        super(MTKTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        int radius = MTKConfig.CROP_GROWTH_RADIUS.get();
        if (player == null) return null;

        if (!level.isClientSide && player.isSteppingCarefully()) {
            for (int x = -1* radius; x <= radius; x++) {
                for (int y = -1* radius; y <= radius; y++) {
                    for (int z = -1* radius; z <= radius; z++) {
                        BlockPos targetPos = pos.offset(x, y, z);
                        BlockState state = level.getBlockState(targetPos);

                        for (Property<?> property : state.getProperties()) {
                            if (property.getName().equals("age") && property instanceof IntegerProperty ageProperty) {
                                int currentAge = state.getValue(ageProperty);
                                int maxAge = Collections.max(ageProperty.getPossibleValues());

                                if (currentAge < maxAge) {
                                    level.setBlock(targetPos, state.setValue(ageProperty, maxAge), 2);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            // サウンドと耐久処理
            level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }else super.useOn(context);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("radius is " + MTKConfig.CROP_GROWTH_RADIUS.get())
                .withStyle(ChatFormatting.GRAY));
    }
}
