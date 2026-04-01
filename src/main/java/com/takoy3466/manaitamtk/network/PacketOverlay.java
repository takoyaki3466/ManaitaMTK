package com.takoy3466.manaitamtk.network;

import com.mojang.serialization.Codec;
import com.takoy3466.manaitamtk.core.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.core.interfaces.IOverlay;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class PacketOverlay extends AbstractMTKPacket<List<MTKOverlayIcon>> {
    private final BlockPos pos;

    private static final Codec<List<MTKOverlayIcon>> LIST_CODEC = MTKOverlayIcon.CODEC.listOf();

    public PacketOverlay(List<MTKOverlayIcon> msg, BlockPos pos) {
        super(msg);
        this.pos = pos;
    }


    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeJsonWithCodec(LIST_CODEC, msg);
        buf.writeBlockPos(pos);
    }

    public static PacketOverlay decode(FriendlyByteBuf buf) {
        return new PacketOverlay(buf.readJsonWithCodec(LIST_CODEC), buf.readBlockPos());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                Level level = player.level();
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof IOverlay overlayBlockEntity) {
                    overlayBlockEntity.setIconList(msg);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
