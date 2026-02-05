package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.init.TriggersInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketScreenOpen extends AbstractMTKPacket<String> {
    public PacketScreenOpen(String msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.msg);
    }

    public static PacketScreenOpen decode(FriendlyByteBuf buf) {
        return new PacketScreenOpen(buf.readUtf());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        handleWork(ctx, serverPlayer -> {
            if (serverPlayer != null) {
                TriggersInit.MTK_OPEN_TRIGGER.trigger(serverPlayer, this.msg);
            }
        });
    }
}
