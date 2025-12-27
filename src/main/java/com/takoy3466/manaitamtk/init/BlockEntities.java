package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.block.blockEntity.AutoWorkbenchMTKBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {
    //block_entityの追加
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ManaitaMTK.MOD_ID);

    public static final RegistryObject<BlockEntityType<MTKChestBlockEntity>> MTK_CHEST = BLOCK_ENTITIES.register("mtk_chest",
            () -> BlockEntityType.Builder.of(MTKChestBlockEntity::new, BlocksInit.Blocks.MTK_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityWood>> MTK_FURNACE_WOOD = BLOCK_ENTITIES.register("mtk_furnace_wood",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityWood(pos, state, MTKEnum.WOOD), BlocksInit.Blocks.WOOD_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityStone>> MTK_FURNACE_STONE = BLOCK_ENTITIES.register("mtk_furnace_stone",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityStone(pos, state, MTKEnum.STONE), BlocksInit.Blocks.STONE_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityIron>> MTK_FURNACE_IRON = BLOCK_ENTITIES.register("mtk_furnace_iron",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityIron(pos, state, MTKEnum.IRON), BlocksInit.Blocks.IRON_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityGold>> MTK_FURNACE_GOLD = BLOCK_ENTITIES.register("mtk_furnace_gold",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityGold(pos, state, MTKEnum.GOLD), BlocksInit.Blocks.GOLD_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityDiamond>> MTK_FURNACE_DIAMOND = BLOCK_ENTITIES.register("mtk_furnace_diamond",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityDiamond(pos, state, MTKEnum.DIAMOND), BlocksInit.Blocks.DIAMOND_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityMTK>> MTK_FURNACE_MTK = BLOCK_ENTITIES.register("mtk_furnace_mtk",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityMTK(pos, state, MTKEnum.MTK), BlocksInit.Blocks.MTK_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityGODMTK>> MTK_FURNACE_GODMTK = BLOCK_ENTITIES.register("mtk_furnace_god",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityGODMTK(pos, state, MTKEnum.GODMTK), BlocksInit.Blocks.GODMTK_MTK_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.FurnaceEntityBreak>> MTK_FURNACE_BREAK = BLOCK_ENTITIES.register("mtk_furnace_break",
            () -> BlockEntityType.Builder.of((pos, state) -> new MTKFurnaceBlockEntity.FurnaceEntityBreak(pos, state, MTKEnum.BREAK), BlocksInit.Blocks.BREAK_MTK_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<AutoWorkbenchMTKBlockEntity>> AUTO_WORKBENCH_MTK = BLOCK_ENTITIES.register("auto_workbench_mtk",
            () -> BlockEntityType.Builder.of(AutoWorkbenchMTKBlockEntity::new, BlocksInit.Blocks.AUTO_WORKBENCH_MTK.get()).build(null));
}
