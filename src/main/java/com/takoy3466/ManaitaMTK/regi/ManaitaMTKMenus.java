package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ManaitaMTKMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ManaitaMTK.MOD_ID);

    /*
    public static final RegistryObject<MenuType<FurnaceMTKMenu>> MTK_FURNACE_MENU =
            MENU_TYPE.register("mtk_furnace_menu", () ->
                            IForgeMenuType.create((id, inventory, data) -> {
                                BlockPos pos = data.readBlockPos();
                                Level level = inventory.player.level();
                                if (!(level.getBlockEntity(pos) instanceof FurnaceMTKEntity entity)) {
                                    throw new IllegalStateException("Invalid BlockEntity at " + pos);
                                }
                                return new FurnaceMTKMenu();
                            })
                    );

     */

}
