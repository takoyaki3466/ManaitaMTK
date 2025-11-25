package com.takoy3466.manaitamtk.apiMTK;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class MTKPacket<T extends MTKPacket<T>> {
    public abstract void encode(T t, FriendlyByteBuf buf);
    public abstract void handle(T t, Supplier<NetworkEvent.Context> ctx);
    //decodeだけstaticだからまとめれなかった
}
