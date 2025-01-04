package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class ChargeableItem extends SlimefunItem implements Rechargeable {
    private final int maxCharge;

    public ChargeableItem(
            ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int maxItemCharge) {
        super(itemGroup, item, recipeType, recipe);
        this.maxCharge = maxItemCharge;
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return this.maxCharge;
    }
}
