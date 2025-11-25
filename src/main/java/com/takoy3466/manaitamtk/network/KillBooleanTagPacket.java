package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.apiMTK.MTKPacket;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KillBooleanTagPacket extends MTKPacket<KillBooleanTagPacket> {
    private final boolean msg;
    public KillBooleanTagPacket(boolean msg) {
        this.msg = msg;
    }
    public void encode(KillBooleanTagPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.msg);
    }

    public static KillBooleanTagPacket decode(FriendlyByteBuf buf) {
        return new KillBooleanTagPacket(buf.readBoolean());
    }

    public void handle(KillBooleanTagPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                if (stack.is(ItemsInit.MANAITA_SWORD.get())) {
                    stack.getOrCreateTag().putBoolean("Bool", packet.msg);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
