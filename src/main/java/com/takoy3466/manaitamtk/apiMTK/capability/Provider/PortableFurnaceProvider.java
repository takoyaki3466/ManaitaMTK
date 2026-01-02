package com.takoy3466.manaitamtk.apiMTK.capability.Provider;

import com.takoy3466.manaitamtk.MTKEnum;
import com.takoy3466.manaitamtk.apiMTK.capability.interfaces.IPortableFurnace;
import com.takoy3466.manaitamtk.util.container.MTKContainer;
import com.takoy3466.manaitamtk.apiMTK.helper.MTKContainerHelper;
import com.takoy3466.manaitamtk.util.slot.MTKItemStackHandler;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class PortableFurnaceProvider implements IPortableFurnace<MTKItemStackHandler>, INBTSerializable<CompoundTag>, RecipeHolder {
    private final String HANDLER = "handler";
    private final String LIT_TIME = "LitTime";
    private final String LIT_DURATION = "litDuration";
    private final String COOKING_PROGRESS = "cookingProgress";
    private final String COOK_TIME_TOTAL = "cookTimeTotal";
    private final String RECIPE_USED = "RecipesUsed";

    private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
    private final RecipeType<? extends AbstractCookingRecipe> recipeType = RecipeType.SMELTING;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final MTKContainer mtkContainer = new MTKContainer(1);

    private final MTKItemStackHandler handler = new MTKItemStackHandler(3);
    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;
    private MTKEnum mtkEnum;
    private int itemCount;


    @Override
    public MTKItemStackHandler gethandler() {
        return this.handler;
    }

    @Override
    public int getLitTime() {
        return this.litTime;
    }

    @Override
    public void setLitTime(int litTime) {
        this.litTime = litTime;
    }

    @Override
    public int getLitDuration() {
        return this.litDuration;
    }

    @Override
    public void setLitDuration(int litDuration) {
        this.litDuration = litDuration;
    }

    @Override
    public int getCookingProgress() {
        return this.cookingProgress;
    }

    @Override
    public void setCookingProgress(int cookingProgress) {
        this.cookingProgress = cookingProgress;
    }

    @Override
    public int getCookTimeTotal() {
        return 5;
    }

    @Override
    public void setCookingTotalTime(int totalTime) {
        this.cookingTotalTime = totalTime;
    }

    @Override
    public MTKEnum getMTKEnum() {
        return this.mtkEnum;
    }

    @Override
    public void setMTKEnum(MTKEnum mtkEnum) {
        this.mtkEnum = mtkEnum;
    }

    private MTKContainer getContainer() {
        this.mtkContainer.setItem(0, this.handler.getStackInSlot(0));
        return this.mtkContainer;
    }

    @Override
    public void tick(Level level, MTKEnum mtkEnum) {
        this.mtkEnum = mtkEnum;
        if (this.isLit()) {
            --this.litTime;
        }

        ItemStack itemstack = this.handler.getStackInSlot(1);
        boolean flag2 = !this.handler.getStackInSlot(0).isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (!this.isLit() && (!flag3 || !flag2)) {
            if (this.cookingProgress > 0) {
                this.cookingProgress = Mth.clamp(this.cookingProgress - 2, 0, this.cookingTotalTime);
            }
        } else {
            Recipe<Container> recipe;
            if (flag2) {

                // レシピ取得
                recipe = this.quickCheck.getRecipeFor(this.getContainer(), level).orElse(null);
            } else {
                recipe = null;
            }

            int i = 64;
            if (!this.isLit() && this.canBurn(level.registryAccess(), recipe, this.handler, i)) {
                this.litTime = this.getBurnDuration(itemstack);
                this.litDuration = this.litTime;
                if (this.isLit()) {
                    this.cookingTotalTime = this.getCookTimeTotal();
                    if (itemstack.hasCraftingRemainingItem()) {
                        this.handler.setStackInSlot(1, itemstack.getCraftingRemainingItem());
                    } else if (flag3) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            this.handler.setStackInSlot(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (this.isLit() && this.canBurn(level.registryAccess(), recipe, this.handler, i)) {
                ++this.cookingProgress;
                if (this.cookingProgress >= this.cookingTotalTime) {
                    this.cookingProgress = 0;
                    this.cookingTotalTime = this.getCookTimeTotal();
                    if (this.burn(level.registryAccess(), recipe, this.handler, i)) {
                        this.setRecipeUsed(recipe);
                    }

                }
            } else {
                this.cookingProgress = 0;
            }
        }

    }

    private boolean isLit() {
        return this.litTime > 0;
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

    private <T extends ItemStackHandler> boolean canBurn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, T handler, int i) {
        if (!handler.getStackInSlot(0).isEmpty() && recipe != null) {
            ItemStack stack = recipe.assemble(this.getContainer(), access);
            if (stack.isEmpty()) {
                return false;
            } else {
                ItemStack stack1 = handler.getStackInSlot(2);
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
            return ForgeHooks.getBurnTime(stack, this.recipeType);
        }
    }

    private <T extends ItemStackHandler> boolean burn(RegistryAccess access, @javax.annotation.Nullable Recipe<Container> recipe, T handler, int i) {
        if (recipe != null && this.canBurn(access, recipe, handler, i)) {
            ItemStack input = handler.getStackInSlot(0);
            ItemStack result = recipe.assemble(this.getContainer(), access);

            result.setCount(result.getCount() * this.mtkEnum.getMag());

            ItemStack output = handler.getStackInSlot(2);
            if (output.isEmpty()) {
                handler.setStackInSlot(2, result.copy());
            } else if (output.is(result.getItem())) {
                output.grow(result.getCount());
            }

            if (input.is(Blocks.WET_SPONGE.asItem()) && !handler.getStackInSlot(1).isEmpty() && handler.getStackInSlot(1).is(Items.BUCKET)) {
                handler.setStackInSlot(1, new ItemStack(Items.WATER_BUCKET));
            }

            input.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        MTKContainerHelper.saveHandler(tag, this.handler);
        tag.putInt(LIT_TIME, this.litTime);
        tag.putInt(LIT_DURATION, this.litDuration);
        tag.putInt(COOKING_PROGRESS, this.cookingProgress);
        tag.putInt(COOK_TIME_TOTAL, this.cookingTotalTime);
        CompoundTag compoundTag = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundTag.putInt(resourceLocation.toString(), integer));
        tag.put(RECIPE_USED, compoundTag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        MTKContainerHelper.loadHandler(tag, this.handler);
        this.litTime = tag.getInt(LIT_TIME);
        this.litDuration = tag.getInt(LIT_DURATION);
        this.cookingProgress = tag.getInt(COOKING_PROGRESS);
        this.cookingTotalTime = tag.getInt(COOK_TIME_TOTAL);
        CompoundTag compoundtag = tag.getCompound(RECIPE_USED);
        Iterator var3 = compoundtag.getAllKeys().iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }
    }
}
