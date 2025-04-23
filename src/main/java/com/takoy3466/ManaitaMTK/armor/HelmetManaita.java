package com.takoy3466.ManaitaMTK.armor;

import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
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
        FlyAndInvulnerable.FAI(entity, player);


        if (world.isClientSide){ // クライアント限定処理 : キー入力のチェック
            if (MTKKeyMapping.HelmetKey.consumeClick()){
                modeChange();
                player.displayClientMessage(Component.literal("MODE :" + modeName()),true);
            }
        }
        else { // サーバー限定処理 : エフェクトの付与など
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
        list.add(Component.literal("~Default value is 0.05~")
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
