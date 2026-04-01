package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.core.registry.register.MenuRegister;
import com.takoy3466.manaitamtk.menu.*;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMTKFurnaceMenu;
import com.takoy3466.manaitamtk.menu.abstracts.AbstractMultiFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class MenusInit {
    public static final MenuRegister MENUS = MenuRegister.create(ManaitaMTK.MOD_ID);

    public static final RegistryObject<MenuType<MTKChestMenu>> MTK_CHEST = MENUS.register("mtk_chest", MTKChestMenu::new);

    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_WOOD = furnaceRegister(MTKTiers.WOOD);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_STONE = furnaceRegister(MTKTiers.STONE);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_IRON = furnaceRegister(MTKTiers.IRON);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_GOLD = furnaceRegister(MTKTiers.GOLD);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_DIAMOND = furnaceRegister(MTKTiers.DIAMOND);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_MTK = furnaceRegister(MTKTiers.MTK);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_GODMTK = furnaceRegister(MTKTiers.GODMTK);
    public static final RegistryObject<MenuType<AbstractMTKFurnaceMenu>> MTK_FURNACE_BREAK = furnaceRegister(MTKTiers.BREAK);

    public static final RegistryObject<MenuType<MTKBackpackMenu>> MTK_BACKPACK = MENUS.register("mtk_back_pack", MTKBackpackMenu::new);

    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> WOOD_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Wood::new, MTKTiers.WOOD);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> STONE_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Stone::new, MTKTiers.STONE);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> IRON_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Iron::new, MTKTiers.IRON);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> GOLD_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Gold::new, MTKTiers.GOLD);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> DIAMOND_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Diamond::new, MTKTiers.DIAMOND);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> MTK_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.MTK::new, MTKTiers.MTK);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> GODMTK_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.GodMTK::new, MTKTiers.GODMTK);
    public static final RegistryObject<MenuType<AbstractMultiFurnaceMenu>> BREAK_MULTI_FURNACE = multiFurnace(MultiFurnaceMenu.Break::new, MTKTiers.BREAK);

    public static RegistryObject<MenuType<AbstractMTKFurnaceMenu>> furnaceRegister(MTKTier mtkTier) {
        return MENUS.register("mtk_furnace_" + mtkTier.getName(), (id, inv, buf) -> new AbstractMTKFurnaceMenu(id, inv, buf, mtkTier));
    }

    public static <T extends AbstractMultiFurnaceMenu> RegistryObject<MenuType<T>> multiFurnace(IContainerFactory<T> factory, MTKTier mtkTier) {
        return MENUS.register(mtkTier.getName() + "_multi_furnace", factory);
    }
}
