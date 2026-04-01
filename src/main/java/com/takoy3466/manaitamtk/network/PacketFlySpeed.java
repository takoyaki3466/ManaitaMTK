package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.capability.interfaces.IFly;
import com.takoy3466.manaitamtk.core.interfaces.ISimpleCapability;
import com.takoy3466.manaitamtk.core.interfaces.IUseTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PacketFlySpeed extends AbstractMTKPacket<Float> implements ISimpleCapability<IFly>, IUseTag {
    public PacketFlySpeed(float msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(this.msg);
    }

    public static PacketFlySpeed decode(FriendlyByteBuf buf) {
        return new PacketFlySpeed(buf.readFloat());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                MTKCapabilityHelper.execute(MTKCapabilities.FLY, player.getItemBySlot(EquipmentSlot.HEAD), iFly -> {
                    CompoundTag tag = player.getItemBySlot(EquipmentSlot.HEAD).getOrCreateTag();
                    iFly.setFlySpeed(this.msg);
                    tag.put(getPath(), iFly.serializeNBT());
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    @Override
    public Capability<IFly> getCapability() {
        return MTKCapabilities.FLY;
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }
}
