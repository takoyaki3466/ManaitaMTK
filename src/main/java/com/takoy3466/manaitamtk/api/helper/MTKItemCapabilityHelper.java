package com.takoy3466.manaitamtk.api.helper;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullLazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MTKItemCapabilityHelper {

    /**
     * 主にItemクラスのinitCapabilitiesで使います。
     * @param capability 使いたいcapability
     * @param nonNullLazy 使いたいcapabilityのnonNullLazy
     * @return ICapabilityProviderを返します。
     * @param <T> 主にcapability元のinterfaceになります。
     */
    public static <T> ICapabilityProvider createCapabilityProvider(Capability<T> capability, NonNullLazy<T> nonNullLazy) {
        return new ICapabilityProvider() {

            final LazyOptional<T> lazyOptional = LazyOptional.of(nonNullLazy);

            @Override
            public @NotNull <U> LazyOptional<U> getCapability(@NotNull Capability<U> cap, @Nullable Direction dir) {
                if (cap == capability) {
                    return lazyOptional.cast();
                }else {
                    return LazyOptional.empty();
                }
            }
        };
    }
}
