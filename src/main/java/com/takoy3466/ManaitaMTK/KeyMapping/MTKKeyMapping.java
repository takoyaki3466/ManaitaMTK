package com.takoy3466.ManaitaMTK.KeyMapping;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class MTKKeyMapping {

    public static KeyMapping HelmetKey = keyMappingCreate("helmetsetting",KeyEvent.VK_Z);
    public static KeyMapping FlySpeedKey = keyMappingCreate("flyspeedsetting",KeyEvent.VK_X);
    public static KeyMapping MagnificationKey = keyMappingCreate("magnification_key",KeyEvent.VK_G);

    private static KeyMapping keyMappingCreate(String name, int key){
        return new KeyMapping("key.manaitamtk."+ name, key, "key.manaitamtk.categories");
    }
}
