package com.takoy3466.manaitamtk.block.blockEntity.abstracts;

import com.mojang.serialization.DataResult;
import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.core.abstracts.BaseContainerBlockEntityMultipler;
import com.takoy3466.manaitamtk.core.helper.MTKContainerHelper;
import com.takoy3466.manaitamtk.core.interfaces.IMTKFurnace;
import com.takoy3466.manaitamtk.core.interfaces.ITickableBlockEntity;
import com.takoy3466.manaitamtk.core.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.core.interfaces.IOverlay;
import com.takoy3466.manaitamtk.core.MTKOverlayIcon;
import com.takoy3466.manaitamtk.block.blockEntity.MTKFurnace;
import com.takoy3466.manaitamtk.init.MTKOverlayIcons;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultiFurnaceBlockEntity extends BaseContainerBlockEntityMultipler implements WorldlyContainer, RecipeHolder, StackedContentsCompatible, ITickableBlockEntity, IOverlay {
    /*
    0: blast 材料 1: blast 燃料 2: blast 結果
    3: smoker 材料 4: smoker 燃料 5: smoker 結果
    UP: 入力 DOWN: 結果出力 SIDE: 燃料
     */
    private LazyOptional<? extends IItemHandler>[] handlers;

    public final IMTKFurnace blast, smoker;

    public AbstractMultiFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, MTKTier mtkTier) {
        super(type, pos, state, mtkTier);

        this.blast = new MTKFurnace(this, mtkTier, RecipeType.BLASTING);
        this.smoker = new MTKFurnace(this, mtkTier, RecipeType.SMOKING);

        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH
                , Direction.SOUTH, Direction.EAST, Direction.WEST);

        this.iconList = new ArrayList<>(List.of(MTKOverlayIcons.NONE, MTKOverlayIcons.NONE, MTKOverlayIcons.NONE,
                MTKOverlayIcons.NONE, MTKOverlayIcons.NONE, MTKOverlayIcons.NONE));
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal(getMTKName());
    }

    @Override
    public void serverTick() {
        blast.tick();
        smoker.tick();
    }

    @Override
    public int getContainerSize() {
        return blast.getHandler().getSlots() + smoker.getHandler().getSlots();
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
            return MTKContainerHelper.removeItem(blast.getHandler(), slotId, count);
        }else {
            return MTKContainerHelper.removeItem(smoker.getHandler(), slotId - 3, count);
        }
    }

    @Override
    public ItemStack removeItemNoUpdate(int slotId) {
        if (slotId < 3) {
            return MTKContainerHelper.takeItem(blast.getHandler(), slotId);
        }else {
            return MTKContainerHelper.takeItem(smoker.getHandler(), slotId - 3);
        }
    }

    @Override
    public void setItem(int slotId, ItemStack stack) {
        if (slotId == 0 || slotId == 3) {
            RecipeType<?> recipeType = getRecipeTypeFromStack(stack);
            if (recipeType == RecipeType.BLASTING) {
                blast.setItem(0, stack);
                return;
            }else if (recipeType == RecipeType.SMOKING) {
                smoker.setItem(0, stack);
                return;
            }
        }
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
        this.blast.getHandler().clear();
        this.smoker.getHandler().clear();
    }

    /*
    0: UP
    1: BACK
    2: LEFT
    3: FACE
    4: RIGHT
    5: DOWN
     */
    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.UP) {
            return iconList.get(0).getFace(); // 上でアクセスできるスロット
        } else if (direction == getFacing().getOpposite()) {
            return iconList.get(1).getFace(); // 後ろでアクセスできるスロット
        } else if (direction == getFacing().getClockWise()) {
            return iconList.get(2).getFace(); // 左でアクセスできるスロット
        } else if (direction == getFacing()) {
            return iconList.get(3).getFace(); // 正面でアクセス出来るスロット
        } else if (direction == getFacing().getCounterClockWise()) {
            return iconList.get(4).getFace(); // 右でアクセスできるスロット
        } else if (direction == Direction.DOWN) {
            return iconList.get(5).getFace(); // 下でアクセスできるスロット
        } else {
            return new int[]{};
        }

    }

    private Direction getFacing() {
        return this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
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
                case DOWN -> lazyOptional = this.handlers[0].cast();
                case UP -> lazyOptional = this.handlers[1].cast();
                case NORTH -> lazyOptional = this.handlers[2].cast();
                case SOUTH -> lazyOptional = this.handlers[3].cast();
                case WEST -> lazyOptional = this.handlers[4].cast();
                case EAST -> lazyOptional = this.handlers[5].cast();
                default -> lazyOptional = this.handlers[0].cast();
            }

            return lazyOptional;
        } else {
            return super.getCapability(capability, facing);
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();

        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }

    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();

        handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH
                , Direction.SOUTH, Direction.EAST, Direction.WEST);
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

        DataResult<Tag> result = MTKOverlayIcon.CODEC.listOf().encodeStart(NbtOps.INSTANCE, iconList);
        result.result().ifPresent(resultTag -> tag.put(ManaitaMTK.MOD_ID, resultTag));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        blast.load(tag);
        smoker.load(tag);

        DataResult<List<MTKOverlayIcon>> result = MTKOverlayIcon.CODEC.listOf().parse(NbtOps.INSTANCE, tag.get(ManaitaMTK.MOD_ID));
        result.result().ifPresent(list -> iconList = new ArrayList<>(list));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    /*
                0: UP
                1: BACK
                2: LEFT
                3: FACE
                4: RIGHT
                5: DOWN
                 */
    private List<MTKOverlayIcon> iconList;

    @Override
    public List<MTKOverlayIcon> getIconList() {
        return iconList;
    }

    @Override
    public void setIconList(List<MTKOverlayIcon> iconList) {
        this.iconList = iconList;
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }
}
