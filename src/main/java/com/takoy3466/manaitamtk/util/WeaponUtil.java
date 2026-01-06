package com.takoy3466.manaitamtk.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;

public class WeaponUtil {

    public static void lightningStriker(LivingEntity target, Level level, Player player) {
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        lightning.wasOnFire = false;
        if (player instanceof ServerPlayer sPlayer) {
            lightning.moveTo(target.position());
            lightning.setCause(sPlayer);
            if (!target.isDeadOrDying()) {
                level.addFreshEntity(lightning);
            }
        }

        die(target);

    }

    public static void lightningStriker(List<LivingEntity> targets, Level level, Player player) {
        for (LivingEntity target : targets) {
            lightningStriker(target, level, player);
        }
    }

    public static <T extends Entity> List<T> selectTargets(Class<T> targetClass, Level level, Player player, double radius , Predicate<? super T> predicate) {
        return level.getEntitiesOfClass(targetClass, player.getBoundingBox().inflate(radius), predicate);
    }

    public static void die(LivingEntity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK);
        DamageSource source = new DamageSource(holder);
        if (!entity.level().isClientSide()) {
            entity.setHealth(0.0f);
            if (!entity.isDeadOrDying()) {
                entity.hurt(source, Float.MAX_VALUE);
                if (entity.isAlive()) {
                    entity.die(source);
                }
            }
        }
    }
}

