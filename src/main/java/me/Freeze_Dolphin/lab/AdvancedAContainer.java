package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AdvancedAContainer extends AContainer implements RecipeDisplayItem {
    private static final ItemStack SPLIT_PLACEHOLDER = new CustomItemStack(
            Material.WHITE_STAINED_GLASS_PANE,
            "&7---配方分割线---"
    );
    private static final ItemStack SEPARATOR_ITEM = new CustomItemStack(
            Material.LIGHT_GRAY_STAINED_GLASS_PANE,
            "&a"
    );
    protected AdvancedAContainer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected AdvancedAContainer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    public @NotNull List<ItemStack> getDisplayRecipes() {
        Laboratory.debug("Displaying recipes for " + getId());
        List<ItemStack> display = new ArrayList<>();
        List<MachineRecipe> recipes = this.recipes;
        boolean displayingInput = true;
        if (recipes != null && !recipes.isEmpty()) {
            for (int k = 0; k < recipes.size(); k++) {
                boolean valid = false;
                MachineRecipe recipe = recipes.get(k);
                ItemStack[] input = recipe.getInput();
                ItemStack[] output = recipe.getOutput();
                if (input.length == 0 || output.length == 0) {
                    Laboratory.debug(" | Invalid recipe in machine");
                    Laboratory.debug(" | k = " + k);
                    continue;
                }

                if (!displayingInput) {
                    Laboratory.debug(" | Adding pre-separator");
                    display.add(SEPARATOR_ITEM);
                }

                boolean added = false;
                Laboratory.debug(" | Looping through inputs");
                for (int i = 0; i < input.length; i++) {
                    ItemStack item = input[i];
                    if (item != null && item.getType() != Material.AIR && item.getType().isItem()) {
                        Laboratory.debug(" | Adding input " + item.getType());
                        display.add(item);
                        valid = true;
                        added = true;
                    }
                    if (i < input.length - 1) {
                        Laboratory.debug(" | Adding separator");
                        display.add(SEPARATOR_ITEM);
                    }
                    displayingInput = false;
                }
                Laboratory.debug(" | Looping through done");

                if (displayingInput) {
                    Laboratory.debug(" | Adding pre-separator");
                    display.add(SEPARATOR_ITEM);
                }

                Laboratory.debug(" | Looping through outputs");
                for (int i = 0; i < output.length; i++) {
                    ItemStack item = output[i];
                    if (item != null && item.getType() != Material.AIR && item.getType().isItem()) {
                        Laboratory.debug(" | Adding output " + item.getType());
                        display.add(item);
                        valid = true;
                    }
                    if (i < output.length - 1) {
                        Laboratory.debug(" | Adding separator");
                        display.add(SEPARATOR_ITEM);
                    }
                    displayingInput = true;
                }

                if (valid && k != recipes.size() - 1) {
                    Laboratory.debug(" | Adding splitter");
                    display.add(SPLIT_PLACEHOLDER);
                    display.add(SPLIT_PLACEHOLDER);
                }
                Laboratory.debug(" | Looping through done");
            }
        }

        return display;
    }
}
