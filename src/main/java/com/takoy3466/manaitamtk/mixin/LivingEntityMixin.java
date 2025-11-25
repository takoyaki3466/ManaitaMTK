package com.takoy3466.manaitamtk.mixin;

import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "setHealth", cancellable = true)
    private void onSetHealth(float v, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (whetherInvincible(entity)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "getHealth", cancellable = true)
    private void onGethealth(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (whetherInvincible(entity)) {
            cir.setReturnValue(entity.getMaxHealth());
        }
    }

    @Inject(method = "isDeadOrDying", at = @At("RETURN"), cancellable = true)
    private void onIsDeadOrDying(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (whetherInvincible(entity)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isAlive", at = @At("RETURN"), cancellable = true)
    private void onIsAlive(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (whetherInvincible(entity)) {
            cir.setReturnValue(true);
        }
    }


    @SuppressWarnings({"ConstantValue", "AddedMixinMembersNamePattern", "RedundantIfStatement"})
    @Unique
    private boolean whetherInvincible(LivingEntity entity) {
        if (entity == null) return false;
        if (entity instanceof Player player) {
            if (player == null || player.getInventory() == null || player.getItemBySlot(EquipmentSlot.HEAD) == null || ItemsInit.HELMET_MANAITA == null) return false;
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                return true;
            }
        }
        return false;
    }
}
