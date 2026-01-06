package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.IInvincible;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class InvincibleProvider implements IInvincible, INBTSerializable<CompoundTag> {
    private final String IS_INVINCIBLE = "isInvincible";

    private boolean isInvincible;

    @Override
    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    @Override
    public boolean isInvincible() {
        return this.isInvincible;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(IS_INVINCIBLE, this.isInvincible);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.isInvincible = tag.getBoolean(IS_INVINCIBLE);
    }
}
