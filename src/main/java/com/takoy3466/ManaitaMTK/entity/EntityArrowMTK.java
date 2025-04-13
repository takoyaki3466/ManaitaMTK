package com.takoy3466.ManaitaMTK.entity;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EntityArrowMTK extends AbstractArrow {

    private ItemStack ItemA = new ItemStack(Items.AIR);

    public EntityArrowMTK(EntityType type, Level level) {
        super(type, level);
        this.setBaseDamage(this.getBaseDamage() + 2.0d);
    }

    public EntityArrowMTK(Level level, LivingEntity entity, ItemStack stack) {
        super((EntityType) ManaitaMTKEntities.MTK_ARROW.get(), entity, level);
        this.setBaseDamage(this.getBaseDamage() + 210000000d);
        ItemA = stack;
    }

    public EntityArrowMTK(Level level, double x, double y, double z) {
        super((EntityType) ManaitaMTKEntities.MTK_ARROW.get(), x, y, z, level);
        this.setBaseDamage(this.getBaseDamage() + 210000000d);
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return ItemA;
    }
}
