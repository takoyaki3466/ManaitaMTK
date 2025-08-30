package com.takoy3466.manaitamtk.renderer;

import com.takoy3466.manaitamtk.entity.EntityArrowMTK;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MTKArrowRenderer extends ArrowRenderer<EntityArrowMTK> {
    public MTKArrowRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityArrowMTK entity) {
        return new ResourceLocation(ManaitaMTK.MOD_ID, "textures/entity/mtk_arrow.png");
    }
}
