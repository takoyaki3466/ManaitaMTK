package com.takoy3466.manaitamtk.api.capability.helper;

import com.takoy3466.manaitamtk.api.helper.MTKItemCapabilityHelper;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
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

public class MTKCapabilityHelper {

    public static <T> ICapabilityProvider setCapability(Capability<T> capability, NonNullLazy<T> nonNullLazy) {
        return MTKItemCapabilityHelper.createCapabilityProvider(capability, nonNullLazy);
    }

    public static <T> LazyOptional<T> getLazyOptional(Capability<T> capability, ItemStack stack) {
        return stack.getCapability(capability);
    }

    public static <T> LazyOptional<T> getLazyOptional(Capability<T> capability, UseOnContext context) {
        return getLazyOptional(capability, getStack(context));
    }

    public static <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player) {
        return getLazyOptional(capability, getStack(player));
    }

    public static <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player, InteractionHand hand) {
        return getLazyOptional(capability, getStack(player, hand));
    }

    public static <T> LazyOptional<T> getLazyOptional(Capability<T> capability, Player player, EquipmentSlot slot) {
        return getLazyOptional(capability, getStack(player, slot));
    }

    public static <T> void execute(LazyOptional<T> lazyOptional, NonNullConsumer<? super T> consumer) {
        lazyOptional.ifPresent(consumer);
    }

    public static <T> void execute(Capability<T> capability, ItemStack stack, NonNullConsumer<? super T> consumer) {
        execute(getLazyOptional(capability, stack), consumer);
    }

    public static <T> void execute(Capability<T> capability, UseOnContext context, NonNullConsumer<? super T> consumer) {
        execute(getLazyOptional(capability, getStack(context)), consumer);
    }

    public static <T> void execute(Capability<T> capability, Player player, NonNullConsumer<? super T> consumer) {
        execute(getLazyOptional(capability, getStack(player)), consumer);
    }

    public static <T> void execute(Capability<T> capability, Player player, InteractionHand hand, NonNullConsumer<? super T> consumer) {
        execute(getLazyOptional(capability, getStack(player, hand)), consumer);
    }

    public static <T> void execute(Capability<T> capability, Player player, EquipmentSlot slot, NonNullConsumer<? super T> consumer) {
        execute(getLazyOptional(capability, getStack(player, slot)), consumer);
    }

    public static <T> boolean isUsage(LazyOptional<T> lazyOptional) {
        return lazyOptional.isPresent() && lazyOptional.resolve().isPresent();
    }

    // isUsage後に使うこと
    public static <T> T getInterface(LazyOptional<T> lazyOptional) {
        if (lazyOptional.isPresent() && lazyOptional.resolve().isPresent()) {
            return lazyOptional.resolve().get();
        }
        return null;
    }

    public static ItemStack getStack(UseOnContext context) {
        return context.getItemInHand();
    }

    public static ItemStack getStack(Player player) {
        return player.getMainHandItem();
    }

    public static ItemStack getStack(Player player, InteractionHand hand) {
        return player.getItemInHand(hand);
    }

    public static ItemStack getStack(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot);
    }

}
