package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
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

    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_WOOD = register(MTKEnum.WOOD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_STONE = register(MTKEnum.STONE);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_IRON = register(MTKEnum.IRON);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GOLD = register(MTKEnum.GOLD);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_DIAMOND = register(MTKEnum.DIAMOND);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_MTK = register(MTKEnum.MTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_GODMTK = register(MTKEnum.GODMTK);
    public static final RegistryObject<MenuType<MTKFurnaceMenu>> MTK_FURNACE_BREAK = register(MTKEnum.BREAK);

    public static final RegistryObject<MenuType<MTKBackpackMenu>> MTK_BACKPACK = register("mtk_back_pack", MTKBackpackMenu::new);


    public static RegistryObject<MenuType<MTKFurnaceMenu>> register(MTKEnum mtkEnum) {
        return MENU.register("mtk_furnace_" + mtkEnum.getComponent(),
                () -> IForgeMenuType.create((id, inv, buf) -> new MTKFurnaceMenu(id, inv, buf, mtkEnum)));
    }

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return MENU.register(name, () -> IForgeMenuType.create(factory));
    }
}
