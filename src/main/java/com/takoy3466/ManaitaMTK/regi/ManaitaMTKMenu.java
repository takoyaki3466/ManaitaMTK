package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ManaitaMTKMenu {

    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ManaitaMTK.MOD_ID);

    //public static final RegistryObject<MenuType<DoubleCraftingTableMenu>> MAGNIFICATION_CRAFTING_MENU =
    //      MENU_TYPE.register(DoubleCraftingTableEnum.WOOD.getBlockname() + "_crafting_table",
    //              () -> new MenuType<>((id, inv) -> new DoubleCraftingTableMenu(id, inv, ContainerLevelAccess.NULL, DoubleCraftingTableEnum.PLANE.getMag()),
    //                      FeatureFlagSet.of(FeatureFlags.VANILLA)));
}
