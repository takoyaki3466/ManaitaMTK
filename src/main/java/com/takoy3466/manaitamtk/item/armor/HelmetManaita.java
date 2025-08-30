package com.takoy3466.manaitamtk.item.armor;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HelmetManaita extends armorManaitaCore {
    private final Component hoverText = Component.translatable("item.manaitamtk.helmet_manaita.hover_text");
    public static float modeF;

    public HelmetManaita() {
        super(ArmorItem.Type.HELMET, new Item.Properties());
    }

    //ヘルメットをかぶった時の見た目を設定
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
    }

    //ヘルメットをかぶってる時に飛行出来るようにする やつを呼ぶ
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (!(entity instanceof Player player)) return;
        if (world.isClientSide){ // クライアントだけの処理 (キー入力のチェック)
            if (MTKKeyMapping.HelmetKey.consumeClick()){
                modeChange();
                player.displayClientMessage(Component.literal("MODE :" + modeName()),true);
            }
        }
        else { // サーバーだけの処理 (エフェクトの付与など)
            if (modeNumber == 0){
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20*20, 0));
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20*20, 0));
                player.onUpdateAbilities();
            }
        }
    }

    //modeNumberをころころ変えて設定する
    private void modeChange() {
        modeNumber = modeNumber < 1 ? modeNumber + 1 : 0;
    }

    //modeNumberを作成
    static int modeNumber = 0;

    //それぞれのモードに名前割り振り
    private String modeName(){
        return switch (modeNumber){
            case 0 -> "NIGHT VISION and INVISIBILITY ON";
            case 1 -> "NIGHT VISION and INVISIBILITY OFF";
            default -> "nothing";
        };
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        if (modeF == 0.0f) modeF = 0.05f;

        list.add(Component.literal(hoverText.getString() + modeF)
                .withStyle(ChatFormatting.GRAY)
        );
    }
}
