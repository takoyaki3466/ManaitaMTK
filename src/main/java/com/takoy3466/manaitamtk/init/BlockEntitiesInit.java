package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.register.MTKRegistryObject;
import com.takoy3466.manaitamtk.block.blockEntity.AutoWorkbenchMTKBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ManaitaMTK.MOD_ID);
    //block_entityの追加
    public static final RegistryObject<BlockEntityType<MTKChestBlockEntity>> MTK_CHEST = register("mtk_chest", MTKChestBlockEntity::new, BlocksInit.MTK_CHEST);

    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_WOOD = register(BlocksInit.WOOD_MTK_FURNACE, MTKTiers.WOOD);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_STONE = register(BlocksInit.STONE_MTK_FURNACE, MTKTiers.STONE);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_IRON = register( BlocksInit.IRON_MTK_FURNACE, MTKTiers.IRON);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_GOLD = register(BlocksInit.GOLD_MTK_FURNACE, MTKTiers.GOLD);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_DIAMOND = register(BlocksInit.DIAMOND_MTK_FURNACE, MTKTiers.DIAMOND);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_MTK = register(BlocksInit.MTK_MTK_FURNACE, MTKTiers.MTK);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_GODMTK = register(BlocksInit.GODMTK_MTK_FURNACE, MTKTiers.GODMTK);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> MTK_FURNACE_BREAK = register(BlocksInit.BREAK_MTK_FURNACE, MTKTiers.BREAK);

    public static final RegistryObject<BlockEntityType<AutoWorkbenchMTKBlockEntity>> AUTO_WORKBENCH_MTK = register("auto_workbench_mtk", AutoWorkbenchMTKBlockEntity::new, BlocksInit.AUTO_WORKBENCH_MTK);


    public static RegistryObject<BlockEntityType<MTKFurnaceBlockEntity>> register(MTKRegistryObject<Block, Item> block, MTKTier mtkTier) {
        return BLOCK_ENTITY.register("mtk_furnace_" + mtkTier.getName(), () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity(pos, state, mtkTier), block.getBlock()).build(null));
    }

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, MTKRegistryObject<Block, Item> block) {
        return BLOCK_ENTITY.register(name, () -> BlockEntityType.Builder.of(supplier, block.getBlock()).build(null));
    }
}
