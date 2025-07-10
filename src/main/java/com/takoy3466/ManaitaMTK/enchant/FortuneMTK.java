package com.takoy3466.ManaitaMTK.enchant;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKEnchantments;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class FortuneMTK extends Enchantment {
    public FortuneMTK() {
        super(Rarity.COMMON, EnchantmentCategory.DIGGER, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getMinLevel() {
        return 10;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();

        if (level.isClientSide()) return;
        if (player instanceof ServerPlayer sPlayer){
            if (sPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE)return;
        }

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack tool = player.getMainHandItem();

        // カスタムエンチャントのレベルを取得
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ManaitaMTKEnchantments.FORTUNE_MTK.get(), tool);
        if (enchantmentLevel <= 0) return;

        // 元のドロップ取得
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), player, tool);

        // イベントをキャンセル & 手動でドロップ
        event.setCanceled(true);
        level.destroyBlock(pos, false); // false = ドロップしない

        // ドロップをenchantmentLevel倍にして落とす
        for (ItemStack drop : drops) {
            ItemStack multiplied = drop.copy();
            multiplied.setCount(drop.getCount() * enchantmentLevel);
            Block.popResource(level, pos, multiplied);
        }

        // 道具の耐久を減らす
        tool.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(InteractionHand.MAIN_HAND));
    }
}
