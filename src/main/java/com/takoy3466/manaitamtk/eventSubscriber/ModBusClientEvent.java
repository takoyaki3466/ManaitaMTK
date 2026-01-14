package com.takoy3466.manaitamtk.eventSubscriber;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMappings;
import com.takoy3466.manaitamtk.util.tooptip.ClientMTKBackPackTooltip;
import com.takoy3466.manaitamtk.util.tooptip.MTKBackPackTooltip;
import com.takoy3466.manaitamtk.init.EntitiesInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.init.MenusInit;
import com.takoy3466.manaitamtk.renderer.MTKArrowRenderer;
import com.takoy3466.manaitamtk.screen.MTKBackPackScreen;
import com.takoy3466.manaitamtk.screen.MTKChestScreen;
import com.takoy3466.manaitamtk.screen.MTKFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "manaitamtk", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBusClientEvent {

    @SubscribeEvent
    public static void ClientSetup(FMLClientSetupEvent event) {

        arrowMoving();
        menuRegister();

    }

    //矢のレンダー
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer((EntityType) EntitiesInit.MTK_ARROW.get(), MTKArrowRenderer::new);
    }

    //キーの登録
    @SubscribeEvent
    public static void keyRegister(RegisterKeyMappingsEvent event) {
        event.register(MTKKeyMappings.HelmetKey);
        event.register(MTKKeyMappings.FlySpeedKey);
        event.register(MTKKeyMappings.MagnificationKey);
        event.register(MTKKeyMappings.MTKSwitcherOpenKey);
        event.register(MTKKeyMappings.MTKSwitcherSelectKey);
        event.register(MTKKeyMappings.SwitchExterminationKey);
    }

    //弓の動き方
    private static void arrowMoving(){
        ItemProperties.register(ItemsInit.MANAITA_BOW.get(), new ResourceLocation("pull"), (itemstack, clientLevel, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemstack ? 0.0F : (float) (itemstack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(ItemsInit.MANAITA_BOW.get(), new ResourceLocation("pulling"), (itemstack, clientLevel, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemstack ? 1.0F : 0.0F);
    }

    private static void menuRegister() {
        MenuScreens.register(MenusInit.MTK_CHEST.get(), MTKChestScreen::new);

        MenuScreens.register(MenusInit.MTK_FURNACE_WOOD.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_STONE.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_IRON.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_GOLD.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_DIAMOND.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_MTK.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_GODMTK.get(), MTKFurnaceScreen::new);
        MenuScreens.register(MenusInit.MTK_FURNACE_BREAK.get(), MTKFurnaceScreen::new);

        MenuScreens.register(MenusInit.MTK_BACKPACK.get(), MTKBackPackScreen::new);
    }

    @SubscribeEvent
    public static void onRegisterClientTooltipComponentFactories(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(MTKBackPackTooltip.class, ClientMTKBackPackTooltip::new);
    }
}