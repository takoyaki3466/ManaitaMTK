package com.takoy3466.ManaitaMTK.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockManaitaBase extends Block{
    private final int magnification;

    public BlockManaitaBase(int magnification) {
        super(Properties.of()
                .strength(0.5F,210000)
                .sound(SoundType.WOOD)); //音の追加
        this.magnification = magnification;
    }

    static int Size;

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        Size = 0;
        while (Size < magnification){
            // 手に持ってるアイテムをコピー
            ItemStack copyItem = player.getMainHandItem().copy();
            // コピーアイテムの数を決める(１個)
            copyItem.setCount(1);

            //もしインベントリに空のスロットがあるなら
            if (player.getInventory().getFreeSlot() >= 1){
                //コピーをインベントリーに追加
                player.getInventory().add(copyItem);
            }
            //コピーをブロックの位置にドロップ
            else Block.popResource(world, pos, copyItem);
            Size++;
        }
        return InteractionResult.SUCCESS;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("x" + magnification + " only!!")
                .withStyle(ChatFormatting.WHITE)
        );
    }
}
