package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.api.interfaces.IMTKPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

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
}
