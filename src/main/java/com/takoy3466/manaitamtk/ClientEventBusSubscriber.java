package com.takoy3466.manaitamtk;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.item.tooptip.ClientMTKBackPackTooltip;
import com.takoy3466.manaitamtk.item.tooptip.MTKBackPackTooltip;
import com.takoy3466.manaitamtk.init.ManaitaMTKEntities;
import com.takoy3466.manaitamtk.init.ManaitaMTKItems;
import com.takoy3466.manaitamtk.init.ManaitaMTKMenus;
import com.takoy3466.manaitamtk.renderer.MTKArrowRenderer;
import com.takoy3466.manaitamtk.screen.MTKBackPackScreen;
import com.takoy3466.manaitamtk.screen.MTKChestScreen;
import com.takoy3466.manaitamtk.screen.MTKFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "manaitamtk", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void ClientSetup(FMLClientSetupEvent event) {

        arrowMoving();
        menuRegister();

    }

    //矢のレンダー
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer((EntityType) ManaitaMTKEntities.MTK_ARROW.get(), MTKArrowRenderer::new);
    }

    //キーの登録
    @SubscribeEvent
    public static void keyRegister(RegisterKeyMappingsEvent event) {
        event.register(MTKKeyMapping.HelmetKey);
        event.register(MTKKeyMapping.FlySpeedKey);
        event.register(MTKKeyMapping.MagnificationKey);
        event.register(MTKKeyMapping.MTKSwitcherOpenKey);
        event.register(MTKKeyMapping.MTKSwitcherSelectKey);
        event.register(MTKKeyMapping.SwitchExtermination);
    }

    //弓の動き方
    private static void arrowMoving(){
        ItemProperties.register(ManaitaMTKItems.MANAITA_BOW.get(), new ResourceLocation("pull"), (itemstack, clientLevel, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemstack ? 0.0F : (float) (itemstack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(ManaitaMTKItems.MANAITA_BOW.get(), new ResourceLocation("pulling"), (itemstack, clientLevel, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemstack ? 1.0F : 0.0F);
    }

    private static void menuRegister() {
        MenuScreens.register(ManaitaMTKMenus.MTK_CHEST.get(), MTKChestScreen::new);

        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_WOOD.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_STONE.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_IRON.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_GOLD.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_DIAMOND.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_MTK.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_GODMTK.get(), MTKFurnaceScreen::new);
        MenuScreens.register(ManaitaMTKMenus.MTK_FURNACE_BREAK.get(), MTKFurnaceScreen::new);

        MenuScreens.register(ManaitaMTKMenus.MTK_BACKPACK.get(), MTKBackPackScreen::new);

    }

    @SubscribeEvent
    public static void onRegisterClientTooltipComponentFactories(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(MTKBackPackTooltip.class, ClientMTKBackPackTooltip::new);
    }
}