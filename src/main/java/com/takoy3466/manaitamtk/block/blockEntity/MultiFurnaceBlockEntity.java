package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.api.abstracts.BaseContainerBlockEntityMultipler;
import com.takoy3466.manaitamtk.api.interfaces.IMTKFurnace;
import com.takoy3466.manaitamtk.api.interfaces.ITickableBlockEntity;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import com.takoy3466.manaitamtk.menu.MultiFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultiFurnaceBlockEntity extends BaseContainerBlockEntityMultipler implements WorldlyContainer, RecipeHolder, StackedContentsCompatible, ITickableBlockEntity {
    /*
    0: blast 材料 1: blast 燃料 2: blast 結果
    3: smoker 材料 4: smoker 燃料 5: smoker 結果
    UP: 入力 DOWN: 結果出力 SIDE: 燃料
     */
    private static final int[] SLOTS_FOR_UP = new int[]{0, 3};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 5};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1, 4};

    private LazyOptional<? extends IItemHandler> sorter;
    private LazyOptional<? extends IItemHandler>[] handlers;

    public final IMTKFurnace blast, smoker;

    public MultiFurnaceBlockEntity(BlockPos pos, BlockState state, MTKTier mtkTier) {
        super(BlockEntitiesInit.WOOD_MULTI_FURNACE.get(), pos, state, mtkTier);

        this.blast = new MTKFurnace(this, mtkTier, RecipeType.BLASTING);
        this.smoker = new MTKFurnace(this, mtkTier, RecipeType.SMOKING);

        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

        this.sorter = LazyOptional.of(this::getSorter);
    }

    public IItemHandler getSorter() {
        return new InvWrapper(this){
            // 0-2:blast, 3-5:smoker
            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                RecipeType<?> recipeType = getRecipeTypeFromStack(stack);

                if (recipeType == RecipeType.BLASTING) {
                    return insertInto(0, stack, simulate);
                }

                if (recipeType == RecipeType.SMOKING) {
                    return insertInto(3, stack, simulate);
                }

                return stack;
            }

            private ItemStack insertInto(int slot, ItemStack stack, boolean simulate) {
                ItemStack existing = getItem(slot);
                if (!existing.isEmpty() && !ItemStack.isSameItemSameTags(existing, stack)) {
                    return stack;
                }

                int space = existing.isEmpty()
                        ? stack.getMaxStackSize()
                        : existing.getMaxStackSize() - existing.getCount();

                if (space <= 0) return stack;

                int toMove = Math.min(space, stack.getCount());
                if (!simulate) {
                    if (existing.isEmpty()) {
                        setItem(slot, stack.split(toMove));
                    } else {
                        existing.grow(toMove);
                    }
                    setChanged();
                }

                ItemStack remaining = stack.copy();
                remaining.shrink(toMove);
                return remaining;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("test");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new MultiFurnaceMenu(i, inventory, getBlockPos());
    }

    @Override
    public void serverTick() {
        blast.tick();
        smoker.tick();
    }

    @Override
    public int getContainerSize() {
        return blast.getItems().size() + smoker.getItems().size();
    }

    @Override
    public boolean isEmpty() {
        return blast.isEmpty() && smoker.isEmpty();
    }

    @Override
    public ItemStack getItem(int slotId) {
        if (slotId < 3) {
            return blast.getItem(slotId);
        }else {
            return smoker.getItem(slotId - 3);
        }
    }

    @Override
    public ItemStack removeItem(int slotId, int count) {
        if (slotId < 3) {
            return ContainerHelper.removeItem(blast.getItems(), slotId, count);
        }else {
            return ContainerHelper.removeItem(smoker.getItems(), slotId - 3, count);
        }
    }

    @Override
    public ItemStack removeItemNoUpdate(int slotId) {
        if (slotId < 3) {
            return ContainerHelper.takeItem(blast.getItems(), slotId);
        }else {
            return ContainerHelper.takeItem(smoker.getItems(), slotId - 3);
        }
    }

    @Override
    public void setItem(int slotId, ItemStack stack) {
        if (slotId < 3) {
            blast.setItem(slotId, stack);
        }else {
            smoker.setItem(slotId - 3, stack);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.blast.getItems().clear();
        this.smoker.getItems().clear();
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(i, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && i == 1) {
            return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
        } else {
            return true;
        }
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            if (recipe.getType() == blast.getRecipeType()) {
                blast.setRecipesUsed(recipe);
            } else if (recipe.getType() == smoker.getRecipeType()) {
                smoker.setRecipesUsed(recipe);
            }
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        blast.fillStackedContents(stackedContents);
        smoker.fillStackedContents(stackedContents);
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> list) {
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @javax.annotation.Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            LazyOptional lazyOptional;
            switch (facing) {
                case UP -> lazyOptional = this.sorter.cast();
                case DOWN -> lazyOptional = this.handlers[1].cast();
                default -> lazyOptional = this.handlers[2].cast();
            }

            return lazyOptional;
        } else {
            return super.getCapability(capability, facing);
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();

        sorter.invalidate();
        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }

    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        sorter = LazyOptional.of(this::getSorter);
        handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }

    public RecipeType<?> getRecipeTypeFromStack(ItemStack ingredientStack) {
        RecipeType<?> recipeTypeBlast = blast.getRecipeTypeFromStack(ingredientStack);
        RecipeType<?> recipeTypeSmoker = smoker.getRecipeTypeFromStack(ingredientStack);
        if (recipeTypeBlast != null) {
            return recipeTypeBlast;
        } else if (recipeTypeSmoker != null) {
            return recipeTypeSmoker;
        }else {
            return null;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        blast.saveAdditional(tag);
        smoker.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        blast.load(tag);
        smoker.load(tag);
    }
}
