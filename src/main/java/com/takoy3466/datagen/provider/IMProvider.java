package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKItemModelProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IMProvider extends MTKItemModelProvider {

    public IMProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManaitaMTK.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemsInit.PORTABLE_WOOD_FURNACE);
        basicItem(ItemsInit.PORTABLE_STONE_FURNACE);
        basicItem(ItemsInit.PORTABLE_IRON_FURNACE);
        basicItem(ItemsInit.PORTABLE_GOLD_FURNACE);
        basicItem(ItemsInit.PORTABLE_DIAMOND_FURNACE);
        basicItem(ItemsInit.PORTABLE_MTK_FURNACE);
        basicItem(ItemsInit.PORTABLE_GODMTK_FURNACE);
        basicItem(ItemsInit.PORTABLE_BREAK_FURNACE);
    }
}
