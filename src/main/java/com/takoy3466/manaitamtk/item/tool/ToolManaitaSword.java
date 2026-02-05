package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMappings;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.api.capability.interfaces.IKillSword;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.capability.provider.MTKSwordProvider;
import com.takoy3466.manaitamtk.api.interfaces.ISimpleCapability;
import com.takoy3466.manaitamtk.api.interfaces.IUseTag;
import com.takoy3466.manaitamtk.network.MTKNetwork;
import com.takoy3466.manaitamtk.network.PacketisKillAll;
import com.takoy3466.manaitamtk.util.WeaponUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolManaitaSword extends SwordItem implements ISimpleCapability<IKillSword>, IUseTag {
    private final Component SWORD_TEXT_ENEMY = Component.translatable("gui.overlay.sword.enemy_die").withStyle(ChatFormatting.WHITE);
    private final Component SWORD_TEXT_ALL = Component.translatable("gui.overlay.sword.all_die").withStyle(ChatFormatting.RED);
    private final Component MODE = Component.translatable("item.manaitamtk.manaita_sword.hover_text_mode");
    private final Component KEY = Component.literal("Press Shift + ");

    public ToolManaitaSword() {
        super(MTKToolTierList.MTK_TIER, 1, 2147483647f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity player) {
        WeaponUtil.die(target);
        return super.hurtEnemy(stack, target, player);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity target) {
            WeaponUtil.die(target);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        if (!(entity instanceof Player player)) {
            return;
        }
        if (level.isClientSide()) {
            if (MTKKeyMappings.SwitchExterminationKey.consumeClick() && player.isSteppingCarefully()) {
                this.execute(MTKCapabilities.KILL_SWORD, stack, iKillSword -> {
                    iKillSword.setIsKillAll(!iKillSword.isKillAll());
                    MTKNetwork.sendToServer(new PacketisKillAll(iKillSword.isKillAll()));
                });

            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.manaitamtk.manaita_sword_hover_text")
                .withStyle(ChatFormatting.GRAY));
        list.add(Component.literal(this.KEY.getString() + MTKKeyMappings.SwitchExterminationKey.getKey().getDisplayName().getString()));

        execute(stack, iKillSword -> {
            if (iKillSword.isKillAll()) {
                list.add(Component.literal(this.MODE.getString() + this.SWORD_TEXT_ALL.getString()));
            } else list.add(Component.literal(this.MODE.getString() + this.SWORD_TEXT_ENEMY.getString()));
        });
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.setCapability(() -> {
            MTKSwordProvider provider = new MTKSwordProvider();
            if (isContains(stack)) {
                provider.deserializeNBT(getTag(stack));
            }
            return provider;
        });
    }

    @Override
    public Capability<IKillSword> getCapability() {
        return MTKCapabilities.KILL_SWORD;
    }

    @Nullable
    @Override
    public String getPath() {
        return ManaitaMTK.MOD_ID;
    }
}
