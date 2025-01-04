package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class NetherStarCrusher extends AContainer {
    public NetherStarCrusher(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getEnergyConsumption() {
        return Variables.plug.getConfig().getInt("items.nether-star-crusher.energy-cost");
    }

    @Override
    public String getInventoryTitle() {
        return getItemName();
    }

    @Override
    public String getMachineIdentifier() {
        return getId();
    }

    @Override
    public ItemStack getProgressBar() {
        return U.mat(Material.NETHERRACK);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void registerDefaultRecipes() {
        ItemStack pieces_of_star = Tech.A_PIECE_OF_NETHER_STAR.clone();
        pieces_of_star.setAmount(Variables.plug.getConfig().getInt("machines.nether-star-crusher.amount"));
        registerRecipe(
                Variables.plug.getConfig().getInt("machines.nether-star-crusher.time"),
                new ItemStack[] {U.mat(Material.NETHER_STAR)},
                new ItemStack[] {pieces_of_star});
    }
}
