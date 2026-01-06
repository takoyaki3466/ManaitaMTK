package com.takoy3466.manaitamtk.datagen.provider;

import com.takoy3466.manaitamtk.api.record.MTKRecord;
import com.takoy3466.manaitamtk.datagen.provider.abstracts.MTKLangProvider;
import net.minecraft.data.PackOutput;

public class LangKOProvider extends MTKLangProvider {
    public LangKOProvider(PackOutput output) {
        super(output, "ko_kr");
    }

    @Override
    protected void addTranslate() {
        for (MTKRecord mtkRecord : list) {
            add(mtkRecord.item().get(), "휴대용 " + mtkRecord.mtkEnum().getKO() + " 화로");
        }
    }
}
