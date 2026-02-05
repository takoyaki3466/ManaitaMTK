package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.api.interfaces.IUseTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PacketRange extends AbstractMTKPacket<Integer> implements IUseTag {
    public PacketRange(int msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.msg);
    }

    public static PacketRange decode(FriendlyByteBuf buf) {
        return new PacketRange(buf.readInt());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                MTKCapabilityHelper.execute(MTKCapabilities.RANGE_BREAK, stack, iRangeBreak -> {
                    CompoundTag tag = stack.getOrCreateTag();
                    iRangeBreak.setRange(this.msg);
                    tag.put(getPath(), iRangeBreak.serializeNBT());
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }
}
