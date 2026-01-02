package com.takoy3466.manaitamtk.item;

import com.takoy3466.manaitamtk.KeyMapping.MTKKeyMapping;
import com.takoy3466.manaitamtk.apiMTK.abstracts.AbstractMenuItem;
import com.takoy3466.manaitamtk.menu.MTKCraftingTableMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChangeableMagnificationPortableDCT extends AbstractMenuItem {
    static int modeNum = 0;
    public static int magnification = 1;

    public ChangeableMagnificationPortableDCT() {
        super(new Properties()
                .rarity(Rarity.EPIC)
        );
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.openMenu(level, player, "craft " + magnification + "x", hand);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        if (world.isClientSide){
            if(MTKKeyMapping.MagnificationKey.consumeClick()){
                modeNum = modeNum < 4 ? modeNum + 1 : 0;
                switch (modeNum){
                    case 1:
                        magnification = 4;
                        break;
                    case 2:
                        magnification = 8;
                        break;
                    case 3:
                        magnification = 16;
                        break;
                    case 4:
                        magnification = 64;
                        break;
                    case 0:
                    default:
                        magnification = 1;
                        break;
                }
                player.displayClientMessage(Component.literal("MODE : " + magnification + "x"), true);
            }
        }
    }


    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        if (modeNum > 1){
            return true;
        }else return false;
    }

    //ホバーテキストをツールに表示する
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("MODE : " + magnification + "x")
                .withStyle(ChatFormatting.WHITE)
        );
    }

    @Override
    public AbstractContainerMenu setMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new MTKCraftingTableMenu(id, inv, this.createAccess(player), magnification);
    }
}
