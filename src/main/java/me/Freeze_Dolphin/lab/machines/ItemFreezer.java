package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Freeze_Dolphin.lab.U;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class ItemFreezer extends AContainer {
    public ItemFreezer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public String getMachineIdentifier() {
        return getId();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.ICE);
    }

    public void registerDefaultRecipes() {
        registerRecipe(8, new ItemStack[] {U.mat(Material.WATER_BUCKET)}, new ItemStack[] {
            U.mat(Material.ICE), U.mat(Material.BUCKET)
        });
        registerRecipe(3, new ItemStack[] {new ItemStack(Material.SNOWBALL, 4)}, new ItemStack[] {U.mat(Material.ICE)});
        registerRecipe(3, new ItemStack[] {new ItemStack(Material.SNOW_BLOCK)}, new ItemStack[] {U.mat(Material.ICE)});
        registerRecipe(24, new ItemStack[] {U.mat(Material.ICE)}, new ItemStack[] {U.mat(Material.PACKED_ICE)});
    }
}
