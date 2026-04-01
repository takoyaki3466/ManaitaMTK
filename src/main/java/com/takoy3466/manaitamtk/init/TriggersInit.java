package com.takoy3466.manaitamtk.init;

import com.takoy3466.manaitamtk.criterionTrigger.ScreenOpenTrigger;
import com.takoy3466.manaitamtk.criterionTrigger.MTKTrigger;
import com.takoy3466.manaitamtk.criterionTrigger.RightClickTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TriggersInit {

    public static final ScreenOpenTrigger MTK_OPEN_TRIGGER = new ScreenOpenTrigger();
    public static final RightClickTrigger RIGHT_CLICK_TRIGGER = new RightClickTrigger();
    public static final MTKTrigger MTK_TRIGGER = new MTKTrigger();

    public static void init() {
        CriteriaTriggers.register(MTK_OPEN_TRIGGER);
        CriteriaTriggers.register(RIGHT_CLICK_TRIGGER);
        CriteriaTriggers.register(MTK_TRIGGER);
    }
}
