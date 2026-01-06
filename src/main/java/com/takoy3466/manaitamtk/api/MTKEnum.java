package com.takoy3466.manaitamtk.api;

/** @deprecated */
@Deprecated
public enum MTKEnum {
    DEFAULT(1, "default", "Default", "デフォルト", "기본"),

    WOOD(2, "wood", "Wood", "木", "나무"),
    STONE(4, "stone", "Stone", "石", "결석"),
    IRON(8, "iron", "Iron", "鉄", "철"),
    GOLD(16, "gold", "Gold", "金", "금"),
    DIAMOND(32, "diamond", "Diamond", "ダイヤモンド", "다이아몬드"),
    MTK(64, "mtk", "mtk", "MTK", "MTK"),
    GODMTK(512, "godmtk", "Godmtk", "GODMTK", "GODMTK"),

    GLASS(256, "glass", "Glass", "ガラス", "유리"),
    DIRT(1024, "dirt", "Dirt", "土", "토양"),

    BREAK(33554431, "break", "Break", "ブレイク", "부서지다");

    private final int mag;
    private final String component;
    private final String EN;
    private final String JP;
    private final String KO;

    //型の作成
    MTKEnum(int mag, String component, String EN, String JP, String KO){
        this.mag = mag;
        this.component = component;
        this.EN = EN;
        this.JP = JP;
        this.KO = KO;
    }

    public int getMag(){
        return mag;
    }
    public String getComponent(){
        return component;
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
