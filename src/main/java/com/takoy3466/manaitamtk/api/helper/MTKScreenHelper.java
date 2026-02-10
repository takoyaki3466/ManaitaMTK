package com.takoy3466.manaitamtk.api.helper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class MTKScreenHelper {

    public static <T extends Screen> void centerBlit(GuiGraphics graphics, T screen, ResourceLocation texture,
                                                     int textureSizeX, int textureSizeY,
                                                     int u, int v,
                                                     int width, int height) {
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        graphics.blit(texture, x, y, u, v, width, height, textureSizeX, textureSizeY);
    }
}
