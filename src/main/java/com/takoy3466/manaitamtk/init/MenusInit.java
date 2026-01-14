package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.registry.register.MenuRegister;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import com.takoy3466.manaitamtk.menu.MTKFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class MenusInit {
    public static final MenuRegister MENUS = MenuRegister.create(ManaitaMTK.MOD_ID);

    public static final RegistryObject<MenuType<MTKChestMenu>> MTK_CHEST = MENUS.register("mtk_chest", MTKChestMenu::new);

    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_WOOD = furnaceRegister(MTKTiers.WOOD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_STONE = furnaceRegister(MTKTiers.STONE);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_IRON = furnaceRegister(MTKTiers.IRON);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GOLD = furnaceRegister(MTKTiers.GOLD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_DIAMOND = furnaceRegister(MTKTiers.DIAMOND);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_MTK = furnaceRegister(MTKTiers.MTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GODMTK = furnaceRegister(MTKTiers.GODMTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_BREAK = furnaceRegister(MTKTiers.BREAK);

    public static final RegistryObject<MenuType<MTKBackpackMenu>> MTK_BACKPACK = MENUS.register("mtk_back_pack", MTKBackpackMenu::new);


    public static RegistryObject<MenuType<MTKFurnaceMenu>> furnaceRegister(MTKTier mtkTier) {
        return MENUS.register("mtk_furnace_" + mtkTier.getName(), (id, inv, buf) -> new MTKFurnaceMenu(id, inv, buf, mtkTier));
    }
}
