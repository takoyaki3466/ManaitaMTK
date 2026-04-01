package com.takoy3466.manaitamtk.core;

import com.mojang.serialization.Codec;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * overlayをより簡単に管理するため
 */
public class MTKOverlayIcon {
    private final ResourceLocation display;
    private final int[] faceInt;

    /**
     * @param faceInt 搬出する面を設定します、新しく1から作る場合、どの数字がどの面かはBlockEntity側で設定してください。
     */
    public MTKOverlayIcon(ResourceLocation resourceLocation, int[] faceInt) {
        this.display = resourceLocation;
        this.faceInt = faceInt;
    }

    public MTKOverlayIcon(String modId, String path, int[] faceInt) {
        this(new ResourceLocation(modId, path), faceInt);
    }

    /**
     * バニラのpathからアイコンを作ります
     * @param path バニラのpath (textures/item/coal.pngなど)
     */
    public MTKOverlayIcon(String path, int[] faceInt) {
        this(new ResourceLocation(path), faceInt);
    }

    /**
     * CODECから生成するときに用います。
     */
    protected MTKOverlayIcon(ResourceLocation resourceLocation, IntStream intStream) {
        this(resourceLocation, intStream.toArray());
    }

    public static final Codec<MTKOverlayIcon> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("display").forGetter(MTKOverlayIcon::getDisplay),
            Codec.INT_STREAM.fieldOf("port_int").forGetter(icon -> Arrays.stream(icon.getFace()))
            ).apply(instance, MTKOverlayIcon::new)
    );

    public void blit(GuiGraphics graphics, int x, int y) {
        graphics.blit(display, x, y, 0, 0, 16, 16, 16, 16);
    }

    public ResourceLocation getDisplay() {
        return display;
    }

    public int[] getFace() {
        return faceInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MTKOverlayIcon icon)) return false;
        return Objects.equals(display, icon.display);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(display);
    }
}
