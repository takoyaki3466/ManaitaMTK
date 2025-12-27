package com.takoy3466.manaitamtk.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import com.takoy3466.manaitamtk.menu.PortableFurnaceMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public abstract class ItemRenderMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Final private PoseStack pose;
    @Shadow public abstract int drawString(Font p_283343_, @Nullable String p_281896_, int p_283569_, int p_283418_, int p_281560_, boolean p_282130_);

    @Inject(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void onRenderItemDecoration(Font font, ItemStack stack, int x, int y, String string, CallbackInfo ci) {
        if (minecraft.player.containerMenu instanceof MTKChestMenu || minecraft.player.containerMenu instanceof MTKBackpackMenu || minecraft.player.containerMenu instanceof PortableFurnaceMenu) {
            if (stack.getCount() > 999){
                String s = string == null ? "++" : string;
                this.pose.translate(0.0F, 0.0F, 200.0F);
                this.drawString(font, s, x + 19 - 2 - font.width(s), y + 6 + 3, 16777215, true);

                ci.cancel();
            }
        }
    }
}
