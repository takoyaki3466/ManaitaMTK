package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKLangProvider;
import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMappings;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredBlockRegistryObject;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredRegistryObject;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.init.ItemsInit;
import com.takoy3466.manaitamtk.init.MTKTiers;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

public class LangJPProvider extends MTKLangProvider {
    public LangJPProvider(PackOutput output) {
        super(output, ManaitaMTK.MOD_ID, "ja_jp");

    }

    @Override
    protected void groupTranslate() {
        add("itemGroup.mtk_main", "まな板MTK");
    }

    @Override
    protected void itemTranslate() {
        portableFurnace(ItemsInit.PORTABLE_WOOD_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_STONE_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_IRON_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_GOLD_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_DIAMOND_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_MTK_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_GODMTK_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_BREAK_FURNACE);

        doubleBlock(ItemsInit.WOOD_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.STONE_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.IRON_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.GOLD_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.DIAMOND_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.MTK_DOUBLE_BLOCK_MTK);
        doubleBlock(ItemsInit.GODMTK_DOUBLE_BLOCK_MTK);

        portableCraftTable(ItemsInit.PORTABLE_WOOD_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_STONE_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_IRON_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_GOLD_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_DIAMOND_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_MTK_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_GODMTK_CRAFTING_TABLE);
        portableCraftTable(ItemsInit.PORTABLE_BREAK_CRAFTING_TABLE);

        add(ItemsInit.HELMET_MANAITA.get(), "まな板のヘルメット");
        add(ItemsInit.CHESTPLATE_MANAITA.get(), "まな板のチェストプレート");
        add(ItemsInit.LEGINS_MANAITA.get(), "まな板のレギンス");
        add(ItemsInit.BOOTS_MANAITA.get(), "まな板のブーツ");

        add(ItemsInit.MANAITA_PICKAXE.get(), "まな板のツルハシ");
        add(ItemsInit.MANAITA_AXE.get(), "まな板の斧");
        add(ItemsInit.MANAITA_SHOVEL.get(), "まな板のシャベル");
        add(ItemsInit.MANAITA_SWORD.get(), "まな板の剣");
        add(ItemsInit.MANAITA_PAXEL.get(), "まな板のパクセル");
        add(ItemsInit.MANAITA_BOW.get(), "まな板の弓");
        add(ItemsInit.MANAITA_HOE.get(), "まな板のクワ");

        add(ItemsInit.CHANGEABLE_PORTABLE_DCT.get(), "可変式ポータブル倍化作業台");
        add(ItemsInit.ITEM_MTK.get(), "MTK");
        add(ItemsInit.CRUSHED_MTK.get(), "MTKの粉");
        add(ItemsInit.DEBUG_MTK.get(), "デバッグMTK");

        add(ItemsInit.MTK_BACKPACK.get(), "MTKバックパック");
    }
    private void portableFurnace(TieredRegistryObject<Item, MTKTier> object) {
        if (object.getTier() != MTKTiers.GODMTK) {
            add(object.get(), "ポータブル" + of(object.getTier()).getJP() + "かまど");
        }else {
            add(object.get(), "ポータブルGODMTKかまど");
        }
    }
    private void doubleBlock(TieredRegistryObject<Item, MTKTier> object) {
        add(object.get(), of(object.getTier()).getJP() + "の" + object.getTier().getMultiple() + "倍MTK");
    }
    private void portableCraftTable(TieredRegistryObject<Item, MTKTier> object) {
        add(object.get(), "ポータブル" + of(object.getTier()).getJP() + "の" + object.getTier().getMultiple() + "倍作業台");
    }


