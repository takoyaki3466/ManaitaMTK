package com.takoy3466.manaitamtk.screen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.helper.MTKScreenHelper;
import com.takoy3466.manaitamtk.menu.MultiFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MultiFurnaceScreen extends AbstractContainerScreen<MultiFurnaceMenu> {
    private final ResourceLocation SCREEN = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/gui/container/multi_furnace.png");
    public MultiFurnaceScreen(MultiFurnaceMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float v, int i, int i1) {
        MTKScreenHelper.centerBlit(graphics, this, SCREEN, 257, 200, 0, 0, 218, 179);
    }
}
