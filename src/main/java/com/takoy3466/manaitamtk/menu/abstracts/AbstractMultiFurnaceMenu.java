package com.takoy3466.manaitamtk.menu.abstracts;

import com.takoy3466.manaitamtk.core.helper.MTKMenuHelper;
import com.takoy3466.manaitamtk.core.interfaces.IMTKFurnace;
import com.takoy3466.manaitamtk.core.interfaces.IOverlay;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import com.takoy3466.manaitamtk.block.blockEntity.abstracts.AbstractMultiFurnaceBlockEntity;
import com.takoy3466.manaitamtk.network.MTKNetwork;
import com.takoy3466.manaitamtk.network.PacketOverlay;
import com.takoy3466.manaitamtk.util.slot.MTKFurnaceFuelSlot;
import com.takoy3466.manaitamtk.util.slot.MTKSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;

import java.util.List;

public abstract class AbstractMultiFurnaceMenu extends AbstractContainerMenu implements IOverlay {
    private final AbstractMultiFurnaceBlockEntity blockEntity;
    private final Level level;
    private final BlockPos pos;
    public final IMTKFurnace blast, smoker;

    public AbstractMultiFurnaceMenu(MenuType<?> type, int id, Inventory inv, FriendlyByteBuf buf) {
        this(type, id, inv, buf.readBlockPos());
    }

    public AbstractMultiFurnaceMenu(MenuType<?> type, int id, Inventory inv, BlockPos pos) {
        super(type, id);
        BlockEntity be = inv.player.level().getBlockEntity(pos);
        if (be instanceof AbstractMultiFurnaceBlockEntity multiFurnaceBlockEntity){
            this.blockEntity = multiFurnaceBlockEntity;
        } else {
            throw new IllegalStateException(be.getClass().getCanonicalName() + "と AutoWorkbenchMTKBlockEntity クラスは違うよ！");
        }

        this.level = inv.player.level();
        this.pos = pos;
        this.blast = blockEntity.blast;
        this.smoker = blockEntity.smoker;


        this.addSlot(new Slot(blockEntity, 0, 31, 17));
        this.addSlot(new Slot(blockEntity, 3, 56, 17));

        this.addSlot(new MTKFurnaceFuelSlot(this, blockEntity, 1, 31, 53));
        this.addSlot(new MTKFurnaceFuelSlot(this, blockEntity, 4, 56, 53));

        this.addSlot(new FurnaceResultSlot(inv.player, blockEntity, 2, 116, 21));
        this.addSlot(new FurnaceResultSlot(inv.player, blockEntity, 5, 116, 49));

        this.addDataSlots(blockEntity.smoker.getContainerData());
        this.addDataSlots(blockEntity.blast.getContainerData());

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new MTKSlot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new MTKSlot(inv, i, 8 + i * 18, 142));
        }
    }

    public BlockPos getPos() {
        return pos;
    }

    /**
     *
     * @param id 登録した準のid
     * @return クリック前のコピーを返すか動かした後のEMPTYを返すか
     */
    @Override
    public ItemStack quickMoveStack(Player player, int id) {
        /*
        0: blast 材料 2: blast 燃料 4: blast 結果
        1: smoker 材料 3: smoker 燃料 5: smoker 結果
         */
        int BLAST_IN = 0;
        int BLAST_FUEL = 2;
        int BLAST_RESULT = 4;
        int SMOKER_IN = 1;
        int SMOKER_FUEL = 3;
        int SMOKER_RESULT = 5;
        int INV_START = 6;
        int INV_END = 32;
        int HOTBAR_START = 33;
        int HOTBAR_END = 41;
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(id);
        if (slot.hasItem()) {
            ItemStack targetStack = slot.getItem();
            copy = targetStack.copy();

            if (id == 4 || id == 5) {
                if (!this.moveItemStackTo(targetStack, INV_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(targetStack, copy);
            } else if (id == 0 || id == 1 || id == 2 || id == 3) {
                if (!this.moveItemStackTo(targetStack, INV_START, HOTBAR_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.isFuel(targetStack)) {
                    if (!this.moveItemStackTo(targetStack, BLAST_FUEL, SMOKER_FUEL + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.canSmelt(targetStack)) {
                    if (blockEntity.getRecipeTypeFromStack(targetStack) == RecipeType.BLASTING) {
                        if (!this.moveItemStackTo(targetStack, BLAST_IN, BLAST_RESULT + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (blockEntity.getRecipeTypeFromStack(targetStack) == RecipeType.SMOKING) {
                        if (!this.moveItemStackTo(targetStack, SMOKER_IN, SMOKER_IN + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                } else if (id >= INV_START && id <= INV_END) {
                    if (!this.moveItemStackTo(targetStack, HOTBAR_START, HOTBAR_END + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (id >= HOTBAR_START && id <= HOTBAR_END) {
                    if (!this.moveItemStackTo(targetStack, INV_START, INV_END + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (targetStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (targetStack.getCount() == copy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, targetStack);
        }

        return copy;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack stack, int i, int i1, boolean b) {
        return MTKMenuHelper.moveItemStackTo(this.slots, stack, i, i1, b);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
    }

    public boolean canSmelt(ItemStack stack) {
        RecipeType<?> recipeType = blockEntity.getRecipeTypeFromStack(stack);
        if (recipeType == RecipeType.BLASTING) {
            return this.level.getRecipeManager().getRecipeFor(RecipeType.BLASTING, new SimpleContainer(stack), this.level).isPresent();
        } else if (recipeType == RecipeType.SMOKING) {
            return this.level.getRecipeManager().getRecipeFor(RecipeType.SMOKING, new SimpleContainer(stack), this.level).isPresent();
        }
        return false;
    }

    public boolean isFuel(ItemStack stack) {
        return isFuel(stack, RecipeType.BLASTING) || isFuel(stack, RecipeType.SMOKING);
    }

    public boolean isFuel(ItemStack stack, RecipeType recipeType) {
        return ForgeHooks.getBurnTime(stack, recipeType) > 0;
    }

    public AbstractMultiFurnaceBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public List<MTKOverlayIcon> getIconList() {
        return blockEntity.getIconList();
    }

    @Override
    public void setIconList(List<MTKOverlayIcon> iconList) {
        MTKNetwork.sendToServer(new PacketOverlay(iconList, pos));
    }
}
