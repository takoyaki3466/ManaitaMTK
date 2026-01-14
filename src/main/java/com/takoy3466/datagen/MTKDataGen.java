package com.takoy3466.datagen;

import com.takoy3466.datagen.provider.AdvProvider;
import com.takoy3466.datagen.provider.lang.LangJPProvider;
import com.takoy3466.datagen.provider.lang.abstracts.MTKAdvancementProvider;
import com.takoy3466.datagen.provider.recipe.RecipeProvider;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ManaitaMTK.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MTKDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean run = event.includeServer();

        addProvider(run, RecipeProvider::new, generator);

        addProvider(run, LangJPProvider::new, generator);
        generator.addProvider(run, new ForgeAdvancementProvider(output, lookupProvider, helper, List.of(new AdvProvider())));

        //addProvider(run, LangENProvider::new, generator);
        //addProvider(run, LangKOProvider::new, generator);
        // 今後全てのアイテムをdataGen方式に変える予定
    }



    public static <T extends DataProvider> void addProvider(boolean run, DataProvider.Factory<T> factory, DataGenerator generator) {
        generator.addProvider(run, factory);
    }
}
