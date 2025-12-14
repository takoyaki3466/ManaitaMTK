package com.takoy3466.manaitamtk.eventSubscriber;

import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.init.EnchantmentsInit;
import com.takoy3466.manaitamtk.item.tool.ToolManaitaSword;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
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

public class MTKSubscribeEvent {

    // 死ぬ時のイベント
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }
    }

    // 攻撃されたときのイベント
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }
    }

    // ダメージを受けた時のイベント
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if ((event.getEntity() instanceof Player player)) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setAmount(0.0f);
                event.setCanceled(true);
            }
        }
    }

    // HP減少を受けた時のイベント
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
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
        ItemStack stack = event.getItemStack();

        if (!level.isClientSide && stack.getItem() == ItemsInit.MANAITA_SWORD.get()) {
            double radius = (double) MTKConfig.SWORD_KILL_RADIUS.get() * 100;
            boolean killTarget = ToolManaitaSword.modeNumber == 1;


            List<LivingEntity> targets = killTarget ?
                    level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(radius / 10),entity -> (entity != player) && (entity instanceof LivingEntity)) //プレイヤー以外の前LivingEntity
                    : level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius), entity -> (entity != player) && (entity instanceof Enemy) //プレイヤー以外の敵全員
            );

            for (LivingEntity target : targets) {
                // 雷を生成
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                lightning.wasOnFire = false;
                if (player instanceof ServerPlayer sPlayer) {
                    lightning.moveTo(target.position());
                    lightning.setCause(sPlayer);
                    if (!target.isDeadOrDying()) {
                        level.addFreshEntity(lightning);
                    }
                }
                Holder<DamageType> holder = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC);
                target.hurt(new DamageSource(holder), Float.MAX_VALUE);
                target.setHealth(0.0f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (!player.level().isClientSide()) {
            // 特定の装備をつけているか確認
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, false, false, false));
                if (player.getFoodData().getFoodLevel() < 20) {
                    player.getFoodData().setFoodLevel(20);
                }
            }
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
                    player.onUpdateAbilities();

                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.teleport(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                                serverPlayer.getYRot(), serverPlayer.getXRot());
                    }
                }
            }else {
                if (current.getItem() == ItemsInit.HELMET_MANAITA.get()) {
                    player.getAbilities().mayfly = true;
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
            ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
            if (stack.getItem() == ItemsInit.HELMET_MANAITA.get()) {
                float modeF = stack.getOrCreateTag().getFloat("FlySpeed");
                player.getAbilities().setFlyingSpeed(modeF);
                player.onUpdateAbilities();
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBackEvent(LivingKnockBackEvent event) {
        if ((event.getEntity() instanceof Player player)) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setRatioX(0);
                event.setRatioZ(0);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onExperienceDrop(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemsInit.HELMET_MANAITA.get()) {
                event.setCanceled(true);
            }
        }
    }
}