package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MTKNetwork {
    private static int ID = 0;
    public static SimpleChannel CHANNEL;
    private static final String PROTOCOL_VERSION = "1.0";

    public static void register() {
        CHANNEL = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ManaitaMTK.MOD_ID, "network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        CHANNEL.registerMessage(ID++
                , RangePacket.class
                , (packet, buf) -> packet.encode(packet, buf)
                , RangePacket::decode
                , (packet, contextSupplier) -> packet.handle(packet, contextSupplier)
        );

        CHANNEL.registerMessage(ID++
                , KillBooleanTagPacket.class
                , (packet, buf) -> packet.encode(packet, buf)
                , KillBooleanTagPacket::decode
                , (packet, contextSupplier) -> packet.handle(packet, contextSupplier)
        );
    }
}
