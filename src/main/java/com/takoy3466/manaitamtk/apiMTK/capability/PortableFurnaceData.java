package com.takoy3466.manaitamtk.apiMTK.capability;

import com.takoy3466.manaitamtk.apiMTK.capability.interfaces.IPortableFurnace;
import net.minecraft.world.inventory.ContainerData;

public class PortableFurnaceData implements ContainerData {
    private final IPortableFurnace<?> portableFurnace;

    public PortableFurnaceData (IPortableFurnace<?> portableFurnace) {
        this.portableFurnace = portableFurnace;
    }
    @Override
    public int get(int i) {
        return switch (i) {
            case 0 -> portableFurnace.getLitTime();
            case 1 -> portableFurnace.getLitDuration();
            case 2 -> portableFurnace.getCookingProgress();
            case 3 -> portableFurnace.getCookTimeTotal();
            default -> 0;
        };
    }

    @Override
    public void set(int i, int i1) {
        switch (i) {
            case 0 -> portableFurnace.setLitTime(i1);
            case 1 -> portableFurnace.setLitDuration(i1);
            case 2 -> portableFurnace.setCookingProgress(i1);
            case 3 -> portableFurnace.setCookingTotalTime(i1);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
