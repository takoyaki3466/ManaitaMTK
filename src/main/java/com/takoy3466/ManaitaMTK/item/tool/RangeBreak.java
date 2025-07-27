package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class RangeBreak {

    static int x,y,z,numRange,whileRange;
    static LivingEntity entity;
    static LevelAccessor world;
    static int X, Y, Z;

    public static void control(LevelAccessor WORLD, int X, int Y, int Z, LivingEntity ENTITY, int I) {
        if (ENTITY == null)
            return;

        world = WORLD;
        x = X;
        y = Y;
        z = Z;
        entity = ENTITY;

        numRange = -1 * ( I - 1 ) / 2;
        whileRange = ( I + 1 ) / 2;
        operate(world, x, y, z, entity, numRange, whileRange);
    }
    public static void operate(LevelAccessor world, int x, int y, int z, LivingEntity entity, int numRange, int whileRange){

        if ((entity.getXRot() >= 40 && entity.getXRot() <= 90) || (entity.getXRot() <= -40 && entity.getXRot() >= -90)){
            X = numRange;
            Z = numRange;
            while (Z < whileRange){
                while(X < whileRange){
                    world.destroyBlock(BlockPos.containing(x+ X,y ,z+ Z),true,entity);
                    X++;
                }
                Z++;
                X = numRange;
            }
        }
        else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
            X = numRange;
            Y = numRange;
            while (Y < whileRange){
                while(X < whileRange){
                    world.destroyBlock(BlockPos.containing(x+ X,y+ Y, z),true,entity);
                    X++;
                }
                Y++;
                X = numRange;
            }
        }
        else if ((entity.getDirection()) == Direction.EAST || (entity.getDirection()) == Direction.WEST) {
            Y = numRange;
            Z = numRange;
            while (Y < whileRange) {
                while (Z < whileRange) {
                    world.destroyBlock(BlockPos.containing(x, y + Y, z + Z), true, entity);
                    Z++;
                }
                Y++;
                Z = numRange;
            }
        }
    }
}
