package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketRange extends AbstractMTKPacket<Integer> {
    public PacketRange(int msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.msg);
    }

    public static PacketRange decode(FriendlyByteBuf buf) {
        return new PacketRange(buf.readInt());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                if (stack.is(ItemsInit.MANAITA_PICKAXE.get()) ||
                        stack.is(ItemsInit.MANAITA_PAXEL.get())) {

                    stack.getOrCreateTag().putInt("Range", this.msg);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
