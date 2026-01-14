package com.takoy3466.manaitamtk.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.takoy3466.manaitamtk.menu.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public abstract class ItemRenderMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private PoseStack pose;
    @Shadow public abstract int drawString(Font font, @Nullable String s, int x, int y, int i, boolean b);

    @Inject(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void onRenderItemDecoration(Font font, ItemStack stack, int x, int y, String string, CallbackInfo ci) {
        if (manaitaMTK$isMenu(minecraft.player.containerMenu)) {
            if (stack.getCount() > 999){
                String s = string == null ? "++" : string;
                this.pose.translate(0.0F, 0.0F, 200.0F);
                this.drawString(font, s, x + 19 - 2 - font.width(s), y + 6 + 3, 16777215, true);

                ci.cancel();
            }
        }
    }

    @Unique
    private boolean manaitaMTK$isMenu(AbstractContainerMenu menu) {
        boolean isMTKChest = menu instanceof MTKChestMenu;
        boolean isMTKBackpack = menu instanceof MTKBackpackMenu;
        boolean isPortableFurnace = menu instanceof PortableFurnaceMenu;
        boolean isMTKFurnace = menu instanceof MTKFurnaceMenu;
        boolean isMTKCraftTable = menu instanceof MTKCraftingTableMenu;
        return isMTKChest || isMTKBackpack || isPortableFurnace || isMTKFurnace || isMTKCraftTable;
    }
}
