package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Freeze_Dolphin.lab.ADrill;
import me.Freeze_Dolphin.lab.OreGenSystem;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class QuartzDrill extends ADrill {
    public QuartzDrill(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public GEOResource getOreGenResource() {
        return OreGenSystem.getResource("赛特斯石英");
    }

    public ItemStack[] getOutputItems() {
        return new ItemStack[] {Tech.CERTUS_QUARTZ};
    }

    public int getProcessingTime() {
        return 600;
    }

    public int getSpeed() {
        return 1;
    }

    public int getEnergyConsumption() {
        return 32;
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public String getMachineIdentifier() {
        return getId();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_PICKAXE);
    }
}
