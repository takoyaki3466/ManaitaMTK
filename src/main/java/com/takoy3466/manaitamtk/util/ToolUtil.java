package com.takoy3466.manaitamtk.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collections;

public class ToolUtil {

    public static void spreadGrow(Level level, BlockPos pos, int radius) {
        if (!level.isClientSide()) {
            for (int x = -1* radius; x <= radius; x++) {
                for (int y = -1* radius; y <= radius; y++) {
                    for (int z = -1* radius; z <= radius; z++) {
                        BlockPos targetPos = pos.offset(x, y, z);
                        BlockState state = level.getBlockState(targetPos);

                        for (Property<?> property : state.getProperties()) {
                            if (property.getName().equals("age") && property instanceof IntegerProperty ageProperty) {
                                int currentAge = state.getValue(ageProperty);
                                int maxAge = Collections.max(ageProperty.getPossibleValues());

                                if (currentAge < maxAge) {
                                    level.setBlock(targetPos, state.setValue(ageProperty, maxAge), 2);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    // 割と初期のころに作ってずっとそのままなのでコードがずたずたです、すいません
    public static void RangeBreak(Level level, int x, int y, int z, LivingEntity livingEntity, int size) {
        BrakerClass.control(level, x, y, z, livingEntity, size);
    }
    private static class BrakerClass {

        static int x, y, z, numRange, whileRange;
        static LivingEntity entity;
        static Level world;
        static int X, Y, Z;

        public static void control(Level WORLD, int X, int Y, int Z, LivingEntity ENTITY, int I) {
            if (ENTITY == null)
                return;

            world = WORLD;
            x = X;
            y = Y;
            z = Z;
            entity = ENTITY;

            numRange = -1 * (I - 1) / 2;
            whileRange = (I + 1) / 2;
            operate(world, x, y, z, entity, numRange, whileRange);
        }

        public static void operate(Level world, int x, int y, int z, LivingEntity entity, int numRange, int whileRange) {

            if ((entity.getXRot() >= 40 && entity.getXRot() <= 90) || (entity.getXRot() <= -40 && entity.getXRot() >= -90)) {
                X = numRange;
                Z = numRange;
                while (Z < whileRange) {
                    while (X < whileRange) {
                        world.destroyBlock(BlockPos.containing(x + X, y, z + Z), true, entity);
                        X++;
                    }
                    Z++;
                    X = numRange;
                }
            } else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
                X = numRange;
                Y = numRange;
                while (Y < whileRange) {
                    while (X < whileRange) {
                        world.destroyBlock(BlockPos.containing(x + X, y + Y, z), true, entity);
                        X++;
                    }
                    Y++;
                    X = numRange;
                }
            } else if ((entity.getDirection()) == Direction.EAST || (entity.getDirection()) == Direction.WEST) {
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
}
