package com.takoy3466.datagen.provider;

import com.takoy3466.datagen.provider.abstracts.MTKBlockLootProvider;
import com.takoy3466.manaitamtk.init.BlocksInit;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LTProvider extends MTKBlockLootProvider {

    public LTProvider() {
        super(Collections.emptySet()/*爆発耐性を持つアイテムのリスト*/, FeatureFlags.VANILLA_SET);
    }

    @Override
    protected void generate() {
        dropSelfMTK(BlocksInit.BLOCK_MANAITA, true);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return new Iterable<>() {
            @NotNull
            @Override
            public Iterator<Block> iterator() {
                return BlocksInit.BLOCK_MANAITA.getFront().stream().iterator();
            }
        };
        /*
        Iterator<Block> iteratorTier = BlocksInit.TIER_BLOCKS.getRegister().getFront().getEntries().stream().flatMap(RegistryObject::stream).iterator();
        Iterator<Block> iteratorBlock = BlocksInit.BLOCKS.getRegister().getFront().getEntries().stream().flatMap(RegistryObject::stream).iterator();
        return mergeIterators(iteratorBlock, iteratorTier);
        */
    }

    public static <T> Iterable<T> mergeIterators(Iterator<T> iterator1, Iterator<T> iterator2) {
        Stream<T> stream = Stream.concat(
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iterator1, 0), false),
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iterator2, 0), false)
        );
        return stream::iterator;
    }
}
