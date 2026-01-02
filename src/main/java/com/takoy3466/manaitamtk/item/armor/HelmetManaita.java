package com.takoy3466.manaitamtk.item.armor;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.apiMTK.interfaces.IItemhasTag;
import com.takoy3466.manaitamtk.network.MTKNetwork;
import com.takoy3466.manaitamtk.network.PacketFlySpeed;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HelmetManaita extends armorManaitaCore implements IItemhasTag<Float> {
    private final Component hoverText = Component.translatable("item.manaitamtk.helmet_manaita.hover_text");
    private static final Component FIRST_TEXT = Component.translatable("item.manaitamtk.fwai.first_text");
    private int flySpeed = 0;
    public HelmetManaita() {
        super(Type.HELMET, new Properties());
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
        if (world.isClientSide()){ // クライアントだけの処理 (キー入力のチェック)
            if (MTKKeyMapping.HelmetKey.consumeClick()){
                modeNumber = modeChange(modeNumber, 1);
                player.displayClientMessage(Component.literal("MODE :" + modeName()),true);

            } else if (MTKKeyMapping.FlySpeedKey.consumeClick()) {
                this.flySpeed = modeChange(this.flySpeed, 3);// 0 -> 0.05f, 1 -> 0.1f, 2 -> 0.2f, 3 -> 0.6f
                switch (this.flySpeed) {
                    case 0 -> this.send(0.05f);
                    case 1 -> this.send(0.1f);
                    case 2 -> this.send(0.2f);
                    case 3 -> this.send(0.6f);
                    default -> this.send(0.05f);
                }
                String s =  switch (this.flySpeed) {
                    case 0 -> "0.05";
                    case 1 -> "0.1";
                    case 2 -> "0.2";
                    case 3 -> "0.6";
                    default -> "0.05";
                };
                player.displayClientMessage(Component.literal(FIRST_TEXT.getString() + s),true);
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

    private void send(float flySpeed) {
        MTKNetwork.CHANNEL.sendToServer(new PacketFlySpeed(flySpeed));
    }

    @Override
    public Float getTag(ItemStack stack) {
        float flySpeed = stack.getOrCreateTag().getFloat("FlySpeed");
        return Math.max(flySpeed, 0.05f);
    }

    //modeNumberをころころ変えて設定する
    private int modeChange(int select, int maxMode) {
        return select < maxMode ? select + 1 : 0;
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

        list.add(Component.literal(hoverText.getString() + this.getTag(stack))
                .withStyle(ChatFormatting.GRAY)
        );
    }
}
