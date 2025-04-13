package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;

public class ToolManaitaSword extends SwordItem {

    public ToolManaitaSword() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
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

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 1, 2100000000f, new Item.Properties().fireResistant());
    }
}
