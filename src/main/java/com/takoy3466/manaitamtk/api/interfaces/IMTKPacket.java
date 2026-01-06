package com.takoy3466.manaitamtk.api.interfaces;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMTKPacket {

    void encode(FriendlyByteBuf buf);

    void handle(Supplier<NetworkEvent.Context> ctx);
}
