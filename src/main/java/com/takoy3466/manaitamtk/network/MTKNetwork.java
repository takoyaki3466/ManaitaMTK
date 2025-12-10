package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.apiMTK.IMTKPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public class MTKNetwork {
    private static int IDs = 0;
    private static final String PROTOCOL_VERSION = "1.0";
    public static SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ManaitaMTK.MOD_ID, "network"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    public static <T extends IMTKPacket> void registerMessage(Class<T> tClass, Function<FriendlyByteBuf, T> decode) {
        CHANNEL.registerMessage(IDs++, tClass, IMTKPacket::encode, decode, IMTKPacket::handle);
    }

    public static void register() {
        registerMessage(PacketRange.class, PacketRange::decode);
        registerMessage(PacketFlySpeed.class, PacketFlySpeed::decode);

    }
}
