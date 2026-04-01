package com.takoy3466.manaitamtk.capability.interfaces;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IInvincible {

    void setInvincible(boolean isInvincible);

    boolean isInvincible();
}
