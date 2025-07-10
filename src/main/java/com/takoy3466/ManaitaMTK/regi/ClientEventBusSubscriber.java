package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.KeyMapping.MTKKeyMapping;
import com.takoy3466.ManaitaMTK.armor.FlyAndInvincible;
import com.takoy3466.ManaitaMTK.renderer.MTKArrowRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@Mod.EventBusSubscriber(modid = "manaitamtk", bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void ClientSetup(FMLClientSetupEvent event) {

        ArrowMoving();
    }

    //矢のレンダー
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
    }

    //弓の動き方
    private static void ArrowMoving(){
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
}