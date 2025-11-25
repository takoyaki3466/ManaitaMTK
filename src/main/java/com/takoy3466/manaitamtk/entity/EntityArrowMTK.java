package com.takoy3466.manaitamtk.entity;

import com.takoy3466.manaitamtk.init.EntitiesInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityArrowMTK extends AbstractArrow {
    private int tick = 0;
    private final double baseDamage = 2147483647d;

    @SuppressWarnings("unchecked")
    public EntityArrowMTK(EntityType type, Level level) {
        super(type, level);
        this.setBaseDamage(baseDamage);
    }

    @SuppressWarnings("unchecked")
    public EntityArrowMTK(Level level, LivingEntity entity) {
        super((EntityType) EntitiesInit.MTK_ARROW.get(), entity, level);
        this.setBaseDamage(baseDamage);
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
        if (!level().isClientSide && this.tick > 20 * 10) {
            this.discard();
        }
    }
}
