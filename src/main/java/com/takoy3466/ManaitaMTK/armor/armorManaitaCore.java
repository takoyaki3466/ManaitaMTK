package com.takoy3466.ManaitaMTK.armor;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public abstract class armorManaitaCore extends ArmorItem {
    public armorManaitaCore(ArmorItem.Type type, Item.Properties properties) {
        super(new ArmorMaterial() {

            //耐久力
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 15;
            }

            //防御
            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return new int[]{2100000000, 2100000000, 2100000000, 2100000000}[type.getSlot().getIndex()];
            }

            //エンチャントの付きやすさ
            @Override
            public int getEnchantmentValue() {
                return 2100000000;
            }

            //装備した時の音
            @Override
            public SoundEvent getEquipSound() {
                return SoundEvents.EMPTY;
            }

            //修理に使える素材の設定
            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }

            //アーマーたちの基本の名前
            @Override
            public String getName() {
                return "armor_manaita";
            }

            //強靭さ
            @Override
            public float getToughness() {
                return 2100000000f;
            }

            //耐性
            @Override
            public float getKnockbackResistance() {
                return 2100000000f;
            }
        }, type, properties);
    }
    //ヘルメットのクラス
    public static class HelmetManaita extends armorManaitaCore {
        public HelmetManaita() {
            super(ArmorItem.Type.HELMET, new Item.Properties());
        }

        //ヘルメットをかぶった時の見た目を設定
        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
        }

        //ヘルメットをかぶってる時に飛行出来るようにする　＆　無敵にする やつを呼ぶ
        @Override
        public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
            super.inventoryTick(itemstack, world, entity, slot, selected);
            if (entity instanceof Player player) {
                FlyAndInvulnerable.FAI(entity, player);
            }
        }
    }

    //チェストプレートのクラス
    public static class ChestplateManaita extends armorManaitaCore {
        public ChestplateManaita() {
            super(ArmorItem.Type.CHESTPLATE, new Item.Properties());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
        }
    }

    //レギンスのクラス
    public static class LeggingsManaita extends armorManaitaCore {
        public LeggingsManaita() {
            super(ArmorItem.Type.LEGGINGS, new Item.Properties());
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
        }
    }


    //ブーツのクラス
    public static class BootsManaita extends armorManaitaCore {
        public BootsManaita() {
            super(ArmorItem.Type.BOOTS, new Item.Properties());
        }


        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
        }
    }
}