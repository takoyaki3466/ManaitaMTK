package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.core.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.init.TriggersInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMTKTrigger extends AbstractMTKPacket<String> {
    public PacketMTKTrigger(String msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(msg);
    }

    public static PacketMTKTrigger decode(FriendlyByteBuf buf) {
        return new PacketMTKTrigger(buf.readUtf());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        handleWork(ctx, serverPlayer -> {
            if (serverPlayer != null) {
                TriggersInit.MTK_TRIGGER.trigger(serverPlayer, msg);
            }
        });
    }
}
