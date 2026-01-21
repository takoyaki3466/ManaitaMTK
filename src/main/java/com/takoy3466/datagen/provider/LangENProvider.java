package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKLangProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredRegistryObject;
import com.takoy3466.manaitamtk.init.ItemsInit;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

public class LangENProvider extends MTKLangProvider {
    public LangENProvider(PackOutput output) {
        super(output, ManaitaMTK.MOD_ID, "en_us");
    }

    @Override
    protected void groupTranslate() {

    }

    @Override
    protected void itemTranslate() {
        portableFurnace(ItemsInit.PORTABLE_WOOD_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_STONE_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_IRON_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_GOLD_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_DIAMOND_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_MTK_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_GODMTK_FURNACE);
        portableFurnace(ItemsInit.PORTABLE_BREAK_FURNACE);


    }
    private void portableFurnace(TieredRegistryObject<Item, MTKTier> object) {
        add(object.get(), "Portable " + of(object.getTier()) + " Furnace");
    }

    @Override
    protected void blockTranslate() {

    }

    @Override
    protected void keyTranslate() {

    }

    @Override
    protected void advancementTranslate() {

    }

    @Override
    protected void hoverTextTranslate() {

    }

    @Override
    protected void titleTranslate() {

    }

    @Override
    protected void otherTranslate() {

    }
}
