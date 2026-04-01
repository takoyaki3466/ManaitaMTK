package com.takoy3466.manaitamtk.core.overlayScreen;

import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;

import java.util.List;

public class PortButton extends Button {
    private final ButtonManager buttonManager;

    private PortButton(ButtonManager buttonManager) {
        super(buttonManager.getBuilder());
        this.buttonManager = buttonManager;
    }

    public PortButton(int x, int y, int index, List<MTKOverlayIcon> iconList, MTKOverlayIcon defaultIcon) {
        this(new ButtonManager(x, y, index, iconList, defaultIcon));
    }

    public MTKOverlayIcon getCurrentIcon() {
        return buttonManager.getCurrentIcon();
    }

    public void setCurrentIcon(MTKOverlayIcon targetIcon) {
        buttonManager.setCurrentIcon(targetIcon);
    }

    private boolean isClicked;

    @Override
    protected void renderWidget(GuiGraphics graphics, int x, int y, float v) {
        buttonManager.renderButton(graphics, isHovered(), isClicked);
    }

    @Override
    public boolean mouseClicked(double v, double v1, int button) {
        isClicked = isValidClickButton(button);
        return super.mouseClicked(v, v1, button);
    }

    @Override
    public boolean mouseReleased(double v, double v1, int button) {
        if (button == 0) {
            isClicked = false;
        }
        return super.mouseReleased(v, v1, button);
    }
}
