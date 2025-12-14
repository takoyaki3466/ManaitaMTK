package com.takoy3466.manaitamtk.datagen;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.datagen.recipe.RecipeProvider;
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

        addProvider(event.includeServer(), RecipeProvider::new, generator);
    }

    public static <T extends DataProvider> void addProvider(boolean run, DataProvider.Factory<T> factory, DataGenerator generator) {
        generator.addProvider(run, factory);
    }
}
