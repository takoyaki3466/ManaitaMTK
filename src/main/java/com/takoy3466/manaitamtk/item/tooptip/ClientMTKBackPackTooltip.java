package com.takoy3466.manaitamtk.item.tooptip;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClientMTKBackPackTooltip implements ClientTooltipComponent {
    private final MTKBackPackTooltip backPackTooltip;

    public ClientMTKBackPackTooltip(MTKBackPackTooltip backPackTooltip) {
        this.backPackTooltip = backPackTooltip;
    }


    @Override
    public int getHeight() {
        return 18 * 6;
    }

    @Override
    public int getWidth(Font font) {
        return 18 * 9;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        PoseStack pose = graphics.pose();

        NonNullList<ItemStack> nonNullList = this.backPackTooltip.getNonNullList();

        boolean hasStack = false;
        for (int i = 0; i < nonNullList.size(); i++) {
            if (!nonNullList.get(i).isEmpty() || !nonNullList.isEmpty() || !(nonNullList.get(i) == ItemStack.EMPTY)) {
                hasStack = true;
                break;
            }
        }
        if (!hasStack) return;

        int colCount = 9; // 横列の数
        int row = 0;
        int col = 0;

        float textScale = 0.666f;

        for (ItemStack stack : nonNullList) {
            if (stack.isEmpty()) continue;
            int itemCount = stack.getCount();

            int slotX = x + col * 18;
            int slotY = y + row * 18;

            // アイテムの描画
            pose.pushPose();

            pose.translate(0, 0, 0);
            graphics.renderItem(stack, slotX, slotY);
            // スタック数の描画
            String text = setCountDisplay(itemCount);
            pose.translate(0, 0, 200);
            pose.scale(textScale, textScale, 1f);
            int textX = (int)((slotX + 16 - font.width(text) * textScale) / textScale);
            int textY = (int)((slotY + 14 - 5 * textScale) / textScale);
            graphics.drawString(font, text, textX, textY, Color.LIGHT_GRAY.getRGB(), true);
            pose.popPose();

            col++;
            if (col >= colCount) {
                col = 0;
                row++;
            }
        }
    }

    private String setCountDisplay(long count) {
        double tera = Math.pow(10, 12);
        double giga = Math.pow(10, 9);
        double mega = Math.pow(10, 6);
        double kiro = Math.pow(10, 3);

        if (count >= tera) return String.format("%.1fT", count / tera);
        else if (count >= giga) return String.format("%.1fG", count / giga);
        else if (count >= mega) return String.format("%.0fM", count / mega);
        else if (count >= kiro) return String.format("%.0fK", count / kiro);
        else return String.valueOf(count);
    }
}
