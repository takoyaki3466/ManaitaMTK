package com.takoy3466.manaitamtk.block.blockEntity;

import com.takoy3466.manaitamtk.api.interfaces.IMTKFurnace;
import com.takoy3466.manaitamtk.api.mtkTier.MTKTier;
import com.takoy3466.manaitamtk.util.container.MTKContainer;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Optional;

public class MTKFurnace implements IMTKFurnace {
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;
    private NonNullList<ItemStack> items;
    private final MultiFurnaceBlockEntity blockEntity;
    private final MTKTier mtkTier;
    private final MTKContainer mtkContainer = new MTKContainer(1);

    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;

    private final ContainerData containerData;

    public MTKFurnace(MultiFurnaceBlockEntity blockEntity, MTKTier mtkTier, RecipeType recipeType) {
        this.recipeType = recipeType;
        recipesUsed = new Object2IntOpenHashMap<>();
        quickCheck = RecipeManager.createCheck(recipeType);
        items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.blockEntity = blockEntity;
        this.mtkTier = mtkTier;
        containerData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> litTime;
                    case 1 -> litDuration;
                    case 2 -> cookingProgress;
                    case 3 -> cookingTotalTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0 -> litTime = i1;
                    case 1 -> litDuration = i1;
                    case 2 -> cookingProgress = i1;
                    case 3 -> cookingTotalTime = i1;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public int getLitTime() {
        return litTime;
    }

    @Override
    public void setLitTime(int litTime) {
        this.litTime = litTime;
    }

    @Override
    public int getLitDuration() {
        return litDuration;
    }

    @Override
    public void setLitDuration(int litDuration) {
        this.litDuration = litDuration;
    }

    @Override
    public int getCookingProgress() {
        return cookingProgress;
    }

    @Override
    public void setCookingProgress(int cookingProgress) {
        this.cookingProgress = cookingProgress;
    }

    @Override
    public int getCookingTotalTime() {
        return cookingTotalTime;
    }

    @Override
    public void setCookingTotalTime(int totalTime) {
        cookingTotalTime = totalTime;
    }

    @Override
    public ContainerData getContainerData() {
        return containerData;
    }

    @Override
    public RecipeType<?> getRecipeType() {
        return recipeType;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }
    
    @Override
    public boolean isEmpty() {
        Iterator iterator = items.iterator();
        ItemStack stack;
        do {
            if (!iterator.hasNext()) {
                return true;
            }

            stack = (ItemStack) iterator.next();
        } while (stack.isEmpty());

        return false;
    }
    
    @Override
    public ItemStack getItem(int slotId) {
        if (slotId > 2) {
            return ItemStack.EMPTY;
        }
        return items.get(slotId);
    }
    
    public void setItem(int i, ItemStack stack) {
        ItemStack itemstack = this.items.get(i);
        boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, stack);
        this.items.set(i, stack);
        if (stack.getCount() > blockEntity.getMaxStackSize()) {
            stack.setCount(blockEntity.getMaxStackSize());
        }

        if (i == 0 && !flag) {
            this.cookingTotalTime = 5;
            this.cookingProgress = 0;
            blockEntity.setChanged();
        }
    }
    
