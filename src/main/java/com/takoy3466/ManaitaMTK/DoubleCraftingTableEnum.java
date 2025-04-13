package com.takoy3466.ManaitaMTK;

public enum DoubleCraftingTableEnum {
    WOOD(2, "wood", "2x"),
    STONE(4, "stone", "4x"),
    IRON(8, "iron", "8x"),
    GOLD(16, "gold", "16x"),
    DIAMOND(32, "diamond", "32x"),
    MTK(64, "mtk", "64x"),
    GODMTK(512, "godmtk", "512x");

    private final int mag;
    private final String blockname;
    private final String componentName;

    //型の作成
    DoubleCraftingTableEnum(int mag, String blockname, String componentName){
        this.mag = mag;
        this.blockname = blockname;
        this.componentName = componentName;
    }

    public int getMag(){
        return mag;
    }
    public String getBlockname(){
        return blockname;
    }

    public String getComponentName() {
        return componentName;
    }
}
