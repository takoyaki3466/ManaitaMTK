package com.takoy3466.datagen.provider.abstracts;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class MTKBlockStateProvider extends BlockStateProvider {

    public MTKBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    protected void cube(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
    }

    private void cube(Block block, String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        cube(block, models().cube(name, down, up, north, south, east, west));
    }

    protected void cube(Block block, String name, String down, String up, String north, String south, String east, String west) {
        cube(block, name,
                modLoc(getTexture(name, down)),
                modLoc(getTexture(name, up)),
                modLoc(getTexture(name, north)),
                modLoc(getTexture(name, south)),
                modLoc(getTexture(name, east)),
                modLoc(getTexture(name, west))
        );
    }

    protected String getTexture(String blockName, String direction) {
        return "block/" + blockName + "_" + direction;
    }

    protected void cubeOnlyOneSide(Block block, String name, String down, String up, String side) {
        cube(block, name, down, up, side, side, side, side);
    }

    protected void cubeOnlyOneSide(Block block, String name, ResourceLocation down, ResourceLocation up, ResourceLocation side) {
        cube(block, name, down, up, side, side, side, side);
    }
}
