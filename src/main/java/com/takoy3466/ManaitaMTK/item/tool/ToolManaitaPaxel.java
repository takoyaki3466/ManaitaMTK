package com.takoy3466.ManaitaMTK.item.tool;

import com.takoy3466.ManaitaMTK.item.tool.rangebreak.RangeBreakControl;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EquipmentSlot;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

import java.util.List;

public class ToolManaitaPaxel extends TieredItem {
    public ToolManaitaPaxel() {
        super(new Tier() {
            public int getUses() {
                return 0;
            }

            public float getSpeed() {
                return 2100000000f;
            }

            public float getAttackDamageBonus() {
                return 210000000000000000000000000f;
            }

            public int getLevel() {
                return 2100000000;
            }

            public int getEnchantmentValue() {
                return 2100000000;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, new Item.Properties().fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.DIGGER || super.canApplyAtEnchantingTable(stack, enchantment);
    }

    //適正ツールの設定
    @Override
    public boolean isCorrectToolForDrops(BlockState blockstate) {

        return !(!blockstate.is(BlockTags.MINEABLE_WITH_AXE)
                && !blockstate.is(BlockTags.MINEABLE_WITH_HOE)
                && !blockstate.is(BlockTags.MINEABLE_WITH_PICKAXE)
                && !blockstate.is(BlockTags.MINEABLE_WITH_SHOVEL));
    }

    //採掘速度の設定
    @Override
    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
        return 21000000000000000f;
    }

    //攻撃、攻撃速度を追加してアイテムに表示させる
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 21000000000000000000000000000000000000f, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", 2100000000, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }

    //範囲破壊呼び出し
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        boolean retrieval = super.mineBlock(stack, world, blockstate, pos, entity);

            RangeBreakControl.control(world, pos.getX(), pos.getY(), pos.getZ(), entity, modeNumber(stack));

        return retrieval;
    }

    //トリガー(変え方)の設定
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isSteppingCarefully()){
            player.startUsingItem(hand);
            world.playSound(player, player, SoundEvents.WOODEN_DOOR_OPEN, SoundSource.PLAYERS, 1.0F, 1.0F);
            modeChange(stack);
            player.displayClientMessage(Component.literal("MODE :" + modeName(stack)),true);
        }
        return InteractionResultHolder.consume(stack);
    }

    //ピカピカさせる
    @Override
    public boolean isFoil(ItemStack stack) {
        if (modeNumber(stack)==0){
            return false;
        }
        return true;
    }

    //modeNumberをころころ変えてTAGに設定する
    private void modeChange(ItemStack stack) {

        if (stack.getTag() == null) {
            stack.setTag(new CompoundTag());
        }
        stack.getTag().putInt("mode",modeNumber(stack) < 4 ? modeNumber(stack) + 1 : 0);
    }

    //modeNumberを作成
    public int modeNumber (ItemStack stack) {

        if(stack.getTag() == null){
            return 0;
        }
        return stack.getTag().getInt("mode");
    }

    //それぞれのモードに名前割り振り
    private String modeName(ItemStack stack){
        return switch (modeNumber(stack)){
            case 0 -> "Normal";
            case 1 -> "3 * 3";
            case 2 -> "5 * 5";
            case 3 -> "7 * 7";
            case 4 -> "9 * 9";
            default -> "nothing";
        };
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("MODE : " + modeName(stack))
                        .withStyle(ChatFormatting.WHITE)
        );
    }
}