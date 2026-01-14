package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFlySpeed extends AbstractMTKPacket<Float> {
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
                MTKCapabilityHelper.execute(MTKCapabilities.FLY, player.getItemBySlot(EquipmentSlot.HEAD), iFly -> iFly.setFlySpeed(this.msg));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
