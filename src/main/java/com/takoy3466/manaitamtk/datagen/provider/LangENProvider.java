package com.takoy3466.manaitamtk.datagen.provider;

import com.takoy3466.manaitamtk.api.record.MTKRecord;
import com.takoy3466.manaitamtk.datagen.provider.abstracts.MTKLangProvider;
import net.minecraft.data.PackOutput;

public class LangENProvider extends MTKLangProvider {
    public LangENProvider(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslate() {
        for (MTKRecord mtkRecord : list) {
            add(mtkRecord.item().get(), "Portable " + mtkRecord.mtkEnum().getEN() + " Furnace");
        }
    }
}
