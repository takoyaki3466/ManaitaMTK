package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;

public class MTKTiers {

    public static final MTKTier WOOD = MTKTier.create(2, "wood");
    public static final MTKTier STONE = MTKTier.create(4, "stone");
    public static final MTKTier IRON = MTKTier.create(8, "iron");
    public static final MTKTier GOLD = MTKTier.create(16, "gold");
    public static final MTKTier DIAMOND = MTKTier.create(32, "diamond");
    public static final MTKTier MTK = MTKTier.create(64, "mtk");
    public static final MTKTier GODMTK = MTKTier.create(512, "godmtk");
    public static final MTKTier BREAK = MTKTier.create(33554431, "break");

    public static final MTKTier GLASS = MTKTier.create(256, "glass");
    public static final MTKTier DIRT = MTKTier.create(1024, "dirt");
}
