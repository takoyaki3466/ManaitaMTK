package com.takoy3466.ManaitaMTK.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MTKConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> CRUSHED_MTK_MAGNIFICATION;

    static {
        BUILDER.push("Config for ManaitaMTK");

        CRUSHED_MTK_MAGNIFICATION = BUILDER.comment("this is Crushed MTK Magnification.")
                .defineInRange("magnification =", 64, 1, 2147483647);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
