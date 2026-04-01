package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.core.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.init.TriggersInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketRightClickStack extends AbstractMTKPacket<ItemStack> {
    public PacketRightClickStack(ItemStack msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeItemStack(msg, false);
    }

    public static PacketRightClickStack decode(FriendlyByteBuf buf) {
        return new PacketRightClickStack(buf.readItem());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        handleWork(ctx, serverPlayer -> {
            if (serverPlayer != null) {
                TriggersInit.RIGHT_CLICK_TRIGGER.trigger(serverPlayer, msg);
            }
        });
    }
}
