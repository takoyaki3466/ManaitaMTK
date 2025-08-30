package com.takoy3466.manaitamtk.block;

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

public class BlockManaita extends Block {

    public BlockManaita() {
        super(Properties.of()
                .strength(0.5F,210000)
                .sound(SoundType.WOOD)); //音の追加
    }

    static int Sizemode, Size, whilenum;

    private void SizeUP(){
        if (Sizemode < 6){
            Sizemode++;
        }
        else{
            Sizemode = 0;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (player.isSteppingCarefully()){
            SizeUP();
            switch (Sizemode){
                case 0:
                    Size = 1;
                    break;
                case 1:
                    Size = 2;
                    break;
                case 2:
                    Size = 4;
                    break;
                case 3:
                    Size = 8;
                    break;
                case 4:
                    Size = 16;
                    break;
                case 5:
                    Size = 32;
                    break;
                case 6:
                    Size = 64;
                    break;
                default:
                    break;
            }
            player.displayClientMessage(Component.literal("MODE : 2^" + Sizemode),true);
        }
        else {
            whilenum = 0;
            while (whilenum < Size) {
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
                whilenum++;
            }
        }
        return InteractionResult.SUCCESS;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("can change!!! -> x1,x2,x4,x8,x16,x32,x64")
                .withStyle(ChatFormatting.WHITE)
        );
    }
}