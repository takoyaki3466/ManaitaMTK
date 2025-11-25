package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.ManaitaMTK;
import com.takoy3466.manaitamtk.apiMTK.ITickableBlockEntity;
import com.takoy3466.manaitamtk.apiMTK.ItemFlag;
import com.takoy3466.manaitamtk.apiMTK.ItemStackKey;
import com.takoy3466.manaitamtk.apiMTK.slot.MTKItemStackHandler;
import com.takoy3466.manaitamtk.init.BlocksInit;
import com.takoy3466.manaitamtk.menu.AutoWorkbenchMTKMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;

public class AutoWorkbenchMTKBlockEntity extends BlockEntity implements MenuProvider, StackedContentsCompatible, ITickableBlockEntity {
    private NonNullList<ItemStack> items;
    private final Container container;
    private CraftingRecipe recipe;
    private ItemStack result;
    private final MTKItemStackHandler stackHandler = new MTKItemStackHandler(1){
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return false;
        }
    };

    private final LazyOptional<IItemHandler> stackHandlerLazy = LazyOptional.of(() -> this.stackHandler);

    public AutoWorkbenchMTKBlockEntity(BlockPos pos, BlockState state) {
        super(BlocksInit.BlockEntities.AUTO_WORKBENCH_MTK.get(), pos, state);
        this.items = NonNullList.withSize(9, ItemStack.EMPTY);
        container = new SimpleContainer(9);
    }

    @Override
    public void serverTick() {
        if (level.isClientSide()) return;
        boolean isEmpty = false;
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            if (stackHandler.getStackInSlot(i).isEmpty()) {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            this.tryCraft();
        }
    }

    public void tryCraft() {
        Level level = this.getLevel();
        BlockPos pos = this.getBlockPos();
        if (level.isClientSide() || this.recipe == null || this.container == null) return;

        // IItemHandlerのリストを作成
        List<IItemHandler> inputHandlerList = this.getNearbyHandler(level, pos);

        boolean isMatch = this.isMatchItem(this.container, inputHandlerList);

        if (isMatch) {
            this.extractItem(this.container, inputHandlerList);
            this.insertItem(this.stackHandler);
        }
    }

    // 近くにあるIItemHandlerをリストに詰めて返す
    public List<IItemHandler> getNearbyHandler(Level level, BlockPos pos) {
        List<IItemHandler> inputHandlerList = new ArrayList<>();
        Iterator<Direction> iterator = Arrays.stream(Direction.values()).iterator();
        while (iterator.hasNext()) {
            Direction direction = iterator.next();
            BlockEntity input = level.getBlockEntity(pos.relative(direction));
            if (input == null) continue;
            if (input.getBlockState().is(BlocksInit.Blocks.AUTO_WORKBENCH_MTK.get())) continue;
            LazyOptional<IItemHandler> lazyOptional = input.getCapability(ForgeCapabilities.ITEM_HANDLER, direction);
            if (lazyOptional.resolve().isPresent()) {
                inputHandlerList.add(lazyOptional.resolve().get());
            }
        }
        return inputHandlerList;
    }

    // レシピに必要なアイテムがIItemHandlerに入っているかチェック
    private boolean isMatchItem(Container craftingContainer, List<IItemHandler> itemHandlerList) {
        if (craftingContainer == null) return false;

        boolean isSame, hasCount;
        List<ItemFlag> itemFlags = new ArrayList<>();
        Map<ItemStackKey, Integer> stackMap = new HashMap<>();
        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            ItemStack containerStack = craftingContainer.getItem(i);
            if (containerStack.isEmpty()) continue;
            stackMap.merge(ItemStackKey.of(containerStack), containerStack.getCount(), Integer::sum);
        }

        for (Map.Entry<ItemStackKey, Integer> map: stackMap.entrySet()) {
            ItemStackKey materialKey = map.getKey();
            int needInt = map.getValue();
            isSame = false;
            hasCount = false;

            int handlerInt = 0;
            for (IItemHandler itemHandler: itemHandlerList) {
                for (int j = 0; j < itemHandler.getSlots(); j++) {
                    ItemStack handlerStack = itemHandler.getStackInSlot(j);
                    if (handlerStack.isEmpty()) continue;
                    if (ItemStackKey.of(handlerStack).equals(materialKey)) {
                        handlerInt += handlerStack.getCount();
                        isSame = true;
                    }
                }
            }
            if (needInt <= handlerInt) {
                hasCount = true;
            }

            itemFlags.add(new ItemFlag(isSame && hasCount, materialKey.getDefaultInstance()));
        }
        return itemFlags.stream().allMatch(ItemFlag::isFlag);
    }

    // 実際に減らす
    private void extractItem(Container container, List<IItemHandler> handlerList) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack material = container.getItem(i);
            if (material.isEmpty()) continue;

            for (IItemHandler handler : handlerList) {
                for (int j = 0; j < handler.getSlots(); j++) {
                    ItemStack handlerStack = handler.getStackInSlot(j);
                    if (handlerStack.isEmpty()) continue;

                    if (ItemStack.isSameItemSameTags(material, handlerStack)) {
                        handler.extractItem(j, material.getCount(), false);
                        break;
                    }
                }
            }
        }
    }

    // 結果を自身のStackHandlerに入れる
    private void insertItem(MTKItemStackHandler handler) {
        if (this.result == null) return;
        ItemStack result = this.result;
        if (handler.getStackInSlot(0).isEmpty()) {
            handler.setStackInSlot(0, result.copy());
        }
    }

    // レシピからクラフト結果アイテムを獲得
    private ItemStack setResult(CraftingRecipe recipe, CraftingContainer craftingContainer) {
        if (recipe == null || craftingContainer == null) return ItemStack.EMPTY;
        return recipe.assemble(craftingContainer, this.level.registryAccess());
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag MTKTag = new CompoundTag();
        MTKTag.put(ManaitaMTK.MOD_ID, this.stackHandler.serializeNBT());
        tag.put(ManaitaMTK.MOD_ID, MTKTag);
        ContainerHelper.saveAllItems(tag, this.items);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag MTKTag = tag.getCompound(ManaitaMTK.MOD_ID);
        this.stackHandler.deserializeNBT(MTKTag.getCompound(ManaitaMTK.MOD_ID));
        this.items = NonNullList.withSize(this.items.size(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
    }

    public void setRecipe(CraftingRecipe recipe) {
        if (recipe == null) return;
        this.recipe = recipe;
    }

    public void setContainer(CraftingContainer craftingContainer) {
        if (craftingContainer == null) return;
        this.result = this.setResult(this.recipe, craftingContainer);
        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            this.container.setItem(i, craftingContainer.getItem(i));
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("test");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new AutoWorkbenchMTKMenu(id, inventory, this.getBlockPos());
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        Iterator stackIterator = this.items.iterator();

        while(stackIterator.hasNext()) {
            ItemStack itemstack = (ItemStack)stackIterator.next();
            stackedContents.accountStack(itemstack);
        }
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.stackHandlerLazy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.stackHandlerLazy.invalidate();
    }
}
