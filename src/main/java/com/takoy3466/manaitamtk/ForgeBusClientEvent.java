package com.takoy3466.manaitamtk;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.item.ChangeableMagnificationPortableDCT;
import com.takoy3466.manaitamtk.item.tool.MTKSwitcherScreen;
import com.takoy3466.manaitamtk.item.tool.ToolManaitaSword;
import com.takoy3466.manaitamtk.screen.MTKBackPackScreen;
import com.takoy3466.manaitamtk.screen.MTKChestScreen;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

@Mod.EventBusSubscriber(modid = "manaitamtk", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeBusClientEvent {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;
        Item item = minecraft.player.getMainHandItem().getItem();

        boolean hasItem = item == ItemsInit.MANAITA_PICKAXE.get() || item == ItemsInit.MANAITA_PAXEL.get();
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

    private static final ResourceLocation ID = new ResourceLocation(ManaitaMTK.MOD_ID, "manaita_paxel_open_screen");
    private static boolean isDone = false;
    private static boolean isDown;

    @SubscribeEvent
    public static void onAdvancementKey(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;
        Player player = minecraft.player;
        if (isDone) return;
        if (!isDown) {
            if (MTKKeyMapping.MTKSwitcherOpenKey.isDown()) {
                isDown = true;
            }
        }
        if (isDown) {
            if (!player.level().isClientSide()) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(ID);
                if (advancement != null) {
                    AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) {
                        for (String criteria : progress.getRemainingCriteria()) {
                            serverPlayer.getAdvancements().award(advancement, criteria);
                            isDone = true;
                        }
                    }else if (!isDown) {
                        isDown = true;
                    }
                }
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

        if (item == ItemsInit.MANAITA_PICKAXE.get()) {
            MTKSwitcherScreen.MTKIcon mtkIcon = MTKSwitcherScreen.MTKIcon.getFromRange(player.getMainHandItem().getOrCreateTag().getInt("Range"));
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            graphics.renderItem(mtkIcon.getRenderStack(), FLAME_X + 4, FLAME_Y + 4);

        }
        else if (item == ItemsInit.MANAITA_PAXEL.get()) {
            MTKSwitcherScreen.MTKIcon mtkIcon = MTKSwitcherScreen.MTKIcon.getFromRange(player.getMainHandItem().getOrCreateTag().getInt("Range"));
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            graphics.renderItem(mtkIcon.getRenderStack(), FLAME_X + 4, FLAME_Y + 4);

        }
        else if (item == ItemsInit.CHANGEABLE_PORTABLE_DCT.get()) {
            graphics.blit(FLAME_TEXTURE , FLAME_X, FLAME_Y , 0, 0 , 24, 24 , 24, 24);
            ItemStack stack;
            switch (ChangeableMagnificationPortableDCT.magnification) {
                case 1 -> stack = Items.CRAFTING_TABLE.getDefaultInstance();
                case 4 -> stack = Items.STONE.getDefaultInstance();
                case 8 -> stack = Items.IRON_INGOT.getDefaultInstance();
                case 16 -> stack = Items.GOLD_INGOT.getDefaultInstance();
                case 64 -> stack = ItemsInit.CRUSHED_MTK.get().getDefaultInstance();
                default -> stack = ItemStack.EMPTY;
            }
            graphics.renderItem(stack, FLAME_X + 4, FLAME_Y + 4);

        }
        else if (item == ItemsInit.MANAITA_SWORD.get()) {
            boolean killTarget = ToolManaitaSword.modeNumber == 1;
            int xSword = minecraft.getWindow().getGuiScaledWidth() / 2 - minecraft.font.width(killTarget? SWORD_TEXT_ALL.getString() : SWORD_TEXT_ENEMY.getString()) / 2;
            int ySword = minecraft.getWindow().getGuiScaledHeight() - 49 - minecraft.font.lineHeight;
            graphics.drawString(minecraft.font, killTarget? SWORD_TEXT_ALL : SWORD_TEXT_ENEMY, xSword, ySword, killTarget? Color.RED.getRGB() : Color.GRAY.getRGB());
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
}
