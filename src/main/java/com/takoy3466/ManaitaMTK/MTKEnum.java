package com.takoy3466.ManaitaMTK;

public enum MTKEnum {
    DEFAULT(1, "default"),

    WOOD(2, "wood"),
    STONE(4, "stone"),
    IRON(8, "iron"),
    GOLD(16, "gold"),
    DIAMOND(32, "diamond"),
    MTK(64, "mtk"),
    GODMTK(512, "godmtk"),

    GLASS(256, "glass"),
    DIRT(1024, "dirt"),

    BREAK(33554431, "break");

    private final int mag;
    private final String blockname;

    //型の作成
    MTKEnum(int mag, String blockname){
        this.mag = mag;
        this.blockname = blockname;
    }

    public int getMag(){
        return mag;
    }
    public String getBlockname(){
        return blockname;
    }
    public static final MTKEnum[] VALUES = MTKEnum.values();
}
