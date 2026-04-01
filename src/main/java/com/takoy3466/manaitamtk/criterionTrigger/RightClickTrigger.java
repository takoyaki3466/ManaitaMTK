package com.takoy3466.manaitamtk.criterionTrigger;

import com.google.gson.JsonObject;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class RightClickTrigger extends SimpleCriterionTrigger<RightClickTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation(ManaitaMTK.MOD_ID, "right_click");
    public static final String PATH = "item";

    public RightClickTrigger() {
    }

    public ResourceLocation getId() {
        return ID;
    }

    public RightClickTrigger.TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate predicate, DeserializationContext context) {
        ItemPredicate item = ItemPredicate.fromJson(jsonObject.get("item"));
        return new RightClickTrigger.TriggerInstance(predicate, item);
    }

    public void trigger(ServerPlayer serverPlayer, ItemStack stack) {
        this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(stack));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public TriggerInstance(ContextAwarePredicate predicate, ItemPredicate item) {
            super(ID, predicate);
            this.item = item;
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add(PATH, this.item.serializeToJson());
            return jsonObject;
        }

        public boolean matches(ItemStack stack) {
            return this.item.matches(stack);
        }
    }
}
