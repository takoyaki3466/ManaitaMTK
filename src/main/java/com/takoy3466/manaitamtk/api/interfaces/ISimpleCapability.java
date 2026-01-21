package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.common.util.NonNullLazy;

/**
 * 実装するcapabilityが一つの場合に使用します,stackの場合のみ新たに実装しています
 * @param <T> CapabilityのInterfaceを入れます
 */
public interface ISimpleCapability<T> extends IHasCapability {

    Capability<T> getCapability();

    default void execute(ItemStack stack, NonNullConsumer<? super T> consumer) {
        IHasCapability.super.execute(getLazyOptional(getCapability(), stack), consumer);
    }

    default ICapabilityProvider setCapability(NonNullLazy<T> nonNullLazy) {
        return IHasCapability.super.setCapability(getCapability(), nonNullLazy);
    }

    default LazyOptional<T> getLazyOptional(ItemStack stack) {
        return IHasCapability.super.getLazyOptional(getCapability(), stack);
    }
}
