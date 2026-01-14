package com.takoy3466.manaitamtk.api.capability;

import com.takoy3466.manaitamtk.api.capability.interfaces.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MTKCapabilities {
    public static final Capability<IPortableFurnace> PORTABLE_FURNACE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IFly> FLY = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IInvincible> INVINCIBLE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IRangeBreak> RANGE_BREAK = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IKillSword> KILL_SWORD = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IWoodReverse> WOOD_REVERSE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IMultiple> MULTIPLE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<ISpreadGrow> SPREAD_GROW = CapabilityManager.get(new CapabilityToken<>() {});

}
