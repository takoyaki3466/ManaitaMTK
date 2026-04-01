package com.takoy3466.manaitamtk.core.overlayScreen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import com.takoy3466.manaitamtk.init.MTKOverlayIcons;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ButtonManager {
    private final List<MTKOverlayIcon> iconList;

    private IconIndex previousIcon;
    private IconIndex currentIcon;
    private final int x;
    private final int y;
    private final int index;
    private final Button.Builder builder;

    public ButtonManager(int x, int y, int index, List<MTKOverlayIcon> iconList, MTKOverlayIcon defaultIcon) {
        this.iconList = iconList;
        this.currentIcon = IconIndex.set(iconList, defaultIcon);
        this.x = x;
        this.y = y;
        this.index = index;

        this.builder = Button.builder(Component.empty(), b -> next()).bounds(x, y, 20, 20);
    }

    public Button.Builder getBuilder() {
        return builder;
    }

    public MTKOverlayIcon getCurrentIcon() {
        return currentIcon.icon();
    }

    public MTKOverlayIcon getPreviousIcon() {
        return previousIcon.icon();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public void setCurrentIcon(MTKOverlayIcon targetIcon) {
        currentIcon = IconIndex.set(iconList, targetIcon);
    }

    public void setCurrentIcon(int index) {
        currentIcon = IconIndex.of(iconList, index);
    }

    public int getCurrentIndex() {
        return currentIcon.index();
    }

    public final ResourceLocation OVERLAY = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/gui/container/overlay/mtk_overlay.png");

    public void renderButton(GuiGraphics graphics, boolean isHovered, boolean isClicked) {
        if (isHovered) {
            graphics.blit(OVERLAY, getX(), getY(), 118, isClicked ? 81 : 103, MTKOverlayIcons.BUTTON_SIZE, MTKOverlayIcons.BUTTON_SIZE, MTKOverlayIcons.IMAGE_WIDTH, MTKOverlayIcons.IMAGE_HEIGHT);
        }else {
            graphics.blit(OVERLAY, getX(), getY(), 118, 59, MTKOverlayIcons.BUTTON_SIZE, MTKOverlayIcons.BUTTON_SIZE, MTKOverlayIcons.IMAGE_WIDTH, MTKOverlayIcons.IMAGE_HEIGHT);
        }
        getCurrentIcon().blit(graphics, getX()+ 2, getY() + 2);
    }

    public void next() {
        previousIcon = currentIcon;
        int i = previousIcon.index() + 1;
        if (i < iconList.size()) {
            setCurrentIcon(i);
        } else {
            setCurrentIcon(0);
        }
    }

    protected record IconIndex(MTKOverlayIcon icon, int index) {
        public static IconIndex of(List<MTKOverlayIcon> icons, int index) {
            if (index > icons.size()) {
                return new IconIndex(icons.get(0), 0);
            }
            return new IconIndex(icons.get(index), index);
        }

        public static IconIndex set(List<MTKOverlayIcon> icons, MTKOverlayIcon defaultIcon) {
            boolean isContain = false;
            int defaultIndex = 0;
            for (int i = 0; i < icons.size(); i++) {
                if (icons.get(i).equals(defaultIcon)) {
                    isContain = true;
                    defaultIndex = i;
                    break;
                }
            }
            if (isContain) {
                return IconIndex.of(icons, defaultIndex);
            }else {
                return IconIndex.of(icons, 0);
            }
        }
    }
}
