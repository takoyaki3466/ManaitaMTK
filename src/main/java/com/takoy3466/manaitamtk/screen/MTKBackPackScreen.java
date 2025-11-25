package com.takoy3466.manaitamtk.screen;

import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MTKBackPackScreen extends AbstractContainerScreen<MTKBackpackMenu> {
    private final ResourceLocation SCREEN = new ResourceLocation("textures/gui/container/generic_54.png");
    private final int containerRows;

    public MTKBackPackScreen(MTKBackpackMenu mtkBackpackMenu, Inventory inventory, Component component) {
        super(mtkBackpackMenu, inventory, component);
        this.containerRows = menu.getContainerRows();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float v, int i, int i1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(this.SCREEN, x, y, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        graphics.blit(this.SCREEN, x, y + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);

    }

    @Override
    public void render(GuiGraphics graphics, int i, int i1, float v) {
        this.renderBackground(graphics);
        super.render(graphics, i, i1, v);
        this.renderTooltip(graphics, i, i1);
    }
}
