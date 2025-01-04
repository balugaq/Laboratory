package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Food {
    public static final SlimefunItemStack MILK_BOTTLE;
    public static final SlimefunItemStack COLA;

    static {
        ItemGroup c = new ItemGroup(
                new NamespacedKey(Main.instance, "food"),
                new CustomItemStack(Material.COOKED_BEEF, "&7Consider 实验室食物", ""),
                3);

        MILK_BOTTLE = new SlimefunItemStack("LAB_MILK_BOTTLE",
                Color.WHITE,
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1, false, false),
                "&f牛奶瓶");
        COLA = new SlimefunItemStack("LAB_COLA",
                Color.BLACK,
                new PotionEffect(PotionEffectType.SPEED, 600, 1, false, true),
                "&c可乐");

        (new SlimefunItem(c, MILK_BOTTLE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                U.mat(Material.MILK_BUCKET), U.mat(Material.GLASS_BOTTLE), null,
                null, null, null,
                null, null, null
        })).register(Main.instance);

        (new SlimefunItem(c, COLA, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                U.bottle(), U.sfi("LAB_LEMON"), U.mat(Material.SUGAR),
                U.mat(Material.SUGAR), null, null,
                null, null, null
        })).register(Main.instance);

        ShapelessRecipe MILK_BOTTLE_R = new ShapelessRecipe(new NamespacedKey(Main.instance, "milk_bottle_recipe"), MILK_BOTTLE);
        MILK_BOTTLE_R.addIngredient(Material.MILK_BUCKET);
        MILK_BOTTLE_R.addIngredient(Material.GLASS_BOTTLE);
        Variables.plug.getServer().addRecipe(MILK_BOTTLE_R);
    }
}
