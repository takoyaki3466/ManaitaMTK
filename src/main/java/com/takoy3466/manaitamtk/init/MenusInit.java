package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import com.takoy3466.manaitamtk.menu.MTKFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenusInit {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ManaitaMTK.MOD_ID);

    public static final RegistryObject<MenuType<MTKChestMenu>> MTK_CHEST = register("mtk_chest", MTKChestMenu::new);

    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_WOOD = register(MTKTiers.WOOD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_STONE = register(MTKTiers.STONE);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_IRON = register(MTKTiers.IRON);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GOLD = register(MTKTiers.GOLD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_DIAMOND = register(MTKTiers.DIAMOND);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_MTK = register(MTKTiers.MTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GODMTK = register(MTKTiers.GODMTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_BREAK = register(MTKTiers.BREAK);

    public static final RegistryObject<MenuType<MTKBackpackMenu>> MTK_BACKPACK = register("mtk_back_pack", MTKBackpackMenu::new);


    public static RegistryObject<MenuType<MTKFurnaceMenu>> register(MTKTier mtkTier) {
        return MENU.register("mtk_furnace_" + mtkTier.getName(),
                () -> IForgeMenuType.create((id, inv, buf) -> new MTKFurnaceMenu(id, inv, buf, mtkTier)));
    }

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return MENU.register(name, () -> IForgeMenuType.create(factory));
    }
}
