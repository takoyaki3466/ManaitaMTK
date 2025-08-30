package com.takoy3466.manaitamtk.item.tool;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.ManaitaMTK;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.item.Item;

import java.util.List;

public class ToolManaitaPaxel extends TieredItem {
    private final ResourceLocation ID = new ResourceLocation(ManaitaMTK.MOD_ID, "manaita_paxel_open_screen");
    public static int range;
    private boolean isDone = false;
    private boolean isDown;

    public ToolManaitaPaxel() {
        super(MTKTierList.MTK_TIER, new Item.Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.DIGGER;
    }

    //適正ツールの設定
    @Override
    public boolean isCorrectToolForDrops(BlockState blockstate) {
        return true;
    }

    //採掘速度の設定
    @Override
    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
        return 2147483647f;
    }

    //範囲破壊呼び出し
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        if (range == 1 || range == 0) {
            return super.mineBlock(stack, world, blockstate, pos, entity);
        }else {
            RangeBreak.control(world, pos.getX(), pos.getY(), pos.getZ(), entity, range);
            return true;
        }
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (this.isDone) return;
        if (!this.isDown) {
            if (MTKKeyMapping.MTKSwitcherOpenKey.isDown()) {
                this.isDown = true;
            }
        }
        if (this.isDown) {
            if (!player.level().isClientSide) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(this.ID);
                if (advancement != null) {
                    AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) {
                        for (String criteria : progress.getRemainingCriteria()) {
                            serverPlayer.getAdvancements().award(advancement, criteria);
                            this.isDone = true;
                        }
                    }else if (!this.isDown) {
                        this.isDown = true;
                    }
                }
            }
        }
    }

    public void getRangeTag(ItemStack stack) {
        // NBTから "Range" を読み取る (なければ1を読み込む)
        CompoundTag tag = stack.getOrCreateTag();
        range = tag.getInt("Range");
        if (range <= 0) range = 1;
    }

    //ピカピカさせる
    @Override
    public boolean isFoil(ItemStack stack) {
        getRangeTag(stack);
        return range != 1;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        getRangeTag(stack);
        list.add(Component.literal("MODE : " + range + " x " + range)
                .withStyle(ChatFormatting.GRAY));
    }
}