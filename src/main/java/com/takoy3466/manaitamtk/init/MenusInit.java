package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.menu.MTKBackpackMenu;
import com.takoy3466.manaitamtk.menu.MTKChestMenu;
import com.takoy3466.manaitamtk.menu.MTKFurnaceMenu;
import com.takoy3466.manaitamtk.menu.PortableFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenusInit {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ManaitaMTK.MOD_ID);

    public static final RegistryObject<MenuType<MTKChestMenu>> MTK_CHEST = MENU_TYPES.register("mtk_chest",
            () -> IForgeMenuType.create(MTKChestMenu::new));

    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuWood>> MTK_FURNACE_WOOD = MENU_TYPES.register("mtk_furnace_wood",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuWood(id, inventory, buf, MTKEnum.WOOD)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuStone>> MTK_FURNACE_STONE = MENU_TYPES.register("mtk_furnace_stone",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuStone(id, inventory, buf, MTKEnum.STONE)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuIron>> MTK_FURNACE_IRON = MENU_TYPES.register("mtk_furnace_iron",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuIron(id, inventory, buf, MTKEnum.IRON)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuGold>> MTK_FURNACE_GOLD = MENU_TYPES.register("mtk_furnace_gold",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuGold(id, inventory, buf, MTKEnum.GOLD)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuDiamond>> MTK_FURNACE_DIAMOND = MENU_TYPES.register("mtk_furnace_diamond",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuDiamond(id, inventory, buf, MTKEnum.DIAMOND)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuMTK>> MTK_FURNACE_MTK = MENU_TYPES.register("mtk_furnace_mtk",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuMTK(id, inventory, buf, MTKEnum.MTK)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuGODMTK>> MTK_FURNACE_GODMTK = MENU_TYPES.register("mtk_furnace_godmtk",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuGODMTK(id, inventory, buf, MTKEnum.GODMTK)));
    public static final RegistryObject<MenuType<MTKFurnaceMenu.FurnaceMenuBreak>> MTK_FURNACE_BREAK = MENU_TYPES.register("mtk_furnace_break",
            () -> IForgeMenuType.create((id, inventory, buf) -> new MTKFurnaceMenu.FurnaceMenuBreak(id, inventory, buf, MTKEnum.BREAK)));

    public static final RegistryObject<MenuType<MTKBackpackMenu>> MTK_BACKPACK = MENU_TYPES.register("mtk_back_pack",
            () -> IForgeMenuType.create((id, inventory, friendlyByteBuf) -> new MTKBackpackMenu(id, inventory)));

    public static final RegistryObject<MenuType<PortableFurnaceMenu>> PORTABLE_FURNACE = MENU_TYPES.register("portable_furnace",
            () -> IForgeMenuType.create(PortableFurnaceMenu::new));

}
