package com.takoy3466.datagen;

import com.takoy3466.datagen.provider.*;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
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
        generator.addProvider(run, new IMProvider(output, helper));
        generator.addProvider(run, new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(LTProvider::new, LootContextParamSets.BLOCK))));
        generator.addProvider(run, new BSProvider(output, helper));

        //addProvider(run, LangENProvider::new, generator);
        //addProvider(run, LangKOProvider::new, generator);
        // 今後全てのアイテムをdataGen方式に変える予定
    }

    public static <T extends DataProvider> void addProvider(boolean run, DataProvider.Factory<T> factory, DataGenerator generator) {
        generator.addProvider(run, factory);
    }
}
