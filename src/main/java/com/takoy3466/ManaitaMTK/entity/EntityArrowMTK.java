package com.takoy3466.ManaitaMTK.entity;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityArrowMTK extends AbstractArrow {
    int tick = 0;

    public EntityArrowMTK(EntityType type, Level level) {
        super(type, level);
        this.setBaseDamage(this.getBaseDamage() + 2.0d);
    }

    public EntityArrowMTK(Level level, LivingEntity entity) {
        super((EntityType) ManaitaMTKEntities.MTK_ARROW.get(), entity, level);
        this.setBaseDamage(this.getBaseDamage() + 210000000d);
        this.pickup = Pickup.DISALLOWED;
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        this.tick++;
        if (!level().isClientSide && this.tick > 20 * 30) {
            this.discard();
        }
    }
}
