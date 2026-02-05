package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.api.capability.interfaces.IMultiple;
import com.takoy3466.manaitamtk.api.interfaces.ISimpleCapability;
import com.takoy3466.manaitamtk.api.interfaces.IUseTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PacketMultiple extends AbstractMTKPacket<Integer> implements ISimpleCapability<IMultiple>, IUseTag {
    public PacketMultiple(Integer msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.msg);
    }

    public static PacketMultiple decode(FriendlyByteBuf buf) {
        return new PacketMultiple(buf.readInt());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                execute(stack, (iMultiple -> {
                    CompoundTag tag = stack.getOrCreateTag();
                    iMultiple.setMultiple(this.msg);
                    tag.put(getPath(), iMultiple.serializeNBT());
                }));
            }
        });
    }

    @Override
    public Capability<IMultiple> getCapability() {
        return MTKCapabilities.MULTIPLE;
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }
}
