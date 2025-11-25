package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.apiMTK.MTKPacket;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RangePacket extends MTKPacket<RangePacket> {
    private final int range;

    public RangePacket(int range) {
        this.range = range;
    }

    public void encode(RangePacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.range);
    }

    public static RangePacket decode(FriendlyByteBuf buf) {
        return new RangePacket(buf.readInt());
    }

    public void handle(RangePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                if (stack.is(ItemsInit.MANAITA_PICKAXE.get()) ||
                        stack.is(ItemsInit.MANAITA_PAXEL.get())) {

                    stack.getOrCreateTag().putInt("Range", msg.range);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
