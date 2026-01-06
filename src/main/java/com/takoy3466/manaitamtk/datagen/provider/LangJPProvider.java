package com.takoy3466.manaitamtk.datagen.provider;

import com.takoy3466.manaitamtk.api.record.MTKRecord;
import com.takoy3466.manaitamtk.datagen.provider.abstracts.MTKLangProvider;
import net.minecraft.data.PackOutput;

public class LangJPProvider extends MTKLangProvider {
    public LangJPProvider(PackOutput output) {
        super(output, "ja_jp");

    }

    @Override
    protected void addTranslate() {
        for (MTKRecord mtkRecord : list) {
            add(mtkRecord.item().get(), "ポータブル" + mtkRecord.mtkEnum().getJP() + "かまど");
        }
    }
}
