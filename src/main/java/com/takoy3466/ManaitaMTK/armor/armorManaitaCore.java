package com.takoy3466.ManaitaMTK.armor;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public abstract class armorManaitaCore extends ArmorItem {
    public armorManaitaCore(ArmorItem.Type type, Item.Properties properties) {
        super(new ArmorMaterial() {

            //耐久力
            @Override
            public int getDurabilityForType(ArmorItem.@NotNull Type type) {
                return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 15;
            }

            //防御
            @Override
            public int getDefenseForType(ArmorItem.@NotNull Type type) {
                return new int[]{210000000, 210000000, 210000000, 210000000}[type.getSlot().getIndex()];
            }

            //エンチャントの付きやすさ
            @Override
            public int getEnchantmentValue() {
                return 30;
            }

            //装備した時の音
            @Override
            public @NotNull SoundEvent getEquipSound() {
                return SoundEvents.EMPTY;
            }

            //修理に使える素材の設定
            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.EMPTY;
            }

            //アーマーたちの基本の名前
            @Override
            public @NotNull String getName() {
                return "armor_manaita";
            }

            //強靭さ
            @Override
            public float getToughness() {
                return 210000000f;
            }

            //耐性
            @Override
            public float getKnockbackResistance() {
                return 210000000f;
            }
        }, type, properties
                .durability(0)
                .fireResistant()
        );
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}