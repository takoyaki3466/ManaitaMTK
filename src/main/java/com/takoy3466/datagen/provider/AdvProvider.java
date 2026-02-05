package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKAdvancementProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.criterionTrigger.MTKOpenTrigger;
import com.takoy3466.manaitamtk.api.MTKScreenId;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class AdvProvider extends MTKAdvancementProvider {
    private final ForPeopleWhoFindWorkTedious TTF = ForPeopleWhoFindWorkTedious.create(true, true, false);

    private final String INV_CHANGE = "inv_change";

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
        Advancement MTK_START = add(getRoot("manaita_mtk_start"), consumer, null,
                AdvDisplay.create(ItemsInit.ITEM_MTK.get(), "start", new ResourceLocation("textures/block/stone.png"), FrameType.TASK, TTF),
                null,
                AdvCriterion.create("tick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE.asItem()))
        );

        Advancement ITEM_MTK = add(getRoot("item_mtk"), consumer, MTK_START,
                AdvDisplay.create(ItemsInit.ITEM_MTK.get(), "make_mtk", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create(INV_CHANGE, getInvTrigger(ItemsInit.ITEM_MTK.get()))
        );
        Advancement _opWOOD_MTK_FURNACE = add(getRoot("wood_mtk_furnace"),consumer, ITEM_MTK,
                AdvDisplay.create(BlocksInit.WOOD_MTK_FURNACE.getItem(), "make_wood_mtk_furnace", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create(INV_CHANGE, getInvTrigger(BlocksInit.WOOD_MTK_FURNACE.getItem()))
        );

        Advancement MANAITA_PAXEL = add(getRoot("manaita_paxel"), consumer, ITEM_MTK,
                AdvDisplay.create(ItemsInit.MANAITA_PAXEL.get(), "make_paxel", null, FrameType.CHALLENGE, TTF),
                null,
                AdvCriterion.create(INV_CHANGE, getInvTrigger(ItemsInit.MANAITA_PAXEL.get()))
        );

        Advancement PAXEL_OPEN_SCREEN = add(getRoot("manaita_paxel_open_screen"), consumer, MANAITA_PAXEL,
                AdvDisplay.create(ItemsInit.MANAITA_PAXEL.get(), "mtk_screen_open", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create("open_switcher", new MTKOpenTrigger.TriggerInstance(ContextAwarePredicate.ANY, MTKScreenId.MTK_SWITCHER))
        );

    }

    private String getRoot(String rootName) {
        return ManaitaMTK.MOD_ID + ":" + rootName;
    }
}
