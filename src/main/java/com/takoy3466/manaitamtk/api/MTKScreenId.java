package com.takoy3466.manaitamtk.api;

import com.takoy3466.manaitamtk.item.tool.MTKSwitcherScreen;
import net.minecraft.client.gui.screens.Screen;

public class MTKScreenId {
    public static final String MTK_SWITCHER = "mtk_switcher";

    public static String getScreenId(Screen screen) {
        if (screen instanceof MTKSwitcherScreen) return MTK_SWITCHER;

        return "";
    }
}
