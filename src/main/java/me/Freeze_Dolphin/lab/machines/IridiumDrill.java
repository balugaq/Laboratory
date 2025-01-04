package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.Freeze_Dolphin.lab.ADrill;
import me.Freeze_Dolphin.lab.OreGenSystem;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class IridiumDrill extends ADrill {
    public IridiumDrill(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public GEOResource getOreGenResource() {
        return OreGenSystem.getResource("铱");
    }

    @Override
    public ItemStack[] getOutputItems() {
        return new ItemStack[] {Tech.IRIDIUM};
    }

    @Override
    public int getProcessingTime() {
        return 1200;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getEnergyConsumption() {
        return 32;
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
        return new ItemStack(Material.IRON_PICKAXE);
    }
}
