package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.IKillSword;
import com.takoy3466.manaitamtk.util.WeaponUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

public class MTKSwordProvider implements IKillSword, INBTSerializable<CompoundTag> {
    private final String IS_KILL_ALL = "isKillAll";
    private boolean isKillAll;

    @Override
    public void setIsKillAll(boolean isKillAll) {
        this.isKillAll = isKillAll;
    }

    @Override
    public boolean isKillAll() {
        return this.isKillAll;
    }

    @Override
    public void kill(LivingEntity target, Level level, Player player) {
        WeaponUtil.lightningStriker(target, level, player);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(IS_KILL_ALL, this.isKillAll);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.isKillAll = tag.getBoolean(IS_KILL_ALL);
    }
}
