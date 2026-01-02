package com.takoy3466.manaitamtk.datagen.provider.abstracts;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.apiMTK.record.MTKRecord;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;

public abstract class MTKLangProvider extends LanguageProvider {
    protected final List<MTKRecord> MTK_RECORD = List.of(
            new MTKRecord(ItemsInit.PORTABLE_WOOD_FURNACE.get(), MTKEnum.WOOD),
            new MTKRecord(ItemsInit.PORTABLE_STONE_FURNACE.get(), MTKEnum.STONE),
            new MTKRecord(ItemsInit.PORTABLE_IRON_FURNACE.get(), MTKEnum.IRON),
            new MTKRecord(ItemsInit.PORTABLE_GOLD_FURNACE.get(), MTKEnum.GOLD),
            new MTKRecord(ItemsInit.PORTABLE_DIAMOND_FURNACE.get(), MTKEnum.DIAMOND),
            new MTKRecord(ItemsInit.PORTABLE_MTK_FURNACE.get(), MTKEnum.MTK),
            new MTKRecord(ItemsInit.PORTABLE_GODMTK_FURNACE.get(), MTKEnum.GODMTK),
            new MTKRecord(ItemsInit.PORTABLE_BREAK_FURNACE.get(), MTKEnum.BREAK)
    );

    public MTKLangProvider(PackOutput output, String locale) {
        super(output, ManaitaMTK.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addTranslate();
    }

    protected abstract void addTranslate();

}
