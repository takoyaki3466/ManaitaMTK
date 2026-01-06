package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.api.abstracts.BaseContainerBlockEntityMultipler;
import com.takoy3466.manaitamtk.api.interfaces.ITickableBlockEntity;
import com.takoy3466.manaitamtk.init.BlockEntitiesInit;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class MTKFurnaceBlockEntity extends BaseContainerBlockEntityMultipler implements WorldlyContainer, RecipeHolder, StackedContentsCompatible, ITickableBlockEntity {
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;
    private LazyOptional<? extends IItemHandler>[] handlers;
    private NonNullList<ItemStack> items;
    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;

    public final ContainerData dataAccess;
    private final Component guiName = Component.translatable("block.manaitamtk.mtk_furnace.title");

    public MTKFurnaceBlockEntity(BlockPos pos, BlockState state, MTKTier mtkTier) {
        super(switch (mtkTier.getMultiple()) {
            case 2 -> BlockEntitiesInit.MTK_FURNACE_WOOD.get();
            case 4 -> BlockEntitiesInit.MTK_FURNACE_STONE.get();
            case 8 -> BlockEntitiesInit.MTK_FURNACE_IRON.get();
            case 16 -> BlockEntitiesInit.MTK_FURNACE_GOLD.get();
            case 32 -> BlockEntitiesInit.MTK_FURNACE_DIAMOND.get();
            case 64 -> BlockEntitiesInit.MTK_FURNACE_MTK.get();
            case 512 -> BlockEntitiesInit.MTK_FURNACE_GODMTK.get();
            case 33554431 -> BlockEntitiesInit.MTK_FURNACE_BREAK.get();
            default -> BlockEntitiesInit.MTK_FURNACE_WOOD.get();
        }, pos, state, mtkTier);


        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.recipeType = RecipeType.SMELTING;
        this.recipesUsed = new Object2IntOpenHashMap();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
        this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> MTKFurnaceBlockEntity.this.litTime;
                    case 1 -> MTKFurnaceBlockEntity.this.litDuration;
                    case 2 -> MTKFurnaceBlockEntity.this.cookingProgress;
                    case 3 -> MTKFurnaceBlockEntity.this.cookingTotalTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0 -> MTKFurnaceBlockEntity.this.litTime = i1;
                    case 1 -> MTKFurnaceBlockEntity.this.litDuration = i1;
                    case 2 -> MTKFurnaceBlockEntity.this.cookingProgress = i1;
                    case 3 -> MTKFurnaceBlockEntity.this.cookingTotalTime = i1;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal(this.guiName.getString() + " x" + getMultiple());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return null;
    }

    // 加工時間の設定
    private static int  getTotalCookTime() {
        return 1;
    }

    // ここでレシピ結果を出している
    private boolean burn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, NonNullList<ItemStack> stacks, int i) {
        if (recipe != null && this.canBurn(access, recipe, stacks, i)) {
            ItemStack input = stacks.get(0);
            ItemStack result = recipe.assemble(this, access);

            // いつもの倍化処理
            multipler(result);

            ItemStack output = stacks.get(2);
            if (output.isEmpty()) {
                stacks.set(2, result.copy());
            } else if (output.is(result.getItem())) {
                output.grow(result.getCount());
            }

            if (input.is(Blocks.WET_SPONGE.asItem()) && !stacks.get(1).isEmpty() && stacks.get(1).is(Items.BUCKET)) {
                stacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            input.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    // まだ加工途中ですか？
    private boolean isLit() {
        return this.litTime > 0;
    }

    // NBTの読み込み
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.litTime = tag.getInt("BurnTime");
        this.cookingProgress = tag.getInt("CookTime");
        this.cookingTotalTime = tag.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(1));
        CompoundTag compoundtag = tag.getCompound("RecipesUsed");
        Iterator var3 = compoundtag.getAllKeys().iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }

    }

    // NBTの書き込み
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("BurnTime", this.litTime);
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(tag, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundtag.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundtag);
    }

    // ServerでのTick処理 (焼き時間を開いてなくても残り時間を減らすように書いている(と思う))
    @Override
    public void serverTick() {
        BlockPos pos = this.getBlockPos();
        BlockState state = this.getBlockState();
        boolean flag = this.isLit();
        boolean flag1 = false;

        if (this.isLit()) {
            --this.litTime;
        }

        ItemStack itemstack = this.items.get(1);
        boolean flag2 = !this.items.get(0).isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (!this.isLit() && (!flag3 || !flag2)) {
            if (this.cookingProgress > 0) {
                this.cookingProgress = Mth.clamp(this.cookingProgress - 2, 0, this.cookingTotalTime);
            }
        } else {
            Recipe<Container> recipe;
            if (flag2) {

                // レシピ取得
                recipe = this.quickCheck.getRecipeFor(this, level).orElse(null);

            } else {
                recipe = null;
            }

            int i = this.getMaxStackSize();
            if (!this.isLit() && this.canBurn(level.registryAccess(), recipe, this.items, i)) {
                this.litTime = this.getBurnDuration(itemstack);
                this.litDuration = this.litTime;
                if (this.isLit()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem()) {
                        this.items.set(1, itemstack.getCraftingRemainingItem());
                    } else if (flag3) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            this.items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (this.isLit() && this.canBurn(level.registryAccess(), recipe, this.items, i)) {
                ++this.cookingProgress;
                if (this.cookingProgress == this.cookingTotalTime) {
                    this.cookingProgress = 0;
                    this.cookingTotalTime = getTotalCookTime();
                    if (this.burn(level.registryAccess(), recipe, this.items, i)) {
                        this.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                this.cookingProgress = 0;
            }
        }

        if (flag != this.isLit()) {
            flag1 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, this.isLit());
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            setChanged(level, pos, state);
        }
    }

    // お前は加工できるのか？
    private boolean canBurn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, NonNullList<ItemStack> stacks, int i) {
        if (!stacks.get(0).isEmpty() && recipe != null) {
            ItemStack stack = recipe.assemble(this, access);
            if (stack.isEmpty()) {
                return false;
            } else {
                ItemStack stack1 = stacks.get(2);
                if (stack1.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(stack1, stack)) {
                    return false;
                } else if (stack1.getCount() + stack.getCount() <= i && stack1.getCount() + stack.getCount() <= stack1.getMaxStackSize()) {
                    return true;
                } else {
                    return stack1.getCount() + stack.getCount() <= stack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    // 加工時間の獲得
    private int getBurnDuration(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            return ForgeHooks.getBurnTime(stack, this.recipeType);
        }
    }

    @Override
    public boolean canPlaceItem(int i, ItemStack stack) {
        if (i == 2) {
            return false;
        } else if (i != 1) {
            return true;
        } else {
            ItemStack itemstack = this.items.get(1);
            return ForgeHooks.getBurnTime(stack, this.recipeType) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        }
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.items.iterator();

        ItemStack itemstack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemstack = (ItemStack)var1.next();
        } while(itemstack.isEmpty());

        return false;
    }

    @Override
    public ItemStack getItem(int i) {
        return this.items.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        return ContainerHelper.removeItem(this.items, i, i1);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(this.items, i);
    }

    @Override
    public void setItem(int i, ItemStack stack) {
        ItemStack itemstack = this.items.get(i);
        boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, stack);
        this.items.set(i, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (i == 0 && !flag) {
            this.cookingTotalTime = getTotalCookTime();
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.items.clear();
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
    public boolean canPlaceItemThroughFace(int i, ItemStack stack, @javax.annotation.Nullable Direction direction) {
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
            ResourceLocation resourcelocation = recipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
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
    public void awardUsedRecipes(Player p_281647_, List<ItemStack> list) {
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @javax.annotation.Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            LazyOptional lazyOptional;
            switch (facing) {
                case UP -> lazyOptional = this.handlers[0].cast();
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

        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }

    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
