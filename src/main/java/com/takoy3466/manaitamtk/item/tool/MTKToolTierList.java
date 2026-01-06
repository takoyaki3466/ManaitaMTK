package com.takoy3466.manaitamtk.item.tool;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum MTKToolTierList implements Tier {
    MTK_TIER(0, 2147483647f, 2147483747f, 2147483647, 2147483647, Ingredient.of());

    private final int uses, level, enchantmentValue;
    private final float speed, attackDamage;
    private final Ingredient repaierIngredient;

    MTKToolTierList(int uses, float speed, float attackDamage, int level, int enchantmentValue, Ingredient repaierIngredient){
        this.uses = uses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repaierIngredient = repaierIngredient;
    }
    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repaierIngredient;
    }
}
