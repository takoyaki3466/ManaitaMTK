package com.takoy3466.manaitamtk.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemMTK extends Item {
    public ItemMTK() {
        super(new Properties()
                .rarity(Rarity.EPIC) //レアリティの追加

        );
    }
    
    // hori horiさんありがとう！
    @SuppressWarnings("NoTranslation")
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        String lang = Minecraft.getInstance().getLanguageManager().getSelected();
        if ("ko_kr".equals(lang)) {
            list.add(Component.translatable("Thank_you_to_hori_hori_for_helping_with_the_translation"));
        }
    }
}
