package com.takoy3466.manaitamtk.mixin;

import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public class ItemRenderMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void onRenderItemDecoration(Font font, ItemStack stack, int x, int y, String string, CallbackInfo ci) {
        if (minecraft.player.containerMenu instanceof MTKChestMenu || minecraft.player.containerMenu instanceof MTKBackpackMenu) {
            if (stack.getCount() > 999){
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V", at = @At("HEAD"), cancellable = true)
    private void onRenderItemDecoration(Font font, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (minecraft.player.containerMenu instanceof MTKChestMenu || minecraft.player.containerMenu instanceof MTKBackpackMenu) {
            if (stack.getCount() > 999){
                ci.cancel();
            }
        }
    }
}
