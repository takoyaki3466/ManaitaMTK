package com.takoy3466.ManaitaMTK.block.craftingmanaita;

import com.takoy3466.ManaitaMTK.Menu.DoubleCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// クラフト結果が2倍になる作業台ブロック
public class DoubleCraftingTableBlock extends Block {
    private final int magnification;
    private final String componentName;

    public DoubleCraftingTableBlock(Properties properties, int mag, String componentName) {
        super(Properties.of()
                .strength(0.5F,210000)
                .sound(SoundType.WOOD));
        this.magnification = mag;
        this.componentName = componentName;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) { // サーバー側のみ実行
            if (player instanceof ServerPlayer serverPlayer) {
                // GUI を開く際に ContainerLevelAccess.create を渡す
                serverPlayer.openMenu(new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.translatable("craft " + componentName);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        return new DoubleCraftingTableMenu(id, playerInventory, ContainerLevelAccess.create(world, pos), magnification);
                    }
                });
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal(componentName)
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
