package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.interfaces.IMTKPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractMTKPacket<T> implements IMTKPacket {

    public final T msg;

    public AbstractMTKPacket (T msg) {
        this.msg = msg;
    }

    @Override
    public abstract void encode(FriendlyByteBuf buf);

    @Override
    public abstract void handle(Supplier<NetworkEvent.Context> ctx);

    public void handleWork(Supplier<NetworkEvent.Context> ctx, Consumer<ServerPlayer> consumer) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            consumer.accept(player);
        });
        ctx.get().setPacketHandled(true);
    }
}
