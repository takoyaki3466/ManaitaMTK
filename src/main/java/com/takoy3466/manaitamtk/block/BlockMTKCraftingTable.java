package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.MTKTiers;
import com.takoy3466.manaitamtk.api.abstracts.AbstractBlockMultipler;
import com.takoy3466.manaitamtk.api.interfaces.IHasMenuProvider;
import com.takoy3466.manaitamtk.menu.MTKCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// クラフト結果が2倍になる作業台ブロック
public class BlockMTKCraftingTable extends AbstractBlockMultipler implements IHasMenuProvider<MTKCraftingTableMenu> {
    private final Component guiName = Component.translatable("block.manaitamtk.mtk_crafting_table.title");

    public BlockMTKCraftingTable(MTKTier mtkTier) {
        super(Properties.of()
                .strength(0.5F,210000)
                .sound(SoundType.WOOD), mtkTier);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) { // サーバー側のみ実行
            if (player instanceof ServerPlayer serverPlayer) {
                // GUI を開く際に ContainerLevelAccess.create を渡す
                serverPlayer.openMenu(this.getMenuProvider(guiName.getString() + " x" + getMultiple(), player, hand));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        if (getMTKTier() == MTKTiers.BREAK) {
            list.add(Component.literal(" x33554431 !!")
                    .withStyle(ChatFormatting.RED));
        }else {
            list.add(Component.literal("x" + getMultiple())
                    .withStyle(ChatFormatting.WHITE));
        }
    }

    @Override
    public MTKCraftingTableMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new MTKCraftingTableMenu(id, inv, this.createAccess(player), getMTKTier());
    }
}