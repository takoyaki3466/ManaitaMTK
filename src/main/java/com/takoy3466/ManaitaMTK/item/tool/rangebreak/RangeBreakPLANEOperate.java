package com.takoy3466.ManaitaMTK.item.tool.rangebreak;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class RangeBreakPLANEOperate {

    static int numx,numy, numz;

    public static void operate(LevelAccessor world, int x, int y, int z, LivingEntity entity, int numRange, int whileRange){
            
        if ((entity.getXRot() >= 40 && entity.getXRot() <= 90) || (entity.getXRot() <= -40 && entity.getXRot() >= -90)){
            numx = numRange;
            numz = numRange;
            while (numz < whileRange){
                while(numx < whileRange){
                    world.destroyBlock(BlockPos.containing(x+numx,y,z+numz),true,entity);
                    numx++;
                }
                numz++;
                numx = numRange;
            }
        }
        else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
            numx = numRange;
            numy = numRange;
            while (numy < whileRange){
                while(numx < whileRange){
                    world.destroyBlock(BlockPos.containing(x+numx,y+numy,z),true,entity);
                    numx++;
                }
                numy++;
                numx = numRange;
            }
        }
        else if ((entity.getDirection()) == Direction.EAST || (entity.getDirection()) == Direction.WEST) {
            numy = numRange;
            numz = numRange;
            while (numy < whileRange) {
                while (numz < whileRange) {
                    world.destroyBlock(BlockPos.containing(x, y + numy, z + numz), true, entity);
                    numz++;
                }
                numy++;
                numz = numRange;
            }
        }
    }
}
