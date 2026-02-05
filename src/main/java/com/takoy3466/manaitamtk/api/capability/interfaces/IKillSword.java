package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

@AutoRegisterCapability
public interface IKillSword extends INBTSerializable<CompoundTag> {

    void setIsKillAll(boolean isKillAll);

    boolean isKillAll();

    void kill(LivingEntity target, Level level, Player player);

    default void kill(List<LivingEntity> targets, Level level, Player player) {
        for (LivingEntity target : targets) {
            kill(target, level, player);
        }
    }

    @Override
    void deserializeNBT(CompoundTag tag);

    @Override
    CompoundTag serializeNBT();
}
