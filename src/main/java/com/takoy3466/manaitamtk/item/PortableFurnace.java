package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.apiMTK.IItemHasMenuProviderMTK;
import com.takoy3466.manaitamtk.apiMTK.capability.IPortableFurnace;
import com.takoy3466.manaitamtk.apiMTK.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.apiMTK.capability.PortableFurnaceProvider;
import com.takoy3466.manaitamtk.menu.PortableFurnaceMenu;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableFurnace extends Item implements IItemHasMenuProviderMTK {
    private final MTKEnum mtkEnum;

    public PortableFurnace(MTKEnum mtkEnum) {
        super(new Properties());
        this.mtkEnum = mtkEnum;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(this.getMenuProvider("test", stack));
            }
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        if (level.isClientSide) return;
        if (!(entity instanceof Player)) return;

        LazyOptional<IPortableFurnace> lazy = stack.getCapability(MTKCapabilities.PORTABLE_FURNACE);
        lazy.ifPresent(iPortableFurnace -> iPortableFurnace.tick(level, this.mtkEnum));
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inventory, Player player, ItemStack stack) {
        return new PortableFurnaceMenu(id, inventory, stack);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {

            private final LazyOptional<IPortableFurnace> lazyOptional = LazyOptional.of(PortableFurnaceProvider::new);

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
                if (capability == MTKCapabilities.PORTABLE_FURNACE) {
                    return lazyOptional.cast();
                }
                return LazyOptional.empty();
            }
        };
    }
}
