package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.util.slot.MTKItemStackHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockMTKChest extends Block implements EntityBlock {

    public BlockMTKChest() {
        super(Properties.of().strength(10F,21000000).sound(SoundType.WOOD));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlockEntitiesInit.MTK_CHEST.get().create(blockPos, blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        if(player instanceof ServerPlayer sPlayer && blockEntity instanceof MTKChestBlockEntity chestEntity) {
            NetworkHooks.openScreen(sPlayer, chestEntity, pos);
        }

        return InteractionResult.CONSUME;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MTKChestBlockEntity chestEntity) {
                MTKItemStackHandler inventory = chestEntity.getItemHandler();
                for (int index = 0; index < inventory.getSlots(); index++) {
                    ItemStack stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                    level.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return super.getVisualShape(state, getter, pos, collisionContext);
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getShadeBrightness(BlockState p_60472_, BlockGetter p_60473_, BlockPos p_60474_) {
        return 1.0F;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos pos) {
        return true;
    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean skipRendering(BlockState state1, BlockState state, Direction p_60534_) {
        return state.is(this) || super.skipRendering(state1, state, p_60534_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.manaitamtk.mtk_chest.hover_text_1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("block.manaitamtk.mtk_chest.hover_text_2").withStyle(ChatFormatting.GRAY));
    }
}
