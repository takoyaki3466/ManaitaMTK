package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.registry.register.BlockEntityRegister;
import com.takoy3466.manaitamtk.api.registry.tiered.TieredBlockRegistryObject;
import com.takoy3466.manaitamtk.block.blockEntity.AutoWorkbenchMTKBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKChestBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnaceBlockEntity;
import com.takoy3466.manaitamtk.block.blockEntity.AbstractMTKFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesInit {
    public static final BlockEntityRegister BLOCK_ENTITIES = BlockEntityRegister.create(ManaitaMTK.MOD_ID);
    
    //block_entityの追加
    public static final RegistryObject<BlockEntityType<MTKChestBlockEntity>> MTK_CHEST = BLOCK_ENTITIES.register("mtk_chest", MTKChestBlockEntity::new, BlocksInit.MTK_CHEST);

    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Wood>> MTK_FURNACE_WOOD = furnaceRegister(BlocksInit.WOOD_MTK_FURNACE, MTKFurnaceBlockEntity.Wood::new, MTKTiers.WOOD);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Stone>> MTK_FURNACE_STONE = furnaceRegister(BlocksInit.STONE_MTK_FURNACE, MTKFurnaceBlockEntity.Stone::new, MTKTiers.STONE);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Iron>> MTK_FURNACE_IRON = furnaceRegister( BlocksInit.IRON_MTK_FURNACE, MTKFurnaceBlockEntity.Iron::new, MTKTiers.IRON);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Gold>> MTK_FURNACE_GOLD = furnaceRegister(BlocksInit.GOLD_MTK_FURNACE, MTKFurnaceBlockEntity.Gold::new, MTKTiers.GOLD);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Diamond>> MTK_FURNACE_DIAMOND = furnaceRegister(BlocksInit.DIAMOND_MTK_FURNACE, MTKFurnaceBlockEntity.Diamond::new, MTKTiers.DIAMOND);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.MTK>> MTK_FURNACE_MTK = furnaceRegister(BlocksInit.MTK_MTK_FURNACE, MTKFurnaceBlockEntity.MTK::new, MTKTiers.MTK);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.GodMTK>> MTK_FURNACE_GODMTK = furnaceRegister(BlocksInit.GODMTK_MTK_FURNACE, MTKFurnaceBlockEntity.GodMTK::new, MTKTiers.GODMTK);
    public static final RegistryObject<BlockEntityType<MTKFurnaceBlockEntity.Break>> MTK_FURNACE_BREAK = furnaceRegister(BlocksInit.BREAK_MTK_FURNACE, MTKFurnaceBlockEntity.Break::new, MTKTiers.BREAK);

    public static final RegistryObject<BlockEntityType<AutoWorkbenchMTKBlockEntity>> AUTO_WORKBENCH_MTK = BLOCK_ENTITIES.register("auto_workbench_mtk", AutoWorkbenchMTKBlockEntity::new, BlocksInit.AUTO_WORKBENCH_MTK);

    public static <T extends AbstractMTKFurnaceBlockEntity> RegistryObject<BlockEntityType<T>> furnaceRegister(TieredBlockRegistryObject<MTKTier> block, BlockEntityType.BlockEntitySupplier<T> supplier, MTKTier mtkTier) {
        return BLOCK_ENTITIES.register("mtk_furnace_" + mtkTier.getName(), supplier, block);
    }


}
