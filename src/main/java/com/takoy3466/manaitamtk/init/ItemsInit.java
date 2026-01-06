package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.item.*;
import com.takoy3466.manaitamtk.item.armor.BootsManaita;
import com.takoy3466.manaitamtk.item.armor.ChestplateManaita;
import com.takoy3466.manaitamtk.item.armor.HelmetManaita;
import com.takoy3466.manaitamtk.item.armor.LeggingsManaita;
import com.takoy3466.manaitamtk.item.tool.*;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ManaitaMTK.MOD_ID);

    //items
    public static final RegistryObject<Item> ITEM_MTK = ITEMS.register("item_mtk", ItemMTK::new);
    public static final RegistryObject<Item> ZIKOKENZIYOKU = ITEMS.register("zikokenziyoku", ZIKOKENZIYOKU::new);
    public static final RegistryObject<Item> CRUSHED_MTK = ITEMS.register("crushed_mtk", CrushedMTK::new);
    public static final RegistryObject<Item> DEBUG_MTK = ITEMS.register("debug_mtk", DebugMTK::new);

    public static final RegistryObject<Item> PORTABLE_WOOD_CRAFTING_TABLE = portableMCTRegister(MTKTiers.WOOD);
    public static final RegistryObject<Item> PORTABLE_STONE_CRAFTING_TABLE = portableMCTRegister(MTKTiers.STONE);
    public static final RegistryObject<Item> PORTABLE_IRON_CRAFTING_TABLE = portableMCTRegister(MTKTiers.IRON);
    public static final RegistryObject<Item> PORTABLE_GOLD_CRAFTING_TABLE = portableMCTRegister(MTKTiers.GOLD);
    public static final RegistryObject<Item> PORTABLE_DIAMOND_CRAFTING_TABLE = portableMCTRegister(MTKTiers.DIAMOND);
    public static final RegistryObject<Item> PORTABLE_MTK_CRAFTING_TABLE = portableMCTRegister(MTKTiers.MTK);
    public static final RegistryObject<Item> PORTABLE_GODMTK_CRAFTING_TABLE = portableMCTRegister(MTKTiers.GODMTK);
    public static final RegistryObject<Item> PORTABLE_BREAK_CRAFTING_TABLE = portableMCTRegister(MTKTiers.BREAK);

    public static final RegistryObject<Item> CHANGEABLE_PORTABLE_DCT = ITEMS.register("changeable_portable_dct", ChangeableMagnificationPortableDCT::new);

    public static final RegistryObject<Item> WOOD_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.WOOD);
    public static final RegistryObject<Item> STONE_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.STONE);
    public static final RegistryObject<Item> IRON_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.IRON);
    public static final RegistryObject<Item> GOLD_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.GOLD);
    public static final RegistryObject<Item> DIAMOND_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.DIAMOND);
    public static final RegistryObject<Item> MTK_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.MTK);
    public static final RegistryObject<Item> GODMTK_DOUBLE_BLOCK_MTK = doubleBlockMTKRegister(MTKTiers.GODMTK);

    //tools
    public static final RegistryObject<PickaxeItem> MANAITA_PICKAXE = ITEMS.register("manaita_pickaxe", ToolManaitaPickaxe::new);
    public static final RegistryObject<AxeItem> MANAITA_AXE = ITEMS.register("manaita_axe", ToolManaitaAxe::new);
    public static final RegistryObject<ShovelItem> MANAITA_SHOVEL = ITEMS.register("manaita_shovel", ToolManaitaShovel::new);
    public static final RegistryObject<SwordItem> MANAITA_SWORD = ITEMS.register("manaita_sword", ToolManaitaSword::new);
    public static final RegistryObject<TieredItem> MANAITA_PAXEL = ITEMS.register("manaita_paxel", ToolManaitaPaxel::new);
    public static final RegistryObject<BowItem> MANAITA_BOW = ITEMS.register("manaita_bow", BowMTK::new);
    public static final RegistryObject<HoeItem> MANAITA_HOE = ITEMS.register("manaita_hoe", ToolManaitaHoe::new);

    //armors
    public static final RegistryObject<ArmorItem> HELMET_MANAITA = ITEMS.register("helmet_manaita", HelmetManaita::new);
    public static final RegistryObject<ArmorItem> CHESTPLATE_MANAITA = ITEMS.register("chestplate_manaita", ChestplateManaita::new);
    public static final RegistryObject<ArmorItem> LEGINS_MANAITA = ITEMS.register("legins_manaita", LeggingsManaita::new);
    public static final RegistryObject<ArmorItem> BOOTS_MANAITA = ITEMS.register("boots_manaita", BootsManaita::new);

    public static final RegistryObject<Item> MTK_BACKPACK = ITEMS.register("mtk_backpack", MTKBackPack::new);

    public static final RegistryObject<Item> PORTABLE_WOOD_FURNACE = portableFurnaceRegister(MTKTiers.WOOD);
    public static final RegistryObject<Item> PORTABLE_STONE_FURNACE = portableFurnaceRegister(MTKTiers.STONE);
    public static final RegistryObject<Item> PORTABLE_IRON_FURNACE = portableFurnaceRegister(MTKTiers.IRON);
    public static final RegistryObject<Item> PORTABLE_GOLD_FURNACE = portableFurnaceRegister(MTKTiers.GOLD);
    public static final RegistryObject<Item> PORTABLE_DIAMOND_FURNACE = portableFurnaceRegister(MTKTiers.DIAMOND);
    public static final RegistryObject<Item> PORTABLE_MTK_FURNACE = portableFurnaceRegister(MTKTiers.MTK);
    public static final RegistryObject<Item> PORTABLE_GODMTK_FURNACE = portableFurnaceRegister(MTKTiers.GODMTK);
    public static final RegistryObject<Item> PORTABLE_BREAK_FURNACE = portableFurnaceRegister(MTKTiers.BREAK);



    //型の作成
    private static RegistryObject<Item> portableMCTRegister(MTKTier mtkTier){
        return ITEMS.register("portable_" + mtkTier.getName() + "_crafting_table",
                () -> new PortableDoubleCraftingTable(new Item.Properties(), mtkTier)
        );
    }

    private static RegistryObject<Item> doubleBlockMTKRegister(MTKTier mtkTier){
        return ITEMS.register(mtkTier.getName() + "_double_block_mtk",
                () -> new DoubleBlockMTK(new Item.Properties(), mtkTier)
        );
    }

    private static RegistryObject<Item> portableFurnaceRegister(MTKTier mtkTier) {
        return ITEMS.register("portable_" + mtkTier.getName() + "_furnace", () -> new PortableFurnace(mtkTier));
    }
}
