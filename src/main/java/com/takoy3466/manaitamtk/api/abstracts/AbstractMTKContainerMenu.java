package com.takoy3466.manaitamtk.api.abstracts;

import com.takoy3466.manaitamtk.util.slot.MTKSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMTKContainerMenu extends AbstractContainerMenu {

    protected AbstractMTKContainerMenu(@Nullable MenuType<?> type, int id) {
        super(type, id);
    }

    protected void addPlayerInv(Inventory inv) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new MTKSlot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new MTKSlot(inv, i, 8 + i * 18, 142));
        }
    }
}
