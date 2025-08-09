package com.takoy3466.ManaitaMTK;

import com.mojang.blaze3d.platform.InputConstants;
import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.config.MTKConfig;
import com.takoy3466.ManaitaMTK.item.tool.MTKSwitcherScreen;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKEnchantments;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class MTKSubscribeEvent {

    // 死ぬ時のイベント
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setCanceled(true);
                player.setHealth(Player.MAX_HEALTH);
            }
        }
    }

    // 攻撃されたときのイベント
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }

    }

    // ダメージを受けた時のイベント
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if ((event.getEntity() instanceof Player player)) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setAmount(0.0f);
                event.setCanceled(true);
            }
        }
    }

    // HP減少を受けた時のイベント (?)
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = event.getPlayer().level();

        if (level.isClientSide()) return;
        if (player instanceof ServerPlayer sPlayer){
            if (sPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE) return;
        }

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack tool = player.getMainHandItem();

        // エンチャントのレベルを取得
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ManaitaMTKEnchantments.FORTUNE_MTK.get(), tool);
        if (enchantmentLevel <= 0) return;

        // ドロップ取得
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), player, tool);

        // イベントをキャンセル & 手動でドロップ
        event.setCanceled(true);
        level.destroyBlock(pos, false); // false = ドロップしない

        // ドロップを enchantmentLevel 倍にして落とす
        for (ItemStack drop : drops) {
            ItemStack multiplied = drop.copy();
            multiplied.setCount(drop.getCount() * enchantmentLevel * 2);
            Block.popResource(level, pos, multiplied);
        }

        // 道具の耐久を減らす
        tool.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(InteractionHand.MAIN_HAND));
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;
        Item item = minecraft.player.getMainHandItem().getItem();

        if (MTKKeyMapping.MTKSwitcherOpenKey.isDown()) {
            if (item == ManaitaMTKItems.MANAITA_PICKAXE.get() || item ==ManaitaMTKItems.MANAITA_PAXEL.get()) {
                minecraft.setScreen(new MTKSwitcherScreen());
            }
        } else if (InputConstants.isKeyDown(minecraft.getWindow().getWindow(), InputConstants.KEY_LSHIFT)) {
            if (minecraft.screen instanceof MTKSwitcherScreen) {
                minecraft.setScreen(null);
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack itemStack = event.getItemStack();

        if (!level.isClientSide && itemStack.getItem() == ManaitaMTKItems.MANAITA_SWORD.get()) {
            double radius = (double) MTKConfig.SWORD_KILL_RADIUS.get() * 100;

            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(radius),
                    entity -> (entity != player) && (entity instanceof Enemy) //プレイヤー以外の敵全員
            );

            for (int i = 0; i < targets.size(); i++) {
                LivingEntity target = targets.get(i);
                // 雷を生成
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                if (lightning != null) {
                    if (player instanceof ServerPlayer sPlayer) {
                        lightning.moveTo(target.position());
                        lightning.setCause(sPlayer); // プレイヤーが原因
                        level.addFreshEntity(lightning); // ワールドに雷を出す
                    }
                }

                target.setHealth(0.0f);
            }
            // うるさいからいらない
            //level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (!player.level().isClientSide) {
            // 特定の装備をつけているか確認
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, false, false, false));
                if (player.getFoodData().getFoodLevel() < 20) {
                    player.getFoodData().setFoodLevel(20);
                }
            }
        }
    }
}
