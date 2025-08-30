package com.takoy3466.manaitamtk;

import com.mojang.blaze3d.platform.InputConstants;
import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.item.armor.HelmetManaita;
import com.takoy3466.manaitamtk.screen.MTKBackPackScreen;
import com.takoy3466.manaitamtk.screen.MTKChestScreen;
import com.takoy3466.manaitamtk.config.MTKConfig;
import com.takoy3466.manaitamtk.item.ChangeableMagnificationPortableDCT;
import com.takoy3466.manaitamtk.item.tool.MTKSwitcherScreen;
import com.takoy3466.manaitamtk.item.tool.ToolManaitaPaxel;
import com.takoy3466.manaitamtk.item.tool.ToolManaitaPickaxe;
import com.takoy3466.manaitamtk.regi.ManaitaMTKEnchantments;
import com.takoy3466.manaitamtk.regi.ManaitaMTKItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MTKSubscribeEvent {

    // 死ぬ時のイベント
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setCanceled(true);
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

    // HP減少を受けた時のイベント
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

        boolean hasItem = item == ManaitaMTKItems.MANAITA_PICKAXE.get() || item ==ManaitaMTKItems.MANAITA_PAXEL.get();
        if (!hasItem) return;
        if (MTKKeyMapping.MTKSwitcherOpenKey.matches(event.getKey(), event.getScanCode())) {
            if (event.getAction() == GLFW.GLFW_PRESS) {
                minecraft.setScreen(new MTKSwitcherScreen());
            } else if (event.getAction() == GLFW.GLFW_RELEASE) {
                if (minecraft.screen instanceof MTKSwitcherScreen) {
                    minecraft.setScreen(null);
                }
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



            List<LivingEntity> targets = bool ?
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

        if (!player.level().isClientSide) {
            // 特定の装備をつけているか確認
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, false, false, false));
                player.getAbilities().mayfly = true;
                if (player.getFoodData().getFoodLevel() < 20) {
                    player.getFoodData().setFoodLevel(20);
                }
            } else {
                player.getAbilities().mayfly = false;
            }
        }
    }

    private static int modeNum = 0;
    private static final Component FIRST_TEXT = Component.translatable("item.manaitamtk.fwai.first_text");
    @SubscribeEvent
    public static void flySpeedEvent(TickEvent.PlayerTickEvent event) {
        float modeF;
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (!player.level().isClientSide) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                if (MTKKeyMapping.FlySpeedKey.consumeClick()) {
                    modeNum = modeNum < 3 ? modeNum + 1 : 0;
                    modeF = switch (modeNum) {
                        case 1 -> 0.1F;
                        case 2 -> 0.2F;
                        case 3 -> 0.6F;
                        default -> 0.05F;
                    };
                    HelmetManaita.modeF = modeF;
                    player.getAbilities().setFlyingSpeed(modeF);
                    player.displayClientMessage(Component.literal(FIRST_TEXT.getString() + modeF), true);
                }
            }
        }
    }
    private static final Component component = Component.translatable("tooltip.amount");

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Screen screen = Minecraft.getInstance().screen;
        if (screen instanceof MTKChestScreen || screen instanceof MTKBackPackScreen) {
            int count = event.getItemStack().getCount();
            if (count > 1) {
                event.getToolTip().add(Component.literal(component.getString() + count));
            }
        }
    }

    @SubscribeEvent
    public static void onRenderGuiOverlayEvent(RenderGuiOverlayEvent event) {
        ResourceLocation FLAME_TEXTURE = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/gui/switch_flame.png");
        Component SWORD_TEXT_ENEMY = Component.translatable("gui.overlay.sword.enemy_die");
        Component SWORD_TEXT_ALL = Component.translatable("gui.overlay.sword.all_die");
        /*
        ITEM_X = 14;
        ITEM_Y = 14;
        FLAME_LOCATION_X = 0;
        FLAME_LOCATION_Y = 0;
        FLAME_SIZE_X = 24;
        FLAME_SIZE_Y = 24;
        OVERALL_X = 24;
        OVERALL_Y = 24;
        */
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        GuiGraphics graphics = event.getGuiGraphics();

        int FLAME_X = 10;
        int FLAME_Y = minecraft.getWindow().getGuiScaledHeight() - 10 - 24;

        if (player == null || minecraft.options.renderDebug) return;
        Item item = player.getMainHandItem().getItem();

        if (item == ManaitaMTKItems.MANAITA_PICKAXE.get()) {
            MTKSwitcherScreen.MTKIcon mtkIcon = MTKSwitcherScreen.MTKIcon.getFromRange(ToolManaitaPickaxe.range);
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            graphics.renderItem(mtkIcon.getRenderStack(), FLAME_X + 4, FLAME_Y + 4);

        } else if (item == ManaitaMTKItems.MANAITA_PAXEL.get()) {
            MTKSwitcherScreen.MTKIcon mtkIcon = MTKSwitcherScreen.MTKIcon.getFromRange(ToolManaitaPaxel.range);
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            graphics.renderItem(mtkIcon.getRenderStack(), FLAME_X + 4, FLAME_Y + 4);

        } else if (item == ManaitaMTKItems.CHANGEABLE_PORTABLE_DCT.get()) {
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            ItemStack stack;
            switch (ChangeableMagnificationPortableDCT.magnification) {
                case 1 -> stack = Items.CRAFTING_TABLE.getDefaultInstance();
                case 4 -> stack = Items.STONE.getDefaultInstance();
                case 8 -> stack = Items.IRON_INGOT.getDefaultInstance();
                case 16 -> stack = Items.GOLD_INGOT.getDefaultInstance();
                case 64 -> stack = ManaitaMTKItems.CRUSHED_MTK.get().getDefaultInstance();
                default -> stack = ItemStack.EMPTY;
            }
            graphics.renderItem(stack, FLAME_X + 4, FLAME_Y + 4);

        } else if (item == ManaitaMTKItems.MANAITA_SWORD.get()) {
            int xSword = minecraft.getWindow().getGuiScaledWidth() / 2 - minecraft.font.width(bool? SWORD_TEXT_ALL.getString() : SWORD_TEXT_ENEMY.getString()) / 2;
            int ySword = minecraft.getWindow().getGuiScaledHeight() - 49 - minecraft.font.lineHeight;
            graphics.drawString(minecraft.font, bool? SWORD_TEXT_ALL : SWORD_TEXT_ENEMY, xSword, ySword, bool? Color.RED.getRGB() : Color.GRAY.getRGB());
        }
    }

    public static boolean bool;

    @SubscribeEvent
    public static void onPlayerTickSword(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (!player.level().isClientSide) {
            if (player.getMainHandItem().getItem() == ManaitaMTKItems.MANAITA_SWORD.get()) {
                if (player.isSteppingCarefully() && MTKKeyMapping.SwitchExtermination.consumeClick()) {
                    bool = !bool;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBackEvent(LivingKnockBackEvent event) {
        if ((event.getEntity() instanceof Player player)) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ManaitaMTKItems.HELMET_MANAITA.get()) {
                event.setRatioX(0);
                event.setRatioZ(0);
                event.setCanceled(true);
            }
        }
    }
}