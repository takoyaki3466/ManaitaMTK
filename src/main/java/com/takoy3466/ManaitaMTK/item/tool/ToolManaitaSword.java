package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class ToolManaitaSword extends SwordItem {

    public ToolManaitaSword() {
        super(MTKTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }
}
