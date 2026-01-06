package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.IFly;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public class FlyProvider implements IFly, INBTSerializable<CompoundTag> {
    private final String IS_FLY = "isFly";
    private final String FLY_SPEED = "flySpeed";

    private boolean isFly;
    private float flySpeed;

    @Override
    public void setCanFly(boolean isFly) {
        this.isFly = isFly;
    }

    @Override
    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    @Override
    public float getFlySpeed() {
        return this.flySpeed;
    }

    @Override
    public boolean canFly() {
        return this.isFly;
    }

    @Override
    public void flySpeedChange(ItemStack stack, Player player) {
        if (this.isFly) {
            player.getAbilities().setFlyingSpeed(this.flySpeed);
            player.onUpdateAbilities();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(IS_FLY, this.isFly);
        tag.putFloat(FLY_SPEED, this.flySpeed);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.isFly = tag.getBoolean(IS_FLY);
        this.flySpeed = tag.getFloat(FLY_SPEED);
    }
}
