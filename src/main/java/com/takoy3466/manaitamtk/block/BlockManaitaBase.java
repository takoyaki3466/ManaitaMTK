package com.takoy3466.manaitamtk.block;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.abstracts.AbstractBlockMultipler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
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

import java.util.ArrayList;
import java.util.List;

public class BlockManaitaBase extends AbstractBlockMultipler {

    public BlockManaitaBase(MTKTier mtkTier) {
        super(Properties.of()
                .strength(0.5F,210000)
                .sound(SoundType.WOOD), mtkTier); //音の追加
    }

    // whileループから変更
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack target = player.getMainHandItem().copy();
        int itemCount = getMultiple();
        Inventory inv = player.getInventory();
        if (target.isEmpty()) {
            return InteractionResult.PASS;
        }

        List<Integer> freeSlotIds = getFreeSlots(inv);

        for (int slotId : freeSlotIds) {
            if (itemCount <= 0) {
                break;
            }
            if (itemCount >= 64) {
                target.setCount(64);
                itemCount -= 64;
            } else {
                target.setCount(Math.max(itemCount, 0));
                itemCount = -1;
            }

            if (target.getCount() != 0) {
                inv.items.set(slotId, target);
            }
        }

        if (itemCount >= 1) {
            target.setCount(itemCount);
            Block.popResource(world, pos, target);
        }

        return InteractionResult.SUCCESS;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("x" + getMultiple() + " only!!")
                .withStyle(ChatFormatting.WHITE)
        );
    }

    public List<Integer> getFreeSlots(Inventory inv) {
        List<Integer> freeSlotIds = new ArrayList<>();

        for (int i = 0; i < inv.items.size(); i++) {
            if (inv.items.get(i).isEmpty()) {
                freeSlotIds.add(i);
            }
        }
        return freeSlotIds;
    }
}
