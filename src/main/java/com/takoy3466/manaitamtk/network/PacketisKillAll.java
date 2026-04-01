package com.takoy3466.manaitamtk.network;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.abstracts.AbstractMTKPacket;
import com.takoy3466.manaitamtk.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.capability.interfaces.IKillSword;
import com.takoy3466.manaitamtk.core.interfaces.ISimpleCapability;
import com.takoy3466.manaitamtk.core.interfaces.IUseTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PacketisKillAll extends AbstractMTKPacket<Boolean> implements ISimpleCapability<IKillSword>, IUseTag {
    public PacketisKillAll(Boolean msg) {
        super(msg);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.msg);
    }

    public static PacketisKillAll decode(FriendlyByteBuf buf) {
        return new PacketisKillAll(buf.readBoolean());
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getMainHandItem();
                MTKCapabilityHelper.execute(MTKCapabilities.KILL_SWORD, stack, iKillSword -> {
                    CompoundTag tag = stack.getOrCreateTag();
                    iKillSword.setIsKillAll(this.msg);
                    tag.put(getPath(), iKillSword.serializeNBT());
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    @Override
    public Capability<IKillSword> getCapability() {
        return MTKCapabilities.KILL_SWORD;
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }
}
