package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.entity.EntityArrowMTK;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitiesInit {

    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ManaitaMTK.MOD_ID);

    public static final String ARROW_TEXTURE = new ResourceLocation(ManaitaMTK.MOD_ID, "textures/entity/mtk_arrow").toString();

    public static RegistryObject<EntityType<?>> registerEntity(String name, Supplier<EntityType<?>> type) {
        return ENTITY.register(name, type);
    }

    public static final RegistryObject<EntityType<?>> MTK_ARROW = registerEntity("mtk_arrow",
            () -> EntityType.Builder.of(EntityArrowMTK::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(ARROW_TEXTURE)
    );
}