    public void setRecipesUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            recipesUsed.addTo(resourcelocation, 1);
        }
    }

    public void fillStackedContents(StackedContents stackedContents) {
        Iterator stackIterator = items.iterator();

        while(stackIterator.hasNext()) {
            ItemStack itemstack = (ItemStack)stackIterator.next();
            stackedContents.accountStack(itemstack);
        }
    }
    
    public RecipeType<?> getRecipeTypeFromStack(ItemStack ingredientStack) {
        if (ingredientStack.isEmpty()) {
            return null;
        }
        Level level = blockEntity.getLevel();
        if (level == null) {
            return null;
        }
        SimpleContainer inv = new SimpleContainer(ingredientStack);
        Optional<? extends AbstractCookingRecipe> optional = quickCheck.getRecipeFor(inv, level);
        return optional.<RecipeType<?>>map(AbstractCookingRecipe::getType).orElse(null);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("BurnTime", litTime);
        tag.putInt("CookTime", cookingProgress);
        tag.putInt("CookTimeTotal", cookingTotalTime);
        ContainerHelper.saveAllItems(tag, items);
        CompoundTag compoundtag = new CompoundTag();
        recipesUsed.forEach((resourceLocation, integer) -> compoundtag.putInt(resourceLocation.toString(), integer));
        tag.put("RecipesUsed", compoundtag);
    }

    @Override
    public void load(CompoundTag tag) {
        items = NonNullList.withSize(blockEntity.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);
        litTime = tag.getInt("BurnTime");
        cookingProgress = tag.getInt("CookTime");
        cookingTotalTime = tag.getInt("CookTimeTotal");
        litDuration = getBurnDuration(items.get(1));
        CompoundTag compoundtag = tag.getCompound("RecipesUsed");
        Iterator iterator = compoundtag.getAllKeys().iterator();

        while(iterator.hasNext()) {
            String s = (String)iterator.next();
            recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }
    }

    @Override
    public  void tick() {
        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        BlockState state = blockEntity.getBlockState();
        boolean flag = isLit();
        boolean flag1 = false;

        if (isLit()) {
            --litTime;
        }

        ItemStack itemstack = items.get(1);
        boolean flag2 = !items.get(0).isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (!isLit() && (!flag3 || !flag2)) {
            if (cookingProgress > 0) {
                cookingProgress = Mth.clamp(cookingProgress - 2, 0, cookingTotalTime);
            }
        } else {
            Recipe<Container> recipe;
            if (flag2) {

                recipe = quickCheck.getRecipeFor(getContainer(), level).orElse(null);

            } else {
                recipe = null;
            }

            int i = blockEntity.getMaxStackSize();
            if (!isLit() && canBurn(level.registryAccess(), recipe, items, i)) {
                System.out.println(canBurn(level.registryAccess(), recipe, items, i));
                litTime = getBurnDuration(itemstack);
                litDuration = litTime;
                if (isLit()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem()) {
                        items.set(1, itemstack.getCraftingRemainingItem());
                    } else if (flag3) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (isLit() && canBurn(level.registryAccess(), recipe, items, i)) {
                ++cookingProgress;
                if (cookingProgress == cookingTotalTime) {
                    cookingProgress = 0;
                    cookingTotalTime = getCookingTotalTime();
                    if (burn(level.registryAccess(), recipe, items, i)) {
                        blockEntity.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                cookingProgress = 0;
            }
        }

        if (flag != isLit()) {
            flag1 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, isLit());
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            blockEntity.setChanged();
        }
    }

    private SimpleContainer getContainer() {
        this.mtkContainer.setItem(0, items.get(0));
        return this.mtkContainer;
    }

    private boolean burn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, NonNullList<ItemStack> stacks, int i) {
        if (recipe != null && canBurn(access, recipe, stacks, i)) {
            ItemStack input = stacks.get(0);
            ItemStack result = recipe.assemble(getContainer(), access);

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

    private boolean canBurn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, NonNullList<ItemStack> stacks, int i) {
        if (!stacks.get(0).isEmpty() && recipe != null) {
            ItemStack stack = recipe.assemble(getContainer(), access);
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

    private int getBurnDuration(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            return ForgeHooks.getBurnTime(stack, recipeType);
        }
    }

    @Override
    public boolean isLit() {
        return litTime > 0;
    }

    public int getLitProgress() {
        int litTime = this.containerData.get(0);
        int litDuration = this.containerData.get(1);
        return litDuration != 0 ? litTime * 13 / litDuration : 0;
    }

    @Override
    public int getBurnProgress() {
        int progress = containerData.get(2);
        int total = containerData.get(3);
        return total != 0 ? progress * 24 / total : 0;
    }

    @Override
    public MTKTier getMTKTier() {
        return mtkTier;
    }

    @Override
    public int getMultiple() {
        return getMTKTier().getMultiple();
    }

    @Override
    public String getMTKName() {
        return getMTKTier().getName();
    }
}
