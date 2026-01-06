package com.takoy3466.manaitamtk.api.capability.helper;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class MTKCapabilityHelper {

    public static <T> LazyOptional getLazyOptional(Capability<T> capability, ItemStack stack) {
        return stack.getCapability(capability);
    }

    public static <T> LazyOptional getLazyOptional(Capability<T> capability, Player player) {
        return getLazyOptional(capability, player.getMainHandItem());
    }

    public static <T> boolean isUsage(LazyOptional<T> lazyOptional) {
        return lazyOptional.isPresent() && lazyOptional.resolve().isPresent();
    }

    // isUsage後に使うこと
    public static <T> T getInterface(LazyOptional<T> lazyOptional) {
        if (lazyOptional.isPresent() && lazyOptional.resolve().isPresent()) {
            return lazyOptional.resolve().get();
        }else return null;

    }
}
