package com.takoy3466.manaitamtk.api.capability.provider;

import com.takoy3466.manaitamtk.api.capability.interfaces.IFly;
import net.minecraft.nbt.CompoundTag;

public class FlyProvider implements IFly {
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
        if (this.flySpeed == 0) {
            this.flySpeed = 0.05f;
        }
        return this.flySpeed;
    }

    @Override
    public boolean canFly() {
        return this.isFly;
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
