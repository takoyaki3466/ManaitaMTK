package com.takoy3466.manaitamtk.eventSubscriber;

import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.helper.MTKCapabilityHelper;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.init.EnchantmentsInit;
import com.takoy3466.manaitamtk.util.WeaponUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.function.Predicate;

public class MTKSubscribeEvent {

    // 死ぬ時のイベント
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        MTKEventHelper.cancel(event);
    }

    // 攻撃されたときのイベント
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        MTKEventHelper.cancel(event);
    }

    // ダメージを受けた時のイベント
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        MTKEventHelper.cancel(event);
    }

    // HP減少を受けた時のイベント
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        MTKEventHelper.cancel(event);
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
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsInit.FORTUNE_MTK.get(), tool);
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
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.level();
        InteractionHand hand = event.getHand();
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }
        ItemStack stack = serverPlayer.getItemInHand(hand);

        if (!level.isClientSide() && stack.getItem() == ItemsInit.MANAITA_SWORD.get()) {
            double radius = (double) MTKConfig.SWORD_KILL_RADIUS.get() * 100;

            MTKCapabilityHelper.execute(MTKCapabilities.KILL_SWORD, player, hand, iKillSword -> {
                Class<LivingEntity> entityClass = LivingEntity.class;
                Predicate<LivingEntity> allEntity = entity -> (entity != player) && (entity instanceof LivingEntity);
                Predicate<LivingEntity> onlyEnemy = entity -> (entity != player) && (entity instanceof Enemy);

                List<LivingEntity> targets = iKillSword.isKillAll() ?
                        WeaponUtil.selectTargets(entityClass, level, player, radius/10, allEntity)
                        : WeaponUtil.selectTargets(entityClass, level, player, radius, onlyEnemy);

                iKillSword.kill(targets, level, player);

            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (!player.level().isClientSide()) {
            // 特定の装備をつけているか確認
            MTKCapabilityHelper.execute(MTKCapabilities.INVINCIBLE, player, EquipmentSlot.HEAD, iInvincible -> {
                if (iInvincible.isInvincible()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, false, false, false));
                    if (player.getFoodData().getFoodLevel() < 20) {
                        player.getFoodData().setFoodLevel(20);
                    }
                }

            });
        }
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity == null) return;

        if (entity instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) return;
            ItemStack previous = event.getFrom();
            ItemStack current = event.getTo();
            if (previous.getItem() == ItemsInit.HELMET_MANAITA.get()) {
                if (current.getItem() != ItemsInit.HELMET_MANAITA.get()) {
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                    MTKCapabilityHelper.execute(MTKCapabilities.FLY, player, EquipmentSlot.HEAD, iFly -> iFly.setCanFly(false));
                    player.onUpdateAbilities();

                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.teleport(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                                serverPlayer.getYRot(), serverPlayer.getXRot());
                    }
                }
            }else {
                if (current.getItem() == ItemsInit.HELMET_MANAITA.get()) {
                    player.getAbilities().mayfly = true;
                    MTKCapabilityHelper.execute(MTKCapabilities.FLY, player, EquipmentSlot.HEAD, iFly -> iFly.setCanFly(true));
                    player.onUpdateAbilities();
                }
            }
        }
    }

    @SubscribeEvent
    public static void flySpeedEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (!player.level().isClientSide()) {
            MTKCapabilityHelper.execute(MTKCapabilities.FLY, player, EquipmentSlot.HEAD, iFly -> {
                player.getAbilities().setFlyingSpeed(iFly.getFlySpeed());
                player.onUpdateAbilities();
            });

        }
    }

    @SubscribeEvent
    public static void onLivingKnockBackEvent(LivingKnockBackEvent event) {
        MTKEventHelper.cancel(event);
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        MTKEventHelper.cancel(event);
    }

    @SubscribeEvent
    public static void onExperienceDrop(LivingExperienceDropEvent event) {
        MTKEventHelper.cancel(event);
    }
}