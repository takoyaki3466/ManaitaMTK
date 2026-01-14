package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.helper.MTKCapabilityHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketisKillAll extends AbstractMTKPacket<Boolean> {
    public PacketisKillAll(Boolean msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.msg);
    }

    public static PacketisKillAll decode(FriendlyByteBuf buf) {
        return new PacketisKillAll(buf.readBoolean());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                MTKCapabilityHelper.execute(MTKCapabilities.KILL_SWORD, stack, iKillSword -> iKillSword.setIsKillAll(this.msg));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
