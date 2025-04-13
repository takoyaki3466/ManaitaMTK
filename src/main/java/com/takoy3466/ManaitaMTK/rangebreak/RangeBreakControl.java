package com.takoy3466.ManaitaMTK.rangebreak;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class RangeBreakControl {

    static int x,y,z,numRange,whileRange;
    static LivingEntity entity;
    static LevelAccessor world;

    public static void control(LevelAccessor WORLD, int X, int Y, int Z, LivingEntity ENTITY, int I) {
        if (ENTITY == null)
            return;

        world = WORLD;
        x = X;
        y = Y;
        z = Z;
        entity = ENTITY;

        switch (I){
            case 1: //3 * 3
                numRange = -1;
                whileRange = 2;
                break;
            case 2: //5 * 5
                numRange = -2;
                whileRange = 3;
                break;
            case 3: //7 * 7
                numRange = -3;
                whileRange =4;
                break;
            case 4: //9 * 9
                numRange = -4;
                whileRange = 5;
                break;
            default:
                numRange = 0;
                whileRange = 0;
        }
        RangeBreakPLANEOperate.operate(world, x, y, z, entity, numRange, whileRange);
    }
}
