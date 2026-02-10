package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.api.abstracts.AbstractFurnaceMultipler;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.block.blockEntity.MultiFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BlockMultiFurnace extends AbstractFurnaceMultipler {

    public BlockMultiFurnace(MTKTier mtkTier) {
        super(Properties.of().sound(SoundType.WOOD), mtkTier);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MultiFurnaceBlockEntity(blockPos, blockState, getMTKTier());
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        if (level.isClientSide()) {
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof MultiFurnaceBlockEntity furnaceBlockEntity)) {
            return;
        }
        BlockState state = furnaceBlockEntity.getBlockState();
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, createMenuProvider(pos, state), pos);
        }
    }
}
