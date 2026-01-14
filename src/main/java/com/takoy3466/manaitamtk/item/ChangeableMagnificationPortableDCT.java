package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMappings;
import com.takoy3466.manaitamtk.api.capability.interfaces.IMultiple;
import com.takoy3466.manaitamtk.api.capability.provider.MultipleProvider;
import com.takoy3466.manaitamtk.api.interfaces.IHasCapability;
import com.takoy3466.manaitamtk.api.capability.MTKCapabilities;
import com.takoy3466.manaitamtk.api.interfaces.IHasMenuProvider;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.MTKTiers;
import com.takoy3466.manaitamtk.menu.MTKCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChangeableMagnificationPortableDCT extends Item implements IHasCapability, IHasMenuProvider {
    static int modeNum = 0;

    public ChangeableMagnificationPortableDCT() {
        super(new Properties()
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.openMenu(level, player, "craft " + getMagnification(player.getItemInHand(hand)) + "x", hand);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        if (world.isClientSide){
            if(MTKKeyMappings.MagnificationKey.consumeClick()){
                modeNum = modeNum < 4 ? modeNum + 1 : 0;
                switch (modeNum){
                    case 1:
                        setMagnification(stack, 4);
                        break;
                    case 2:
                        setMagnification(stack, 8);
                        break;
                    case 3:
                        setMagnification(stack, 16);
                        break;
                    case 4:
                        setMagnification(stack, 64);
                        break;
                    case 0:
                    default:
                        setMagnification(stack, 1);
                        break;
                }
                player.displayClientMessage(Component.literal("MODE : " + getMagnification(stack) + "x"), true);
            }
        }
    }

    public int getMagnification(ItemStack stack) {
        LazyOptional<IMultiple> lazyOptional = this.getLazyOptional(MTKCapabilities.MULTIPLE, stack);
        if (this.isUsage(lazyOptional)) {
            return this.getInterface(lazyOptional).getMultiple();
        }
        return 1;
    }

    public void setMagnification(ItemStack stack, int magnification) {
        LazyOptional<IMultiple> lazyOptional = this.getLazyOptional(MTKCapabilities.MULTIPLE, stack);
        if (this.isUsage(lazyOptional)) {
            this.execute(lazyOptional, iMultiple -> iMultiple.setMultiple(magnification));
        }
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return this.setCapability(MTKCapabilities.MULTIPLE, MultipleProvider::new);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return getMagnification(stack) > 1;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("MODE : " + getMagnification(stack) + "x")
                .withStyle(ChatFormatting.WHITE)
        );
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new MTKCraftingTableMenu(id, inv, this.createAccess(player), getMTKTier(getMagnification(stack)));
    }

    private MTKTier getMTKTier(int multiple) {
        return switch (multiple) {
            case 2 -> MTKTiers.WOOD;
            case 4 -> MTKTiers.STONE;
            case 8 -> MTKTiers.IRON;
            case 16 -> MTKTiers.GOLD;
            case 32 -> MTKTiers.DIAMOND;
            case 64 -> MTKTiers.MTK;
            case 512 -> MTKTiers.GODMTK;
            case 33554431 -> MTKTiers.BREAK;
            default -> MTKTiers.WOOD;
        };
    }
}
