package com.takoy3466.ManaitaMTK.item.tool;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MTKSwitcherScreen extends Screen {
    private static final ResourceLocation MTK_SWITCHER_LOCATION = new ResourceLocation(ManaitaMTK.MOD_ID,"textures/gui/container/mtk_switcher.png");
    private static final int ALL_SLOTS_WIDTH = MTKIcon.values().length * 31 - 5;
    private MTKIcon previousMode;
    private MTKIcon currentlyMode;
    private int firstMouseX;
    private int firstMouseY;
    private boolean setFirstMousePos;
    private final List<MTKSlot> slots = Lists.newArrayList();
    private static final String UNDER_TEXT_FIRST = Component.translatable("gui.mtk_switcher_screen.first_title").getString();
    private static final String UNDER_TEXT_SECOND = Component.translatable("gui.mtk_switcher_screen.second_title").getString();
    private static final Component UNDER_TEXT_KEY = Component.literal(MTKKeyMapping.MTKSwitcherSelectKey.getKey().getDisplayName().getString()).withStyle(ChatFormatting.AQUA);
    private static final Component UNDER_TEXT = Component.literal(UNDER_TEXT_FIRST + UNDER_TEXT_KEY.getString() + UNDER_TEXT_SECOND);

    public MTKSwitcherScreen() {
        super(GameNarrator.NO_TITLE);
        this.currentlyMode = this.previousMode;
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y, float f) {
        graphics.pose().pushPose();
        RenderSystem.enableBlend();
        int xInt = this.width / 2 - 62;
        int yInt = this.height / 2 - 31 - 27;
        graphics.blit(MTK_SWITCHER_LOCATION, xInt, yInt, 0.0F, 0.0F, 125, 75, 128, 127);
        graphics.pose().popPose();
        super.render(graphics, x, y, f);
        if (this.currentlyMode != null) {
            graphics.drawCenteredString(this.font, this.currentlyMode.getName(), this.width / 2, this.height / 2 - 31 - 20, -1);
        }
        graphics.drawCenteredString(this.font, UNDER_TEXT, this.width / 2, this.height / 2 + 5, 16777215);
        if (!this.setFirstMousePos) {
            this.firstMouseX = x;
            this.firstMouseY = y;
            this.setFirstMousePos = true;
        }

        boolean bool = this.firstMouseX == x && this.firstMouseY == y;
        Iterator iterator = this.slots.iterator();

        while(iterator.hasNext()) {
            if (iterator.next() instanceof MTKSlot mtkSlot){
                mtkSlot.render(graphics, x, y, f);
                mtkSlot.setSelected(this.currentlyMode == mtkSlot.icon);
                if (!bool && mtkSlot.isHoveredOrFocused()) {
                    this.currentlyMode = mtkSlot.icon;
                }
            }
        }
    }

    @Override
    public void init() {
        super.init();
        this.setDefault();
        this.currentlyMode = this.previousMode;

        for(int i = 0; i < MTKIcon.VALUES.length; ++i) {
            MTKIcon mtkIcon = MTKIcon.VALUES[i];
            this.slots.add(new MTKSlot(mtkIcon, this.width / 2 - ALL_SLOTS_WIDTH / 2 + i * 31, this.height / 2 - 31));
        }
    }

    private void setDefault() {
        System.out.println(this.previousMode);
        System.out.println(this.currentlyMode);

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            ItemStack stack = minecraft.player.getMainHandItem();
            if (stack.getItem() == ManaitaMTKItems.MANAITA_PICKAXE.get() || stack.getItem() == ManaitaMTKItems.MANAITA_PAXEL.get()) {
                System.out.println(stack.getOrCreateTag().getInt("Range"));
                this.previousMode = MTKSwitcherScreen.MTKIcon.getFromRange(stack.getOrCreateTag().getInt("Range"));
            }
        }else if (this.previousMode == null) {
            this.previousMode = MTKIcon.ONE;
        }
    }

    @Override
    public boolean keyPressed(int key1, int key2, int key3) {
        if (key1 == MTKKeyMapping.MTKSwitcherSelectKey.getKey().getValue()) {
            this.setFirstMousePos = false;
            this.currentlyMode = this.currentlyMode.getNext();
            // NBTに保存（クライアント側）
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                ItemStack stack = minecraft.player.getMainHandItem();
                if (stack.getItem() == ManaitaMTKItems.MANAITA_PICKAXE.get() || stack.getItem() == ManaitaMTKItems.MANAITA_PAXEL.get()) {
                    stack.getOrCreateTag().putInt("Range", this.currentlyMode.getModeRange());
                }
            }
            return true;
        } else {
            return super.keyPressed(key1, key2, key3);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseScrolled(double x, double y, double delta) {
        if (delta < 0) {
            this.currentlyMode = this.currentlyMode.getNext();
        } else if (delta > 0) {
            this.currentlyMode = this.currentlyMode.getPrevious();
        }
        // NBTに保存（クライアント側）
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            ItemStack stack = minecraft.player.getMainHandItem();
            if (stack.getItem() == ManaitaMTKItems.MANAITA_PICKAXE.get() || stack.getItem() == ManaitaMTKItems.MANAITA_PAXEL.get()) {
                stack.getOrCreateTag().putInt("Range", this.currentlyMode.getModeRange());
            }
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public enum MTKIcon {
        ONE(Component.literal("1x1"), 1, new ItemStack(Items.WOODEN_PICKAXE)),
        THREE(Component.literal("3x3"), 3, new ItemStack(Items.STONE_PICKAXE)),
        FIVE(Component.literal("5x5"), 5, new ItemStack(Items.IRON_PICKAXE)),
        SEVEN(Component.literal("7x7"), 7, new ItemStack(Items.GOLDEN_PICKAXE)),
        NINE(Component.literal("9x9"), 9, new ItemStack(Items.DIAMOND_PICKAXE));

        private static final MTKSwitcherScreen.MTKIcon[] VALUES = MTKIcon.values();
        final Component name;
        final int modeRange;
        final ItemStack renderStack;

        MTKIcon(Component name, int range, ItemStack stack) {
            this.name = name;
            this.modeRange = range;
            this.renderStack = stack;
        }

        public void drawIcon(GuiGraphics graphics, int x, int y) {
            graphics.renderItem(this.renderStack, x, y);
        }

        public Component getName() {
            return this.name;
        }

        public int getModeRange() {
            return this.modeRange;
        }

        public MTKIcon getNext() {
            MTKIcon icon;
            switch (this) {
                case ONE -> icon = MTKIcon.THREE;
                case THREE -> icon = MTKIcon.FIVE;
                case FIVE -> icon = MTKIcon.SEVEN;
                case SEVEN -> icon = MTKIcon.NINE;
                case NINE -> icon = MTKIcon.ONE;
                default -> icon = MTKIcon.ONE;
            }
            return icon;
        }

        private MTKIcon getPrevious() {
            MTKIcon icon;
            switch (this) {
                case ONE -> icon = MTKIcon.NINE;
                case THREE -> icon = MTKIcon.ONE;
                case FIVE -> icon = MTKIcon.THREE;
                case SEVEN -> icon = MTKIcon.FIVE;
                case NINE -> icon = MTKIcon.SEVEN;
                default -> icon = MTKIcon.ONE;
            }
            return icon;
        }

        public static MTKIcon getFromRange(int range) {
            MTKIcon icon;
            switch (range) {
                case 1 -> icon = MTKIcon.ONE;
                case 3 -> icon = MTKIcon.THREE;
                case 5 -> icon = MTKIcon.FIVE;
                case 7 -> icon = MTKIcon.SEVEN;
                case 9 -> icon = MTKIcon.NINE;
                default -> icon = MTKIcon.ONE;
            }
            return icon;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class MTKSlot extends AbstractWidget {
        final MTKIcon icon;
        private boolean isSelected;

        public MTKSlot(MTKIcon mtkIcon, int x, int y) {
            super(x, y, 26, 26, mtkIcon.getName());
            this.icon = mtkIcon;
        }

        public void renderWidget(GuiGraphics graphics, int x, int y, float f) {
            this.drawSlot(graphics);
            this.icon.drawIcon(graphics, this.getX() + 5, this.getY() + 5);
            if (this.isSelected) {
                this.drawSelection(graphics);
            }

        }

        public void updateWidgetNarration(NarrationElementOutput output) {
            this.defaultButtonNarrationText(output);
        }

        public boolean isHoveredOrFocused() {
            return super.isHoveredOrFocused() || this.isSelected;
        }

        public void setSelected(boolean selected) {
            this.isSelected = selected;
        }

        private void drawSlot(GuiGraphics p_281786_) {
            p_281786_.blit(MTKSwitcherScreen.MTK_SWITCHER_LOCATION, this.getX(), this.getY(), 0.0F, 75.0F, 26, 26, 128, 128);
        }

        private void drawSelection(GuiGraphics p_281820_) {
            p_281820_.blit(MTKSwitcherScreen.MTK_SWITCHER_LOCATION, this.getX(), this.getY(), 26.0F, 75.0F, 26, 26, 128, 128);
        }
    }
}
