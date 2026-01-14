package com.takoy3466.manaitamtk.KeyMapping;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.keyMapping.KeyMappingRegister;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class MTKKeyMappings {
    public static final KeyMappingRegister KEY = KeyMappingRegister.create(ManaitaMTK.MOD_ID);

    public static KeyMapping HelmetKey = KEY.register("helmetsetting",KeyEvent.VK_V);
    public static KeyMapping FlySpeedKey = KEY.register("flyspeedsetting",KeyEvent.VK_Z);
    public static KeyMapping MagnificationKey = KEY.register("magnification_key",KeyEvent.VK_G);
    public static KeyMapping MTKSwitcherOpenKey = KEY.register("mtk_switcher_open_key", GLFW.GLFW_KEY_X);
    public static KeyMapping MTKSwitcherSelectKey = KEY.register("mtk_switcher_select_key", KeyEvent.VK_C);
    public static KeyMapping SwitchExterminationKey = KEY.register("mtk_switch_extermination", KeyEvent.VK_M);
}
