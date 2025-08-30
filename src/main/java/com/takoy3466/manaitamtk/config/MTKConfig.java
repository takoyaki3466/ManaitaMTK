package com.takoy3466.manaitamtk.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MTKConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> CRUSHED_MTK_MAGNIFICATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> CROP_GROWTH_RADIUS;
    public static final ForgeConfigSpec.ConfigValue<Integer> SWORD_KILL_RADIUS;

    static {
        BUILDER.push("Config for ManaitaMTK");

        CRUSHED_MTK_MAGNIFICATION = BUILDER.comment("Crushed MTK Magnification \n 1 < magnification < 2147483647")
                .define("magnification",64);

        CROP_GROWTH_RADIUS = BUILDER.comment("Manaita Hoe Growth Radius \n 1 < radius < 2147483647")
                        .define("radius",10);

        SWORD_KILL_RADIUS = BUILDER.comment("Manaita Sword \"kill\" radius")
                        .define("radius", 50);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
