package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.apiMTK.BaseTickerEntityBlock;
import com.takoy3466.manaitamtk.block.blockEntity.AutoWorkbenchMTKBlockEntity;
import com.takoy3466.manaitamtk.init.BlocksInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

// テクスチャを書いてくれたトマトさんありがとう！
public class BlockAutoWorkbenchMTK extends BaseTickerEntityBlock {
    public BlockAutoWorkbenchMTK() {
        super(Properties.of().strength(5F,21000000).sound(SoundType.WOOD));
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        if(player instanceof ServerPlayer sPlayer && blockEntity instanceof AutoWorkbenchMTKBlockEntity workbenchMTKBlock) {
            NetworkHooks.openScreen(sPlayer, workbenchMTKBlock, pos);
        }

        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlocksInit.BlockEntities.AUTO_WORKBENCH_MTK.get().create(blockPos, blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AutoWorkbenchMTKBlockEntity workbench) {
                ItemStackHandler inventory = workbench.getItemHandler();
                NonNullList<ItemStack> slots = workbench.getItems();
                for (int index = 0; index < inventory.getSlots(); index++) {
                    ItemStack stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                    level.addFreshEntity(entity);
                }
                for (ItemStack stack : slots) {
                    var entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                    level.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
