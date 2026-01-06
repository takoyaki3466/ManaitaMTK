package com.takoy3466.manaitamtk.datagen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.datagen.provider.RecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ManaitaMTK.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MTKDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        boolean run = event.includeServer();

        addProvider(run, RecipeProvider::new, generator);

        /*addProvider(run, LangJPProvider::new, generator);
        addProvider(run, LangENProvider::new, generator);
        addProvider(run, LangKOProvider::new, generator);*/
        // もともと手書きだったため一回生成したらおわり。今後全てのアイテムをdataGen方式に変える予定
    }

    public static <T extends DataProvider> void addProvider(boolean run, DataProvider.Factory<T> factory, DataGenerator generator) {
        generator.addProvider(run, factory);
    }
}
