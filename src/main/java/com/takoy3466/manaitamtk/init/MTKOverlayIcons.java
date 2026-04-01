package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;

public class MTKOverlayIcons {

    public static final int IMAGE_WIDTH = 150;
    public static final int IMAGE_HEIGHT = 150;
    public static final int GUI_WIDTH = 104;
    public static final int GUI_HEIGHT = 81;
    public static final int BUTTON_SIZE = 20; // 20 x 20

    public static MTKOverlayIcon NONE = new MTKOverlayIcon(ManaitaMTK.MOD_ID, "textures/gui/container/overlay/none.png", new int[]{});

    public static MTKOverlayIcon MULTI_FURNACE_OUTPUT = new MTKOverlayIcon(ManaitaMTK.MOD_ID, "textures/gui/container/overlay/out.png", new int[]{2, 5});
    public static MTKOverlayIcon MULTI_FURNACE_INPUT = new MTKOverlayIcon(ManaitaMTK.MOD_ID, "textures/gui/container/overlay/in.png", new int[]{0, 3});
    public static MTKOverlayIcon MULTI_FURNACE_BLAST_OUT = new MTKOverlayIcon("textures/item/iron_ingot.png", new int[]{2});
    public static MTKOverlayIcon MULTI_FURNACE_SMOKER_OUT = new MTKOverlayIcon("textures/item/cooked_beef.png", new int[]{5});
    public static MTKOverlayIcon MULTI_FURNACE_FUEL = new MTKOverlayIcon("textures/item/coal.png", new int[]{1, 4});

}
