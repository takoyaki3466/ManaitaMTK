package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.DoubleCraftingTableEnum;
import com.takoy3466.ManaitaMTK.armor.*;
import com.takoy3466.ManaitaMTK.item.*;
import com.takoy3466.ManaitaMTK.item.tool.*;
import com.takoy3466.ManaitaMTK.ManaitaMTK;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

    //items
    public static final RegistryObject<Item> ITEM_MTK = ITEMS.register("item_mtk", ItemMTK::new);
    public static final RegistryObject<Item> ZIKOKENZIYOKU = ITEMS.register("zikokenziyoku", ZIKOKENZIYOKU::new);
    public static final RegistryObject<Item> CRUSHED_MTK = ITEMS.register("crushed_mtk", CrushedMTK::new);

    public static final RegistryObject<Item> PORTABLE_WOOD_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.WOOD);
    public static final RegistryObject<Item> PORTABLE_STONE_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.STONE);
    public static final RegistryObject<Item> PORTABLE_IRON_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.IRON);
    public static final RegistryObject<Item> PORTABLE_GOLD_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.GOLD);
    public static final RegistryObject<Item> PORTABLE_DIAMOND_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.DIAMOND);
    public static final RegistryObject<Item> PORTABLE_MTK_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.MTK);
    public static final RegistryObject<Item> PORTABLE_GODMTK_CRAFTING_TABLE = PortableDCTRegister(DoubleCraftingTableEnum.GODMTK);

    public static final RegistryObject<Item> CHANGEABLE_PORTABLE_DCT = ITEMS.register("changeable_portable_dct", ChangeableMagnificationPortableDCT::new);

    public static final RegistryObject<Item> WOOD_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.WOOD);
    public static final RegistryObject<Item> STONE_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.STONE);
    public static final RegistryObject<Item> IRON_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.IRON);
    public static final RegistryObject<Item> GOLD_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.GOLD);
    public static final RegistryObject<Item> DIAMOND_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.DIAMOND);
    public static final RegistryObject<Item> MTK_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.MTK);
    public static final RegistryObject<Item> GODMTK_DOUBLE_BLOCK_MTK = DoubleBlockMTKRegister(DoubleCraftingTableEnum.GODMTK);

    //tools
    public static final RegistryObject<PickaxeItem> MANAITA_PICKAXE = ITEMS.register("manaita_pickaxe", ToolManaitapickaxe::new);
    public static final RegistryObject<AxeItem> MANAITA_AXE = ITEMS.register("manaita_axe", ToolManaitaAxe::new);
    public static final RegistryObject<ShovelItem> MANAITA_SHOVEL = ITEMS.register("manaita_shovel", ToolManaitaShovel::new);
    public static final RegistryObject<SwordItem> MANAITA_SWORD = ITEMS.register("manaita_sword", ToolManaitaSword::new);
    public static final RegistryObject<TieredItem> MANAITA_PAXEL = ITEMS.register("manaita_paxel", ToolManaitaPaxel::new);
    public static final RegistryObject<BowItem> MANAITA_BOW = ITEMS.register("manaita_bow", BowMTK::new);

    //armors
    public static final RegistryObject<ArmorItem> HELMET_MANAITA = ITEMS.register("helmet_manaita", HelmetManaita::new);
    public static final RegistryObject<ArmorItem> CHESTPLATE_MANAITA = ITEMS.register("chestplate_manaita", ChestplateManaita::new);
    public static final RegistryObject<ArmorItem> LEGINS_MANAITA = ITEMS.register("legins_manaita", LeggingsManaita::new);
    public static final RegistryObject<ArmorItem> BOOTS_MANAITA = ITEMS.register("boots_manaita", BootsManaita::new);




    //型の作成
    public static RegistryObject<Item> PortableDCTRegister(DoubleCraftingTableEnum tableEnum){
        return ITEMS.register("portable_" + tableEnum.getBlockname() + "_crafting_table",
                () -> new PortableDoubleCraftingTable(new Item.Properties(), tableEnum.getMag(), tableEnum.getComponentName())
        );
    }

    public static RegistryObject<Item> DoubleBlockMTKRegister(DoubleCraftingTableEnum tableEnum){
        return ITEMS.register(tableEnum.getBlockname() + "_double_block_mtk",
                () -> new DoubleBlockMTK(new Item.Properties(), tableEnum.getMag())
        );
    }
}
