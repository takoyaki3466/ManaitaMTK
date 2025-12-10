package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaSword extends SwordItem {
    private final Component SWORD_TEXT_ENEMY = Component.translatable("gui.overlay.sword.enemy_die").withStyle(ChatFormatting.WHITE);
    private final Component SWORD_TEXT_ALL = Component.translatable("gui.overlay.sword.all_die").withStyle(ChatFormatting.RED);
    private final Component MODE = Component.translatable("item.manaitamtk.manaita_sword.hover_text_mode");
    private final Component KEY = Component.literal("Press Shift + ");
    public static int modeNumber = 0;

    public ToolManaitaSword() {
        super(MTKTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity player) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK);
        DamageSource source = new DamageSource(holder);
        // 攻撃された敵を即死させる
        if (!entity.level().isClientSide()) {
            entity.setHealth(0.0f);
            if (!entity.isDeadOrDying()) {
                entity.hurt(source, Float.MAX_VALUE);
                entity.die(source);
            }
        }
        return super.hurtEnemy(stack, entity, player);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC);
        DamageSource source = new DamageSource(holder);
        if (entity instanceof LivingEntity target) {
            target.setInvulnerable(false);
            target.setHealth(0.0f);

            if (!target.isDeadOrDying()) {
                target.hurt(source, Float.MAX_VALUE);
                target.die(source);
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        if (!(entity instanceof Player player)) return;
        if (level.isClientSide()) {
            if (MTKKeyMapping.SwitchExtermination.consumeClick() && player.isSteppingCarefully()) {
                this.modeChange();
            }
        }
    }

    private void modeChange() {
        modeNumber = modeNumber < 1 ? modeNumber + 1 : 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.manaita_sword_hover_text")
                .withStyle(ChatFormatting.GRAY));
        list.add(Component.literal(this.KEY.getString() + MTKKeyMapping.SwitchExtermination.getKey().getDisplayName().getString()));

        if (modeNumber == 1) {
            list.add(Component.literal(this.MODE.getString() + this.SWORD_TEXT_ALL.getString()));
        } else list.add(Component.literal(this.MODE.getString() + this.SWORD_TEXT_ENEMY.getString()));
    }
}
