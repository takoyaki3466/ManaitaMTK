package com.takoy3466.manaitamtk.block.abstracts;

import com.takoy3466.manaitamtk.core.abstracts.AbstractFurnaceMultipler;
import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.block.blockEntity.abstracts.AbstractMultiFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBlockMultiFurnace extends AbstractFurnaceMultipler {

    public AbstractBlockMultiFurnace(MTKTier mtkTier) {
        super(Properties.of().sound(SoundType.WOOD), mtkTier);
    }

    @Nullable
    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        if (level.isClientSide()) {
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AbstractMultiFurnaceBlockEntity furnaceBlockEntity) {
            if (player instanceof ServerPlayer serverPlayer) {
                NetworkHooks.openScreen(serverPlayer, furnaceBlockEntity, pos);
            }
        }
    }
}
