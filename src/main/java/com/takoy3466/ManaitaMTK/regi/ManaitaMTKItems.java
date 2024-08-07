package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.armor.armorManaitaCore;
import com.takoy3466.ManaitaMTK.item.ItemMTK;
import com.takoy3466.ManaitaMTK.item.tool.*;
import com.takoy3466.ManaitaMTK.main.ManaitaMTK;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

    //items
    public static final RegistryObject<Item> ITEM_MTK = ITEMS.register("item_mtk", ItemMTK::new);

    //tools
    public static final RegistryObject<PickaxeItem> MANAITA_PICKAXE = ITEMS.register("manaita_pickaxe", ToolManaitapickaxe::new);
    public static final RegistryObject<AxeItem> MANAITA_AXE = ITEMS.register("manaita_axe", ToolManaitaAxe::new);
    public static final RegistryObject<ShovelItem> MANAITA_SHOVEL = ITEMS.register("manaita_shovel", ToolManaitaShovel::new);
    public static final RegistryObject<SwordItem> MANAITA_SWORD = ITEMS.register("manaita_sword", ToolManaitaSword::new);
    public static final RegistryObject<TieredItem> MANAITA_PAXEL = ITEMS.register("manaita_paxel", ToolManaitaPaxel::new);

    //armors
    public static final RegistryObject<ArmorItem> HELMET_MANAITA = ITEMS.register("helmet_manaita", armorManaitaCore.HelmetManaita::new);
    public static final RegistryObject<ArmorItem> CHESTPLATE_MANAITA = ITEMS.register("chestplate_manaita", armorManaitaCore.ChestplateManaita::new);
    public static final RegistryObject<ArmorItem> LEGINS_MANAITA = ITEMS.register("legins_manaita", armorManaitaCore.LeggingsManaita::new);
    public static final RegistryObject<ArmorItem> BOOTS_MANAITA = ITEMS.register("boots_manaita", armorManaitaCore.BootsManaita::new);

}
