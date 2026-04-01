package com.takoy3466.manaitamtk.screen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import com.takoy3466.manaitamtk.core.overlayScreen.PortButton;
import com.takoy3466.manaitamtk.init.MTKOverlayIcons;
import com.takoy3466.manaitamtk.network.MTKNetwork;
import com.takoy3466.manaitamtk.network.PacketOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;

public class MTKOverlayScreen extends Screen {
    public final ResourceLocation OVERLAY = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/gui/container/overlay/mtk_overlay.png");

    private final int INDEX;

    private boolean visible = false;

    private final List<MTKOverlayIcon> iconList, defaultIconList;
    private final List<PortButton> portButtons = new ArrayList<>();
    private final List<MTKOverlayIcon> heldIconList = new ArrayList<>();

    private final BlockPos pos;
    private int portButtonsIndex = 0;

    private int leftPos, topPos;

    public MTKOverlayScreen(BlockPos pos, List<MTKOverlayIcon> iconList, List<MTKOverlayIcon> defaultIconList) {
        super(Component.empty());

        this.INDEX = defaultIconList.size();

        this.iconList = iconList;
        if (defaultIconList.size() >= 6) {
            this.defaultIconList = defaultIconList;
        }else {
            this.defaultIconList = iconList;
        }

        this.pos = pos;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = ((this.width - MTKOverlayIcons.IMAGE_WIDTH) / 2) + 30;
        this.topPos = ((this.height - MTKOverlayIcons.IMAGE_HEIGHT) / 2) + 30;

        this.isDragging = false;

        this.initWidgets();
    }

    public void open() {
        this.visible = true;
        Minecraft.getInstance().pushGuiLayer(this);
        load();
    }

    public void close() {
        this.visible = false;
        save();
        send();
        Minecraft.getInstance().popGuiLayer();
    }

    private void send() {
        MTKNetwork.sendToServer(new PacketOverlay(heldIconList, pos));
    }

    private void save() {
        heldIconList.clear();
        for (PortButton portButton : portButtons) {
            heldIconList.add(portButton.getCurrentIcon());
        }
    }

    private void load() {
        if (portButtons.size() == INDEX && heldIconList.size() == INDEX) {
            for (int i = 0; i < portButtons.size(); i++) {
                portButtons.get(i).setCurrentIcon(heldIconList.get(i));
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    private void initWidgets() {
        this.addRenderableWidget(createCloseButton(leftPos + 5, topPos + 5));

        portButtons.clear();

        this.portButtonsIndex = 0;
        portButtons.add(createPortButton(leftPos + 53, topPos + 10, defaultIconList.get(0)));// UP
        portButtons.add(createPortButton(leftPos + 11, topPos + 31, defaultIconList.get(1)));// BACK
        portButtons.add(createPortButton(leftPos + 32, topPos + 31, defaultIconList.get(2)));// LEFT
        portButtons.add(createPortButton(leftPos + 53, topPos + 31, defaultIconList.get(3)));// CENTER
        portButtons.add(createPortButton(leftPos + 74, topPos + 31, defaultIconList.get(4)));// RIGHT
        portButtons.add(createPortButton(leftPos + 53, topPos + 52, defaultIconList.get(5)));// DOWN

        portButtons.forEach(this::addRenderableWidget);
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y, float v) {
        renderBackground(graphics);

        renderHomeScreen(graphics, leftPos, topPos);

        super.render(graphics, x, y, v);
    }

    @Override
    public void onClose() {
        this.close();
    }

    public void renderHomeScreen(GuiGraphics graphics, int x, int y) {
        graphics.blit(OVERLAY, x, y, 0, 0,
                MTKOverlayIcons.GUI_WIDTH, MTKOverlayIcons.GUI_HEIGHT, MTKOverlayIcons.IMAGE_WIDTH, MTKOverlayIcons.IMAGE_HEIGHT);
    }

    public Button createCloseButton(int x, int y) {
        return new Button(Button.builder(Component.empty(), b -> this.close()).bounds(x, y, 9, 9)){
            @Override
            protected void renderWidget(GuiGraphics graphics, int x, int y, float v) {
                graphics.blit(OVERLAY, this.getX(), this.getY(),
                        5, 5, 9, 9, MTKOverlayIcons.IMAGE_WIDTH, MTKOverlayIcons.IMAGE_HEIGHT);
            }
        };
    }

    public PortButton createPortButton(int x, int y, MTKOverlayIcon defaultIcon) {
        PortButton button = new PortButton(x, y, this.portButtonsIndex, this.iconList, defaultIcon);
        this.portButtonsIndex++;
        return button;
    }

    private boolean isDragging;
    private int dragOffsetX;
    private int dragOffsetY;

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        if (x >=leftPos && x <= (leftPos + MTKOverlayIcons.IMAGE_WIDTH) && y > topPos && y <= (topPos + 12)) {
            if (button == 0) {
                isDragging = true;
                dragOffsetX = (int)(x - leftPos);
                dragOffsetY = (int)(y - topPos);
            }
        }
        return super.mouseClicked(x, y, button);
    }

    @Override
    public boolean mouseReleased(double x, double y, int button) {
        if (button == 0) {
            isDragging = false;
        }
        return super.mouseReleased(x, y, button);
    }

    @Override
    public boolean mouseDragged(double x, double y, int button, double dx, double dy) {
        if (isDragging && button == 0) {
            leftPos = (int) (x - dragOffsetX);
            topPos  = (int) (y - dragOffsetY);

            // 画面外制限
            leftPos = Mth.clamp(leftPos, 0, this.width - MTKOverlayIcons.IMAGE_WIDTH);
            topPos = Mth.clamp(topPos, 0, this.height - MTKOverlayIcons.IMAGE_HEIGHT);

            reSetWidget();
            return true;
        }

        return super.mouseDragged(x, y, button, dx, dy);
    }

    private void reSetWidget() {
        save();
        this.clearWidgets();
        this.initWidgets();
        load();
    }
}
