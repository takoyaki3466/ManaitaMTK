package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class ToolManaitaHoe extends HoeItem {
    public ToolManaitaHoe() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            @Override
            public float getSpeed() {
                return 2100000000f;
            }

            public float getDestroySpeed() {
                return 2100000000f;
            }

            public float getAttackDamageBonus() {
                return 210000000000000000000000000f;
            }

            public int getLevel() {
                return 2100000000;
            }

            public int getEnchantmentValue() {
                return 2100000000;
            }

            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 2100000000, 2100000000f, new Item.Properties().fireResistant());
    }
}
