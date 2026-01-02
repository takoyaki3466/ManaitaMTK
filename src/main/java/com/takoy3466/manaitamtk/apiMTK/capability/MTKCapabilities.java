package com.takoy3466.manaitamtk.apiMTK.capability;

import com.takoy3466.manaitamtk.apiMTK.capability.interfaces.IPortableFurnace;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MTKCapabilities {
    public static final Capability<IPortableFurnace> PORTABLE_FURNACE = CapabilityManager.get(new CapabilityToken<>() {
    });
}
