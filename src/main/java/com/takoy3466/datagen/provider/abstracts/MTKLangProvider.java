package com.takoy3466.datagen.provider.abstracts;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.MTKTiers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class MTKLangProvider extends LanguageProvider {

    public MTKLangProvider(PackOutput output, String modId, String locale) {
        super(output, modId, locale);
    }

    @Override
    protected void addTranslations() {
        groupTranslate();
        itemTranslate();
        blockTranslate();
        keyTranslate();
        advancementTranslate();
        hoverTextTranslate();
        titleTranslate();
        otherTranslate();

    }

    protected abstract void groupTranslate();

    protected abstract void itemTranslate();

    protected abstract void blockTranslate();

    protected abstract void keyTranslate();

    protected abstract void advancementTranslate();

    protected void addAdvTranslate(String baseName, String title, String description) {
        add("advancements." + baseName + ".title", title);
        add("advancements." + baseName + ".description", description);
    }

    protected abstract void hoverTextTranslate();

    protected abstract void titleTranslate();

    protected abstract void otherTranslate();

    protected enum MTKLangEnum {

        WOOD("Wood", "木", "나무"),
        STONE("Stone", "石", "결석"),
        IRON("Iron", "鉄", "철"),
        GOLD("Gold", "金", "금"),
        DIAMOND( "Diamond", "ダイヤモンド", "다이아몬드"),
        MTK( "mtk", "MTK", "MTK"),
        GODMTK("Godmtk", "GOD神々", "GODMTK"),

        GLASS("Glass", "ガラス", "유리"),
        DIRT("Dirt", "土", "토양"),

        BREAK("Break", "ブレイク", "부서지다");

        private final String EN;
        private final String JP;
        private final String KO;

        MTKLangEnum(String EN, String JP, String KO){
            this.EN = EN;
            this.JP = JP;
            this.KO = KO;
        }

        public String getJP() {
            return JP;
        }

        public String getEN() {
            return EN;
        }

        public String getKO() {
            return KO;
        }
    }

    protected MTKLangEnum of(MTKTier mtkTier) {
        if (mtkTier == MTKTiers.WOOD) return MTKLangEnum.WOOD;
        else if (mtkTier == MTKTiers.STONE) return MTKLangEnum.STONE;
        else if (mtkTier == MTKTiers.IRON) return MTKLangEnum.IRON;
        else if (mtkTier == MTKTiers.GOLD) return MTKLangEnum.GOLD;
        else if (mtkTier == MTKTiers.DIAMOND) return MTKLangEnum.DIAMOND;
        else if (mtkTier == MTKTiers.MTK) return MTKLangEnum.MTK;
        else if (mtkTier == MTKTiers.GODMTK) return MTKLangEnum.GODMTK;
        else if (mtkTier == MTKTiers.BREAK) return MTKLangEnum.BREAK;
        else return MTKLangEnum.WOOD;
    }
}
