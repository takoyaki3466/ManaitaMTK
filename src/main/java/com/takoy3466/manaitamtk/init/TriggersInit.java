package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.api.criterionTrigger.MTKOpenTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TriggersInit {

    public static final MTKOpenTrigger MTK_OPEN_TRIGGER = new MTKOpenTrigger();

    public static void init() {
        CriteriaTriggers.register(MTK_OPEN_TRIGGER);
    }
}
