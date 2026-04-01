package com.takoy3466.manaitamtk.criterionTrigger;

import com.google.gson.JsonObject;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class MTKTrigger extends SimpleCriterionTrigger<MTKTrigger.TriggerInstance> {
    public static String CRAFT_IN_BREAK_CRAFTING_TABLE = "craft_in_break_crafting_table";



    public static final ResourceLocation ID = new ResourceLocation(ManaitaMTK.MOD_ID, "mtk");
    public static final String PATH = "mtk";

    public MTKTrigger() {
    }

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        String target = jsonObject.get(PATH).getAsString();
        return new TriggerInstance(contextAwarePredicate, target);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayer, String target) {
        this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(target));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String target;

        public TriggerInstance(ContextAwarePredicate predicate, String target) {
            super(ID, predicate);
            this.target = target;
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.addProperty(PATH, target);
            return jsonObject;
        }

        public boolean matches(String target) {
            return this.target.matches(target);
        }
    }
}
