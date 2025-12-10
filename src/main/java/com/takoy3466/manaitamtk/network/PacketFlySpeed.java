package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.apiMTK.IMTKPacket;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFlySpeed implements IMTKPacket {
    private final float msg;
    public PacketFlySpeed(float msg) {
        this.msg = msg;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(this.msg);
    }

    public static PacketFlySpeed decode(FriendlyByteBuf buf) {
        return new PacketFlySpeed(buf.readFloat());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
                if (stack.is(ItemsInit.HELMET_MANAITA.get())) {
                    stack.getOrCreateTag().putFloat("FlySpeed", this.msg);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