    @Override
    protected void blockTranslate() {
        craftTable(BlocksInit.WOOD_CRAFTING_TABLE);
        craftTable(BlocksInit.STONE_CRAFTING_TABLE);
        craftTable(BlocksInit.IRON_CRAFTING_TABLE);
        craftTable(BlocksInit.GOLD_CRAFTING_TABLE);
        craftTable(BlocksInit.DIAMOND_CRAFTING_TABLE);
        craftTable(BlocksInit.MTK_CRAFTING_TABLE);
        craftTable(BlocksInit.GODMTK_CRAFTING_TABLE);
        craftTable(BlocksInit.BREAK_CRAFTING_TABLE);
        furnace(BlocksInit.WOOD_MTK_FURNACE);
        furnace(BlocksInit.STONE_MTK_FURNACE);
        furnace(BlocksInit.IRON_MTK_FURNACE);
        furnace(BlocksInit.GOLD_MTK_FURNACE);
        furnace(BlocksInit.DIAMOND_MTK_FURNACE);
        furnace(BlocksInit.MTK_MTK_FURNACE);
        furnace(BlocksInit.GODMTK_MTK_FURNACE);
        furnace(BlocksInit.BREAK_MTK_FURNACE);

        add(BlocksInit.BLOCK_MANAITA.getBlock(), "まな板");
        add(BlocksInit.BLOCK_MANAITA_DIAMOND.getBlock(), "まな板のダイヤモンド");
        add(BlocksInit.BLOCK_MANAITA_DIRT.getBlock(), "まな板土");
        add(BlocksInit.BLOCK_MANAITA_GLASS.getBlock(), "まな板のガラス");

        add(BlocksInit.MTK_CHEST.getBlock(), "まな板チェスト");
        add(BlocksInit.AUTO_WORKBENCH_MTK.getBlock(), "自動作業台");
    }
    private void craftTable(TieredBlockRegistryObject<MTKTier> object) {
        add(object.getBlock(), of(object.getTier()).getJP() + "の" + object.getTier().getMultiple() + "倍作業台");
    }
    private void furnace(TieredBlockRegistryObject<MTKTier> object) {
        add(object.getBlock(), of(object.getTier()).getJP() + "の" + object.getTier().getMultiple() + "倍かまど");
    }

    @Override
    protected void keyTranslate() {
        add(MTKKeyMappings.KEY.getCategories(), "まな板MTK");
        add(MTKKeyMappings.HelmetKey.getName(), "ヘルメットキー");
        add(MTKKeyMappings.FlySpeedKey.getName(), "飛行速度変更キー");
        add(MTKKeyMappings.MagnificationKey.getName(), "倍率変更キー");
        add(MTKKeyMappings.MTKSwitcherOpenKey.getName(), "スイッチャーを開く");
        add(MTKKeyMappings.MTKSwitcherSelectKey.getName(), "スイッチャーでの選択の移動");
        add(MTKKeyMappings.SwitchExterminationKey.getName(), "まな板剣モード切替");
    }

    @Override
    protected void advancementTranslate() {
        add("advancements.start.title", "Manaita MTK");
        add("advancements.start.description", "Manaita MTK");
        add("advancements.placed_block_manaita.title", "テクスチャがのっぺりしてるような気がする...");
        add("advancements.placed_block_manaita.description", "まな板ブロックを作ろう！");
        add("advancements.make_mtk.title", "このまな板設置できないんだけど！！");
        add("advancements.make_mtk.description", "MTKを作ろう");
        add("advancements.make_paxel.title", "パクセルこそ至高！！！");
        add("advancements.make_paxel.description", "まな板のパクセルを作ろう！");
        add("advancements.placed_block_manaita_diamond.title", "ダイヤなのに木の音がするまな板！");
        add("advancements.placed_block_manaita_diamond.description", "まな板ダイヤブロックを作ろう！");
        add("advancements.placed_block_manaita_dirt.title", "土なのにまな「板」...?");
        add("advancements.placed_block_manaita_dirt.description", "まな板土を作ろう！");
        add("advancements.placed_block_manaita_glass.title", "ガラスなのに木の音がする...");
        add("advancements.placed_block_manaita_glass.description", "まな板ガラスブロックを作ろう！");
        add("advancements.make_crushed_mtk.title", "粉砕機はないのか？");
        add("advancements.make_crushed_mtk.description", "MTKを焼いてみよう！");
        add("advancements.make_break_crafting_table.title", "なんか数字のキリ悪くない...?");
        add("advancements.make_break_crafting_table.description", "ブレイク作業台を作ろう！");
        add("advancement.mtk_screen_open.title", "なんか見覚えあるスクリーンだな...");
        add("advancement.mtk_screen_open.description", "F3とF4押したときに出てくるやつやないか！");
        add("advancements.make_break_mtk_furnace.title", "あれ...テクスチャの使いまわし...?");
        add("advancements.make_break_mtk_furnace.description", "ブレイクかまどを作ろう！");
        add("advancements.make_wood_mtk_furnace.title", "待て待て！木で焼いたら燃えちゃうって！");
        add("advancements.make_wood_mtk_furnace.description", "木のかまどを作ろう！");
        add("advancements.make_mtk_mtk_furnace.title", "MTK_MTK_FURNACEって冗長すぎないか...?");
        add("advancements.make_mtk_mtk_furnace.description", "MTKかまどを作ろう！");
        add("advancements.placed_mtk_chest.title","「板」のチェストに物は入らなくないか?");
        add("advancements.placed_mtk_chest.description", "まな板チェストを作ろう！");
        add("advancements.mtk_backpack.title", "このバック...ペラペラじゃない?");
        add("advancements.mtk_backpack.description", "MTKバックパックを作ってみよう！");
        add("advancements.auto_workbench_mtk.title", "BuildCraft");
        add("advancements.auto_workbench_mtk.description", "自動作業台を作ろう！");

    }

