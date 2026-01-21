package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKBlockStateProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.init.BlocksInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BSProvider extends MTKBlockStateProvider {
    private final String TOP = "top";
    private final String  BOTTOM = "bottom";
    private final String  SIDE1 = "side1";
    private final String  SIDE2 = "side2";
    private final String  SIDE3 = "side3";
    private final String  SIDE4 = "side4";

    public BSProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ManaitaMTK.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cube(BlocksInit.BLOCK_MANAITA.getBlock(), "block_manaita", TOP, TOP, SIDE2, SIDE1, SIDE1, SIDE1);
    }
}
