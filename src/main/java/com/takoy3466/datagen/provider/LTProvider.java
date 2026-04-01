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
        dropSelfMTK(BlocksInit.BLOCK_MANAITA);
        dropSelfMTK(BlocksInit.BLOCK_MANAITA_DIAMOND);
        dropSelfMTK(BlocksInit.BLOCK_MANAITA_DIRT);
        dropSelfMTK(BlocksInit.BLOCK_MANAITA_GLASS);

        dropSelfMTK(BlocksInit.WOOD_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.STONE_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.IRON_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.GOLD_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.DIAMOND_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.MTK_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.GODMTK_CRAFTING_TABLE);
        dropSelfMTK(BlocksInit.BREAK_CRAFTING_TABLE);

        dropSelfMTK(BlocksInit.MTK_CHEST);

        dropSelfMTK(BlocksInit.WOOD_MTK_FURNACE);
        dropSelfMTK(BlocksInit.STONE_MTK_FURNACE);
        dropSelfMTK(BlocksInit.IRON_MTK_FURNACE);
        dropSelfMTK(BlocksInit.GOLD_MTK_FURNACE);
        dropSelfMTK(BlocksInit.DIAMOND_MTK_FURNACE);
        dropSelfMTK(BlocksInit.MTK_MTK_FURNACE);
        dropSelfMTK(BlocksInit.GODMTK_MTK_FURNACE);
        dropSelfMTK(BlocksInit.BREAK_MTK_FURNACE);

        dropSelfMTK(BlocksInit.AUTO_WORKBENCH_MTK);

        dropSelfMTK(BlocksInit.WOOD_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.STONE_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.IRON_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.GOLD_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.DIAMOND_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.MTK_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.GODMTK_MULTI_FURNACE);
        dropSelfMTK(BlocksInit.BREAK_MULTI_FURNACE);

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        Iterator<Block> iteratorTier = BlocksInit.TIER_BLOCKS.getRegister().getFront().getEntries().stream().flatMap(RegistryObject::stream).iterator();
        Iterator<Block> iteratorBlock = BlocksInit.BLOCKS.getRegister().getFront().getEntries().stream().flatMap(RegistryObject::stream).iterator();
        Stream<Block> stream = Stream.concat(
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iteratorTier, 0), false),
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iteratorBlock, 0), false)
        );
        return stream::iterator;
    }
}
