package com.takoy3466.ManaitaMTK.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LeggingsManaita extends armorManaitaCore {
    public LeggingsManaita() {
        super(ArmorItem.Type.LEGGINGS, new Item.Properties());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "manaitamtk:textures/models/armor/armor_manaita_layer.png";
    }
}