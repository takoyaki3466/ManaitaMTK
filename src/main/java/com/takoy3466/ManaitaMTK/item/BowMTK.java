package com.takoy3466.ManaitaMTK.item;

import com.takoy3466.ManaitaMTK.entity.EntityArrowMTK;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BowMTK extends BowItem {

    public BowMTK() {
        super(new Properties()
                .fireResistant()
                .rarity(Rarity.COMMON)
                .stacksTo(1)
        );
    }

    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            ItemStack itemstack = Items.AIR.getDefaultInstance();

            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) {
                return;
            }

            float f = getPowerForTime(i);
            if (!((double) f < 0.1)) {
                if (!world.isClientSide) {
                    AbstractArrow abstractarrow = new EntityArrowMTK(world, entity);
                    abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 30.0F, 0.0F);//射角,弾速,ブレ度
                    if (f == 1.0F) {
                        abstractarrow.setCritArrow(true);
                    }
                    int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                    if (j > 0) {
                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5 + 0.5);
                    }
                    int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                    if (k > 0) {
                        abstractarrow.setKnockback(k);
                    }
                    if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                        abstractarrow.setSecondsOnFire(100);
                    }
                    stack.hurtAndBreak(1, player, (p_289501_) -> {
                        p_289501_.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    world.addFreshEntity(abstractarrow);
                }
                world.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    public static float getPowerForTime(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getUseDuration(ItemStack stack) {
        return 720;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
    }
    
    public int getDefaultProjectileRange() {
        return 15;
    }
}
