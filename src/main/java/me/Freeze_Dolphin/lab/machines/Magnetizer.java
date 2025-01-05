package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Freeze_Dolphin.lab.AdvancedAContainer;
import me.Freeze_Dolphin.lab.Tech;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Magnetizer extends AdvancedAContainer {
    public Magnetizer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public String getMachineIdentifier() {
        return getId();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_BLOCK);
    }

    public void registerDefaultRecipes() {
        registerRecipe(120, new ItemStack[] {SlimefunItems.MAGNET}, new ItemStack[] {Tech.SUPER_MAGNET});
        registerRecipe(
                240, new ItemStack[] {SlimefunItems.REINFORCED_PLATE}, new ItemStack[] {Tech.MAGNETIZED_REINFORCED_PLATE
                });
    }
}
