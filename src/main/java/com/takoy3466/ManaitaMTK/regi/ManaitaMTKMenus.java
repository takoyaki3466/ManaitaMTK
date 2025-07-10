package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ManaitaMTKMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ManaitaMTK.MOD_ID);

    /*
    public static final RegistryObject<MenuType<AutoCraftingTableMenu>> AUTO_CRAFTING_MENU =
            MENU_TYPE.register("auto_crafting_menu", () ->
                            IForgeMenuType.create((id, inv, data) -> {
                                BlockPos pos = data.readBlockPos();
                                Level level = inv.player.level();
                                if (!(level.getBlockEntity(pos) instanceof AutoCraftingTableBlockEntity be)) {
                                    throw new IllegalStateException("Invalid BlockEntity at " + pos);
                                }
                                return new AutoCraftingTableMenu(id, inv, ContainerLevelAccess.create(level, pos), be);
                            })
                    );
     */
}
