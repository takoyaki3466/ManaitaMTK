package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.MTKTiers;
import com.takoy3466.manaitamtk.api.interfaces.IMTKMultiple;
import com.takoy3466.manaitamtk.api.interfaces.ITickableBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.AbstractMTKFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
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

public abstract class AbstractBlockMTKFurnace extends AbstractFurnaceBlock implements IMTKMultiple {
    private final MTKTier mtkTier;

    public AbstractBlockMTKFurnace(MTKTier mtkTier) {
        super(Properties.of().strength(0.5F,210000).sound(SoundType.WOOD));
        this.mtkTier = mtkTier;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return ITickableBlockEntity.getTickerHelper(level);
        }
        return null;
    }

    public MenuProvider createMenuProvider(BlockPos pos, BlockState state) {
        if (newBlockEntity(pos, state) instanceof MenuProvider provider) {
            return provider;
        }
        return null;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        if (level.isClientSide()) {
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof AbstractMTKFurnaceBlockEntity furnaceBlockEntity)) {
            return;
        }
        BlockState state = furnaceBlockEntity.getBlockState();
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, createMenuProvider(pos, state), pos);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        Component c;
        switch (getMultiple()) {
            case 2, 4, 8, 32, 512 -> {return;}
            case 16 -> c = Component.translatable("block.manaitamtk.mtk_furnace_iron.hover_text");
            case 64 -> c = Component.translatable("block.manaitamtk.mtk_furnace_mtk.hover_text");
            case 33554431 -> c = Component.translatable("block.manaitamtk.mtk_furnace_break.hover_text");
            default -> c = Component.literal(String.valueOf(new IllegalStateException("Unexpected value: " + mtkTier)));
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
            if (blockentity instanceof AbstractMTKFurnaceBlockEntity furnaceBlockEntity) {
                if (getMTKTier() == MTKTiers.BREAK) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), furnaceBlockEntity.getItem(0));
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), furnaceBlockEntity.getItem(1));
                    ItemStack dropResultItem = furnaceBlockEntity.getItem(2);
                    if (!dropResultItem.isEmpty()) {
                        dropResultItem.setCount(64);
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), dropResultItem);
                    }
                }
                Containers.dropContents(level, pos, furnaceBlockEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, state1, bool);
        }
    }

    @Override
    public MTKTier getMTKTier() {
        return this.mtkTier;
    }

    @Override
    public int getMultiple() {
        return getMTKTier().getMultiple();
    }

    @Override
    public String getMTKName() {
        return getMTKTier().getName();
    }
}
