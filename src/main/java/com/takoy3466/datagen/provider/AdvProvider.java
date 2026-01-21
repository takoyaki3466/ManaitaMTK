package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKAdvancementProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class AdvProvider extends MTKAdvancementProvider {
    private final ForPeopleWhoFindWorkTedious TTF = ForPeopleWhoFindWorkTedious.create(true, true, false);

    private final String INV_CHANGE = "inv_change";


    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
        Advancement MTK_START = add(getRoot("manaita_mtk_start"), consumer,
                null,
                AdvDisplay.create(ItemsInit.ITEM_MTK.get(), "start", new ResourceLocation("textures/block/stone.png"), FrameType.TASK, TTF),
                null,
                AdvCriterion.create("tick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE.asItem()))
        );

        Advancement ITEM_MTK = add(getRoot("item_mtk"),
                consumer, MTK_START,
                AdvDisplay.create(ItemsInit.ITEM_MTK.get(), "make_mtk", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create(INV_CHANGE, BlocksInit.BLOCK_MANAITA.getItem())
        );
        Advancement WOOD_MTK_FURNACE = add(getRoot("wood_mtk_furnace"),consumer, ITEM_MTK,
                AdvDisplay.create(BlocksInit.WOOD_MTK_FURNACE.getItem(), "make_wood_mtk_furnace", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create(INV_CHANGE, BlocksInit.WOOD_MTK_FURNACE.getItem())
        );

    }

    private String getRoot(String rootName) {
        return ManaitaMTK.MOD_ID + ":" + rootName;
    }
}
