package com.takoy3466.manaitamtk.api.interfaces;

import com.takoy3466.manaitamtk.api.helper.MTKItemCapabilityHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.common.util.NonNullLazy;

public interface IHasCapability {

    default <T> ICapabilityProvider setCapability(Capability<T> capability, NonNullLazy<T> nonNullLazy) {
        return MTKItemCapabilityHelper.createCapabilityProvider(capability, nonNullLazy);
    }

    default <T> LazyOptional<T> getLazyOptional(Capability<T> capability, ItemStack stack) {
        return stack.getCapability(capability);
    }

    default <T> LazyOptional<T> getLazyOptional(Capability<T> capability, UseOnContext context) {
        return getLazyOptional(capability, getStack(context));
    }

    default <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player) {
        return getLazyOptional(capability, getStack(player));
    }

    default <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player, InteractionHand hand) {
        return getLazyOptional(capability, getStack(player, hand));
    }

    default <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player, EquipmentSlot slot) {
        return getLazyOptional(capability, getStack(player, slot));
    }

    default <T> void execute(LazyOptional<T> lazyOptional, NonNullConsumer<? super T> consumer) {
        lazyOptional.ifPresent(consumer);
    }

    default <T> void execute(Capability<T> capability, ItemStack stack, NonNullConsumer<? super T> consumer) {
        this.execute(getLazyOptional(capability, stack), consumer);
    }

    default <T> void execute(Capability<T> capability, UseOnContext context, NonNullConsumer<? super T> consumer) {
        this.execute(getLazyOptional(capability, getStack(context)), consumer);
    }

    default <T> void execute(Capability<T> capability, Player player, NonNullConsumer<? super T> consumer) {
        this.execute(getLazyOptional(capability, getStack(player)), consumer);
    }

    default <T> void execute(Capability<T> capability, Player player, InteractionHand hand, NonNullConsumer<? super T> consumer) {
        this.execute(getLazyOptional(capability, getStack(player, hand)), consumer);
    }

    default <T> void execute(Capability<T> capability, Player player, EquipmentSlot slot, NonNullConsumer<? super T> consumer) {
        this.execute(getLazyOptional(capability, getStack(player, slot)), consumer);
    }

    default <T> boolean isUsage(LazyOptional<T> lazyOptional) {
        return lazyOptional.isPresent() && lazyOptional.resolve().isPresent();
    }

    // isUsage後に使うこと
    default <T> T getInterface(LazyOptional<T> lazyOptional) {
        if (lazyOptional.isPresent() && lazyOptional.resolve().isPresent()) {
            return lazyOptional.resolve().get();
        }
        return null;
    }

    default ItemStack getStack(UseOnContext context) {
        return context.getItemInHand();
    }

    default ItemStack getStack(Player player) {
        return player.getMainHandItem();
    }

    default ItemStack getStack(Player player, InteractionHand hand) {
        return player.getItemInHand(hand);
    }

    default ItemStack getStack(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot);
    }

}
