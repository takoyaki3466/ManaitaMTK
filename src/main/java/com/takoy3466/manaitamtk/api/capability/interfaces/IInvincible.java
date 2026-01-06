package com.takoy3466.manaitamtk.api.capability.interfaces;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IInvincible {

    void setInvincible(boolean isInvincible);

    boolean isInvincible();
}
