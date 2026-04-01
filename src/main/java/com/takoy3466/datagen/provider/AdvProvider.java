package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKAdvancementProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.criterionTrigger.ScreenOpenTrigger;
import com.takoy3466.manaitamtk.core.MTKScreenId;
import com.takoy3466.manaitamtk.criterionTrigger.MTKTrigger;
import com.takoy3466.manaitamtk.criterionTrigger.RightClickTrigger;
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
                getInvChangeCriterion(ItemsInit.ITEM_MTK.get())
        );
        Advancement WOOD_MTK_FURNACE = add(getRoot("wood_mtk_furnace"),consumer, ITEM_MTK,
                AdvDisplay.create(BlocksInit.WOOD_MTK_FURNACE.getItem(), "make_wood_mtk_furnace", null, FrameType.TASK, TTF),
                null,
                getInvChangeCriterion(BlocksInit.WOOD_MTK_FURNACE.getItem())
        );

        Advancement MTK_MTK_FURNACE = add(getRoot("mtk_mtk_furnace"), consumer, WOOD_MTK_FURNACE,
                AdvDisplay.create(BlocksInit.MTK_MTK_FURNACE.getItem(), "make_mtk_mtk_furnace", null, FrameType.TASK, TTF),
                null,
                getInvChangeCriterion(BlocksInit.MTK_MTK_FURNACE.getItem())
        );

        Advancement MANAITA_PAXEL = add(getRoot("manaita_paxel"), consumer, ITEM_MTK,
                AdvDisplay.create(ItemsInit.MANAITA_PAXEL.get(), "make_paxel", null, FrameType.CHALLENGE, TTF),
                null,
                getInvChangeCriterion(ItemsInit.MANAITA_PAXEL.get())
        );

        Advancement PAXEL_OPEN_SCREEN = add(getRoot("manaita_paxel_open_screen"), consumer, MANAITA_PAXEL,
                AdvDisplay.create(ItemsInit.MANAITA_PAXEL.get(), "mtk_screen_open", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create("open_switcher", new ScreenOpenTrigger.TriggerInstance(ContextAwarePredicate.ANY, MTKScreenId.MTK_SWITCHER))
        );

        Advancement WOOD_MULTI_FURNACE = add(getRoot("wood_multi_furnace"), consumer, MTK_MTK_FURNACE,
                AdvDisplay.create(BlocksInit.WOOD_MULTI_FURNACE.getItem(), "make_wood_multi_furnace", null, FrameType.TASK, TTF),
                null,
                getInvChangeCriterion(BlocksInit.WOOD_MULTI_FURNACE.getItem())
        );

        Advancement BREAK_CRAFTING_TABLE = add(getRoot("break_crafting_table"), consumer, ITEM_MTK,
                AdvDisplay.create(BlocksInit.BREAK_CRAFTING_TABLE.getItem(), "make_break_crafting_table", null, FrameType.CHALLENGE, TTF),
                null,
                getInvChangeCriterion(BlocksInit.BREAK_CRAFTING_TABLE.getItem())
        );

        Advancement BREAK_MTK_FURNACE = add(getRoot("break_mtk_furnace"), consumer, BREAK_CRAFTING_TABLE,
                AdvDisplay.create(BlocksInit.BREAK_MTK_FURNACE.getItem(), "make_break_mtk_furnace", null, FrameType.CHALLENGE, TTF),
                null,
                getInvChangeCriterion(BlocksInit.BREAK_MTK_FURNACE.getItem())
        );

        Advancement BREAK_MULTI_FURNACE = add(getRoot("break_multi_furnace"), consumer, BREAK_MTK_FURNACE,
                AdvDisplay.create(BlocksInit.BREAK_MULTI_FURNACE.getItem(), "make_break_multi_furnace", null, FrameType.CHALLENGE, TTF),
                null,
                getInvChangeCriterion(BlocksInit.BREAK_MULTI_FURNACE.getItem())
        );

        Advancement USE_SWORD = add(getRoot("use_sword"), consumer, MANAITA_PAXEL,
                AdvDisplay.create(ItemsInit.MANAITA_SWORD.get(), "use_sword", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create("use_sword", new RightClickTrigger.TriggerInstance(ContextAwarePredicate.ANY, ItemPredicate.Builder.item().of(ItemsInit.MANAITA_SWORD.get()).build()))
        );

        Advancement CRAFT_IN_BREAK_CRAFTING_TABLE = add(getRoot("craft_in_break_ct"), consumer, BREAK_CRAFTING_TABLE,
                AdvDisplay.create(BlocksInit.BREAK_CRAFTING_TABLE.getItem(), "craft_in_break_ct", null, FrameType.TASK, TTF),
                null,
                AdvCriterion.create("craft_in_break_ct", new MTKTrigger.TriggerInstance(ContextAwarePredicate.ANY, MTKTrigger.CRAFT_IN_BREAK_CRAFTING_TABLE))
                );

    }

    private String getRoot(String rootName) {
        return ManaitaMTK.MOD_ID + ":" + rootName;
    }
}