    @Override
    protected void hoverTextTranslate() {
        add("item.manaitamtk.zikokenziyoku.hover_text", "注意！　右クリックすると即死します！");
        add("item.manaitamtk.manaita_axe.hover_text", "シフト右クリックで剥いだ木を戻せる！");
        add("item.manaitamtk.helmet_manaita.hover_text", "速さ : ");
        add("item.manaitamtk.manaita_hoe.hover_text", "シフト右クリックで耕せる範囲 : ");
        add("item.manaitamtk.bow_mtk.hover_text", "発射した矢は30秒で消えるよ！");
        add("item.manaitamtk.manaita_sword_hover_text", "左クリックでも右クリックでも即死！");
        add("item.manaitamtk.mtk_backpack.hover_text", "空っぽだね");
        add("item.manaitamtk.manaita_sword.hover_text_mode", "モード: ");
        add("item.manaitamtk.manaita_pickaxe_hover_text", "範囲を指定したら、右クリックで範囲破壊できるよ！");
        add("item.manaitamtk.manaita_paxel_hover_text", "範囲を指定したら、左クリックで範囲破壊できるよ！");

        add("block.manaitamtk.mtk_furnace_iron.hover_text", "これくらいがちょうどよくない？");
        add("block.manaitamtk.mtk_furnace_mtk.hover_text", "一回の精錬で中身がいっぱい！");
        add("block.manaitamtk.mtk_furnace_break.hover_text", "こんなにいらない！ (33554431倍)");
        add("block.manaitamtk.mtk_chest.hover_text_1", "21億 × 54...?");
        add("block.manaitamtk.mtk_chest.hover_text_2", "アイテムのテクスチャ...なんかのっぺりしてない...?");
        add("block.manaitamtk.auto_workbench_mtk.hover_text", "GUIを開いている時は最後に使ったレシピが表示されます");

        add("item.manaitamtk.fwai.first_text", "飛行速度 : ");
        add("item.manaitamtk.fwai.second_text", " | 歩く速度 : ");
    }

    @Override
    protected void titleTranslate() {
        add("gui.mtk_switcher_screen.first_title", "");
        add("gui.mtk_switcher_screen.second_title", "またはホイールで移動");

        add("block.manaitamtk.mtk_crafting_table.title", "MTK作業台");
        add("block.manaitamtk.mtk_furnace.title", "MTKかまど");
        add("item.manaitamtk.portable_furnace.title", "ポータブルかまど");
    }

    @Override
    protected void otherTranslate() {
        add("tooltip.amount", "個数: ");
        add("util.manaitamtk.press_to_shift", "SHIFTを押して説明文を表示");
        add("gui.overlay.sword.enemy_die", "敵殲滅モード");
        add("gui.overlay.sword.all_die", "全エンティティ殲滅モード");
        add("mtk_cant_move_key", "Cant Move Key");
    }
}
