package com.takoy3466.manaitamtk.screen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.init.MTKOverlayIcons;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMultiFurnaceMenu;
import com.takoy3466.manaitamtk.screen.abstracts.AbstractPortScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MultiFurnaceScreen extends AbstractPortScreen<AbstractMultiFurnaceMenu> {
    private final ResourceLocation TEXTURE = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/gui/container/multi_furnace.png");

    public MultiFurnaceScreen(AbstractMultiFurnaceMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, menu.getPos(),
                MTKOverlayIcons.MULTI_FURNACE_INPUT,
                MTKOverlayIcons.MULTI_FURNACE_FUEL,
                MTKOverlayIcons.MULTI_FURNACE_BLAST_OUT,
                MTKOverlayIcons.MULTI_FURNACE_SMOKER_OUT,
                MTKOverlayIcons.MULTI_FURNACE_OUTPUT,
                MTKOverlayIcons.NONE
        );
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float v, int i, int i1) {
        int x = (this.width - 176) / 2;
        int y = (this.height - 166) / 2;
        graphics.blit(TEXTURE, x, y, 0, 0, 176, 166);
        int blastProgress, smokerProgress;
        if (menu.blast.isLit()) {
            blastProgress = menu.blast.getLitProgress();
            graphics.blit(TEXTURE,
                    x + 31, y + 36 + 12 - blastProgress,
                    176, 12 - blastProgress, 14, blastProgress + 1);
        }
        blastProgress = menu.blast.getBurnProgress();
        graphics.blit(TEXTURE, x + 79, y + 20, 176, 14, blastProgress + 1, 16);


        if (menu.smoker.isLit()) {
            smokerProgress = menu.smoker.getLitProgress();
            graphics.blit(TEXTURE,
                    x + 56, y + 36 + 12 - smokerProgress,
                    176, 12 - smokerProgress, 14, smokerProgress + 1);
        }
        smokerProgress = menu.smoker.getBurnProgress();
        graphics.blit(TEXTURE, x + 79, y + 48, 176, 14, smokerProgress + 1, 16);

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float tick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, tick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}
