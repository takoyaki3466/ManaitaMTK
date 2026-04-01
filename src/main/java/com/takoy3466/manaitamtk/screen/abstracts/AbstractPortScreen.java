package com.takoy3466.manaitamtk.screen.abstracts;

import com.takoy3466.manaitamtk.core.interfaces.IOverlay;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import com.takoy3466.manaitamtk.init.MTKOverlayIcons;
import com.takoy3466.manaitamtk.screen.MTKOverlayScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;

public abstract class AbstractPortScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private final MTKOverlayScreen screen;

    public AbstractPortScreen(T parentMenu, Inventory inventory, Component component, BlockPos pos, List<MTKOverlayIcon> icons) {
        super(parentMenu, inventory, component);
        if (parentMenu instanceof IOverlay overlayMenu) {
            this.screen = new MTKOverlayScreen(pos, icons, overlayMenu.getIconList());
        }else {
            throw new IllegalStateException(parentMenu.getClass().getCanonicalName() + "はIOverlayをimplmentしてないよ！");
        }
    }

    public AbstractPortScreen(T parentMenu, Inventory inventory, Component component, BlockPos pos, MTKOverlayIcon... icons) {
        this(parentMenu, inventory, component, pos, List.of(icons));
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(createOpenButton(leftPos + 173, topPos + 10));
    }

    public Button createOpenButton(int x, int y) {
        return new Button(Button.builder(Component.empty(), b -> screen.open()).bounds(x, y, 19, 28)){
            @Override
            protected void renderWidget(GuiGraphics graphics, int x, int y, float v) {
                graphics.pose().pushPose();
                boolean isVisible = screen.isVisible();
                if (isVisible) {
                    graphics.pose().translate(0, 0, 200);
                }
                graphics.blit(screen.OVERLAY, this.getX(), this.getY(), 113, isVisible ? 29 : 0, 18, 28, MTKOverlayIcons.IMAGE_WIDTH, MTKOverlayIcons.IMAGE_HEIGHT);

                graphics.pose().popPose();
            }
        };
    }

    @Override
    protected boolean isHovering(int i, int i1, int i2, int i3, double v, double v1) {
        if (screen.isVisible()) {
            return false;
        }
        return super.isHovering(i, i1, i2, i3, v, v1);
    }
}
