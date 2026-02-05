package com.takoy3466.manaitamtk.api.criterionTrigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class MTKOpenTrigger extends SimpleCriterionTrigger<MTKOpenTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation("mtk_open");
    public static final String PATH = "screen";

    public MTKOpenTrigger() {

    }

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        String screenId = jsonObject.get(PATH).getAsString();
        return new TriggerInstance(contextAwarePredicate, screenId);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayer, String screenId) {
        this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(screenId));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String screenId;

        public TriggerInstance(ContextAwarePredicate contextAwarePredicate, String screenId) {
            super(MTKOpenTrigger.ID, contextAwarePredicate);
            this.screenId = screenId;
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.addProperty(PATH, this.screenId);
            return jsonObject;
        }

        public boolean matches(String screenId) {
            return this.screenId.equals(screenId);
        }
    }
}
