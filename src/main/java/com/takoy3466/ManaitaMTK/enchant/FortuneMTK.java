package com.takoy3466.ManaitaMTK.enchant;

import com.takoy3466.ManaitaMTK.regi.ManaitaMTKEnchantments;
import com.takoy3466.ManaitaMTK.regi.ManaitaMTKItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class FortuneMTK extends Enchantment {
    public FortuneMTK() {
        super(Rarity.COMMON, EnchantmentCategory.DIGGER, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getMinLevel() {
        return 10;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof DiggerItem;
    }
}
