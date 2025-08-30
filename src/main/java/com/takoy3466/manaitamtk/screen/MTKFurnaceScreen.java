package com.takoy3466.manaitamtk.screen;

import com.takoy3466.manaitamtk.menu.MTKFurnaceMenuBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MTKFurnaceScreen extends AbstractContainerScreen<MTKFurnaceMenuBase> implements RecipeUpdateListener {
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
    public final AbstractFurnaceRecipeBookComponent recipeBookComponent;
    private boolean widthTooNarrow;
    private final ResourceLocation texture;

    // 元の名前がさっぱりわからないのでi1,i2などが多いです、すいません
    public MTKFurnaceScreen(MTKFurnaceMenuBase menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.recipeBookComponent = new SmeltingRecipeBookComponent();
        this.texture = TEXTURE;
    }

    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 20, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (button) -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            button.setPosition(this.leftPos + 20, this.height / 2 - 49);
        }));
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    public void render(GuiGraphics graphics, int x, int y, float f) {
        this.renderBackground(graphics);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(graphics, f, x, y);
            this.recipeBookComponent.render(graphics, x, y, f);
        } else {
            this.recipeBookComponent.render(graphics, x, y, f);
            super.render(graphics, x, y, f);
            this.recipeBookComponent.renderGhostRecipe(graphics, this.leftPos, this.topPos, true, f);
        }

        this.renderTooltip(graphics, x, y);
        this.recipeBookComponent.renderTooltip(graphics, this.leftPos, this.topPos, x, y);
    }

    protected void renderBg(GuiGraphics graphics, float f, int x, int y) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        graphics.blit(this.texture, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        int progress;
        if (this.menu.isLit()) {
            progress = this.menu.getLitProgress();
            graphics.blit(this.texture, leftPos + 56, topPos + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1);
        }
        progress = this.menu.getBurnProgress();
        graphics.blit(this.texture, leftPos + 79, topPos + 34, 176, 14, progress + 1, 16);
    }

    public boolean mouseClicked(double d, double v, int i) {
        if (this.recipeBookComponent.mouseClicked(d, v, i)) {
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(d, v, i);
        }
    }

    protected void slotClicked(Slot slot, int i, int i1, ClickType clickType) {
        super.slotClicked(slot, i, i1, clickType);
        this.recipeBookComponent.slotClicked(slot);
    }

    public boolean keyPressed(int i, int i1, int i2) {
        return !this.recipeBookComponent.keyPressed(i, i1, i2) && super.keyPressed(i, i1, i2);
    }

    protected boolean hasClickedOutside(double v, double v1, int i, int i1, int i2) {
        boolean bool = v < (double)i || v1 < (double)i1 || v >= (double)(i + this.imageWidth) || v1 >= (double)(i1 + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(v, v1, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, i2) && bool;
    }

    public boolean charTyped(char c, int i) {
        return this.recipeBookComponent.charTyped(c, i) || super.charTyped(c, i);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}
