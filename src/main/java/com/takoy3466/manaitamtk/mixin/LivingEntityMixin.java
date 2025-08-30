package com.takoy3466.manaitamtk.mixin;

import com.takoy3466.manaitamtk.regi.ManaitaMTKItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "setHealth", cancellable=true)
    private void onSetHealth(float v, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity == null) return;
        if (entity instanceof LocalPlayer localPlayer) {
            if (localPlayer == null || localPlayer.getInventory() == null || localPlayer.getItemBySlot(EquipmentSlot.HEAD) == null) return;
            if (localPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void onDie(DamageSource source, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity == null) return;
        if (entity instanceof LocalPlayer localPlayer) {
            if (localPlayer == null || localPlayer.getInventory() == null || localPlayer.getItemBySlot(EquipmentSlot.HEAD) == null) return;
            if (localPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                ci.cancel();
            }
        }
    }
}
