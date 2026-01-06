package com.takoy3466.manaitamtk.datagen.provider.abstracts;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.MTKEnum;
import com.takoy3466.manaitamtk.api.record.MTKRecord;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;

public abstract class MTKLangProvider extends LanguageProvider {
    protected List<MTKRecord> list = List.of(
            new MTKRecord(ItemsInit.PORTABLE_WOOD_FURNACE, MTKEnum.WOOD),
            new MTKRecord(ItemsInit.PORTABLE_STONE_FURNACE, MTKEnum.STONE),
            new MTKRecord(ItemsInit.PORTABLE_IRON_FURNACE, MTKEnum.IRON),
            new MTKRecord(ItemsInit.PORTABLE_GOLD_FURNACE, MTKEnum.GOLD),
            new MTKRecord(ItemsInit.PORTABLE_DIAMOND_FURNACE, MTKEnum.DIAMOND),
            new MTKRecord(ItemsInit.PORTABLE_MTK_FURNACE, MTKEnum.MTK),
            new MTKRecord(ItemsInit.PORTABLE_GODMTK_FURNACE, MTKEnum.GODMTK),
            new MTKRecord(ItemsInit.PORTABLE_BREAK_FURNACE, MTKEnum.BREAK)
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
