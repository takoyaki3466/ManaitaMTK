package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntityBase;
import com.takoy3466.manaitamtk.regi.ManaitaMTKBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockMTKFurnace extends AbstractFurnaceBlock {
    private final MTKEnum mtkEnum;

    public BlockMTKFurnace(MTKEnum mtkEnum) {
        super(Properties.of().strength(0.5F,210000).sound(SoundType.WOOD));
        this.mtkEnum= mtkEnum;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide()) return;
        if (player instanceof ServerPlayer sPlayer) {
            switch (mtkEnum) {
                case WOOD -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityWood furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case STONE -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityStone furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case IRON -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityIron furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case GOLD -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityGold furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case DIAMOND -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityDiamond furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case MTK -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityMTK furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case GODMTK -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityGODMTK furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                case BREAK -> {
                    if (blockEntity instanceof MTKFurnaceBlockEntity.FurnaceEntityBreak furnaceBlockEntity) {
                        NetworkHooks.openScreen(sPlayer, furnaceBlockEntity, pos);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + mtkEnum);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        switch (mtkEnum) {
            case WOOD -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityWood(pos, state, mtkEnum);
            }
            case STONE -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityStone(pos, state, mtkEnum);
            }
            case IRON -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityIron(pos, state, mtkEnum);
            }
            case GOLD -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityGold(pos, state, mtkEnum);
            }
            case DIAMOND -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityDiamond(pos, state, mtkEnum);
            }
            case MTK -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityMTK(pos, state, mtkEnum);
            }
            case GODMTK -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityGODMTK(pos, state, mtkEnum);
            }
            case BREAK -> {
                return new MTKFurnaceBlockEntity.FurnaceEntityBreak(pos, state, mtkEnum);
            }
            default -> {
                return  null;
            }
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        BlockEntityType<? extends MTKFurnaceBlockEntityBase> blockEntityType = getBlockEntityType();
        switch (mtkEnum) {
            case WOOD -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityWood::serverTick);
            }
            case STONE -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityStone::serverTick);
            }
            case IRON -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityIron::serverTick);
            }
            case GOLD -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityGold::serverTick);
            }
            case DIAMOND -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityDiamond::serverTick);
            }
            case MTK -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityMTK::serverTick);
            }
            case GODMTK -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityGODMTK::serverTick);
            }
            case BREAK -> {
                return createTickerHelper(type, blockEntityType, MTKFurnaceBlockEntity.FurnaceEntityBreak::serverTick);
            }
            default -> throw new IllegalStateException("Unexpected value: " + mtkEnum);
        }
    }

    private BlockEntityType<? extends MTKFurnaceBlockEntityBase> getBlockEntityType() {
        BlockEntityType<? extends MTKFurnaceBlockEntityBase> blockEntityType;
        switch (mtkEnum) {
            case WOOD -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_WOOD.get();
            case STONE -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_STONE.get();
            case IRON -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_IRON.get();
            case GOLD -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_GOLD.get();
            case DIAMOND -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_DIAMOND.get();
            case MTK -> blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_MTK.get();
            case GODMTK ->blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_GODMTK.get();
            case BREAK ->blockEntityType = ManaitaMTKBlocks.BlockEntities.MTK_FURNACE_BREAK.get();
            default -> blockEntityType = null;
        }
        return blockEntityType;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        Component c;
        switch (mtkEnum) {
            case WOOD, STONE, GOLD, DIAMOND, GODMTK -> {return;}
            case IRON -> c = Component.translatable("block.manaitamtk.mtk_furnace_iron.hover_text");
            case MTK -> c = Component.translatable("block.manaitamtk.mtk_furnace_mtk.hover_text");
            case BREAK -> c = Component.translatable("block.manaitamtk.mtk_furnace_break.hover_text");
            default -> c = Component.literal(String.valueOf(new IllegalStateException("Unexpected value: " + mtkEnum)));
        }
        list.add(c);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
        if (state.getValue(LIT)) {
            double x = (double) pos.getX() + 0.5;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5;
            if (source.nextDouble() < 0.1) {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double v = source.nextDouble() * 0.6 - 0.3;
            double x1 = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : v;
            double x2 = source.nextDouble() * 6.0 / 16.0;
            double x3 = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : v;
            level.addParticle(ParticleTypes.SMOKE, x + x1, y + x2, z + x3, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, x + x1, y + x2, z + x3, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean bool) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (this.mtkEnum == MTKEnum.BREAK && blockentity instanceof MTKFurnaceBlockEntity.FurnaceEntityBreak furnaceEntityBreak) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), furnaceEntityBreak.getItem(0));
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), furnaceEntityBreak.getItem(1));
                ItemStack dropResultItem = furnaceEntityBreak.getItem(2);
                if (!dropResultItem.isEmpty()) {
                    dropResultItem.setCount(64);
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), dropResultItem);
                }
            }
            Containers.dropContents(level, pos, (Container) blockentity);
            level.updateNeighbourForOutputSignal(pos, this);

            super.onRemove(state, level, pos, state1, bool);
        }

    }
}
