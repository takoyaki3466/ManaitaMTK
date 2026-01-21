package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.interfaces.ISimpleCapability;
import com.takoy3466.manaitamtk.api.interfaces.IUseTag;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.abstracts.AbstractItemMultipler;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.provider.PortableFurnaceProvider;
import com.takoy3466.manaitamtk.api.capability.interfaces.IPortableFurnace;
import com.takoy3466.manaitamtk.api.interfaces.IHasMenuProvider;
import com.takoy3466.manaitamtk.menu.PortableFurnaceMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableFurnace extends AbstractItemMultipler implements ISimpleCapability<IPortableFurnace>, IHasMenuProvider, IUseTag {

    public PortableFurnace(MTKTier mtkTier) {
        super(new Properties(), mtkTier);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        String displayName = Component.translatable("item.manaitamtk.portable_furnace.title").getString() + " x" + getMultiple();
        return this.openMenu(level, player, displayName, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        if (nbt == null) {
            return;
        }
        stack.setTag(nbt);
        if (isContains(nbt)) {
            this.execute(stack, iPortableFurnace -> iPortableFurnace.deserializeNBT(getTag(nbt)));
        }
    }

    @Override
    public @Nullable CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag().copy();
        this.execute(stack, iPortableFurnace -> tag.put(getPath(), iPortableFurnace.serializeNBT()));
        return tag;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        if (level.isClientSide()) return;
        if (!(entity instanceof Player)) return;

        this.execute(stack, iPortableFurnace -> iPortableFurnace.tick(level, getMTKTier()));
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inventory, Player player, ItemStack stack) {
        return new PortableFurnaceMenu(id, inventory, stack);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.setCapability(() -> {
            PortableFurnaceProvider provider = new PortableFurnaceProvider();
            if (isContains(stack)) {
                provider.deserializeNBT(getTag(stack));
            }
            return provider;
        });
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }

    @Override
    public Capability<IPortableFurnace> getCapability() {
        return MTKCapabilities.PORTABLE_FURNACE;
    }
}
