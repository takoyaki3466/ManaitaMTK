package com.takoy3466.ManaitaMTK.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZIKOKENZIYOKU extends Item {
    private final Component component = Component.translatable("item.manaitamtk.zikokenziyoku.display_massage");

    public ZIKOKENZIYOKU() {
        super(new Properties()
                .rarity(Rarity.EPIC)
                .stacksTo(1)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        // プレイヤーが右クリックしたときに即死させる
        if (!player.level().isClientSide) {
            player.kill(); // プレイヤーのヘルスを0にして即死させる
            player.displayClientMessage(component, true);
        }

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.zikokenziyoku.hover_text")
                .withStyle(ChatFormatting.RED)
        );
    }
}
