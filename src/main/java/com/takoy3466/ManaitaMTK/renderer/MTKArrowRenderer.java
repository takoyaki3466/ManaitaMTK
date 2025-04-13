package com.takoy3466.ManaitaMTK.renderer;

import com.takoy3466.ManaitaMTK.entity.EntityArrowMTK;
import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
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
