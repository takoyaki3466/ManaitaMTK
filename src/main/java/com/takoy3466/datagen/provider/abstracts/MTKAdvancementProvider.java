package com.takoy3466.datagen.provider.abstracts;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class MTKAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    protected Advancement add(String root, Consumer<Advancement> consumer, @Nullable Advancement parent, AdvDisplay advDisplay, @Nullable AdvancementRewards rewards, AdvCriterion AdvCriterion) {
        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(parent);
        }
        addDisplay(builder, advDisplay);

        if (rewards != null) {
            builder.rewards(rewards);
        }
        return builder.addCriterion(AdvCriterion.criterionName(), AdvCriterion.criterion()).save(consumer, root);
    }

    protected Advancement add(@Nullable Advancement parent, AdvDisplay advDisplay, @Nullable AdvancementRewards rewards, Consumer<Advancement> consumer, String root, AdvCriterion... advCriteria) {
        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(parent);
        }
        addDisplay(builder, advDisplay);

        if (rewards != null) {
            builder.rewards(rewards);
        }
        addCriterions(builder, advCriteria);
        return builder.save(consumer, root);
    }

    /**
     * displayメソッドの引数だけ異様に多いのでわかりやすくするためにまとめています
     * @param displayItem 表示されるアイテムアイコン
     * @param title 進捗のタイトル
     * @param description 進捗の説明
     * @param backGround 進捗の背景の場所 @Nullable
     * @param frameType フレームの色
     * @param isPopUp 達成したときに画面右上にポップアップ表示するかどうか
     * @param isFrowChat 達成したときにチャット欄に表示するかどうか
     * @param isHideUntilAchieved 達成されるまで進捗を進捗管理画面から隠すかどうか
     */
    protected record AdvDisplay(ItemLike displayItem, Component title, Component description, @Nullable ResourceLocation backGround, FrameType frameType, boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {

        public static AdvDisplay create(ItemLike item, Component title, Component description, @Nullable ResourceLocation backGround, FrameType frameType, boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {
            return new AdvDisplay(item, title, description, backGround, frameType, isPopUp, isFrowChat, isHideUntilAchieved);
        }

        public static AdvDisplay create(ItemLike item, String titlePath, String descriptionPath, @Nullable ResourceLocation backGround, FrameType frameType, boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {
            return create(item, Component.translatable(titlePath), Component.translatable(descriptionPath), backGround, frameType, isPopUp, isFrowChat, isHideUntilAchieved);
        }

        // めんどくさい人向け
        public static AdvDisplay create(ItemLike item, String baseCommonName, @Nullable ResourceLocation backGround, FrameType frameType, boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {
            Component title = Component.translatable("advancements." + baseCommonName + ".title");
            Component description = Component.translatable("advancements." + baseCommonName + ".description");
            return create(item, title, description, backGround, frameType, isPopUp, isFrowChat, isHideUntilAchieved);
        }

        // エクストリームめんどくさい人向け
        public static AdvDisplay create(ItemLike item, String baseCommonName, @Nullable ResourceLocation backGround, FrameType frameType, ForPeopleWhoFindWorkTedious tedious) {
            Component title = Component.translatable("advancements." + baseCommonName + ".title");
            Component description = Component.translatable("advancements." + baseCommonName + ".description");
            return create(item, title, description, backGround, frameType, tedious.isPopUp(), tedious.isFrowChat(), tedious.isHideUntilAchieved());
        }
    }

    /**
     * 複数回addCriterionしたい場合を考えこのようなレコードクラスを設けています
     * @param criterionName 条件の名前
     * @param criterion 条件本体
     */
    protected record AdvCriterion(String criterionName, Criterion criterion) {

        public static AdvCriterion create(String criterionName, Criterion criterion) {
            return new AdvCriterion(criterionName, criterion);
        }

        public static AdvCriterion create(String criterionName, CriterionTriggerInstance instance) {
            return new AdvCriterion(criterionName, new Criterion(instance));
        }
    }

    /**
     * 面倒くさい人向けにまとめたものを先に作っておく用
     */
    protected record ForPeopleWhoFindWorkTedious(boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {

        public static ForPeopleWhoFindWorkTedious create(boolean isPopUp, boolean isFrowChat, boolean isHideUntilAchieved) {
            return new ForPeopleWhoFindWorkTedious(isPopUp, isFrowChat, isHideUntilAchieved);
        }
    }

    @SuppressWarnings("SameParameterValue")
    protected String getRoot(String modId, String rootName) {
        return modId + ":" + rootName;
    }

    protected void addCriterions(Advancement.Builder builder, AdvCriterion... criteria) {
        for (AdvCriterion criterion : criteria) {
            builder.addCriterion(criterion.criterionName(), criterion.criterion());
        }
    }

    protected void addDisplay(Advancement.Builder builder, AdvDisplay advDisplay) {
        builder.display(
                advDisplay.displayItem(),
                advDisplay.title(),
                advDisplay.description(),
                advDisplay.backGround(),
                advDisplay.frameType(),
                advDisplay.isPopUp(),
                advDisplay.isFrowChat(),
                advDisplay.isHideUntilAchieved()
        );
    }

    protected static InventoryChangeTrigger.TriggerInstance getInvTrigger(ItemLike itemLike) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(itemLike);
    }
}
