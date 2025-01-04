package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Variables {
    public static final List<ItemStack> rechargableBattery = new ArrayList<>();
    public static final List<ItemStack> BlastFurnaceFuel = new ArrayList<>();
    public static final List<ItemStack> Dye = new ArrayList<>();
    public static final List<ItemStack> UnplaceableItems = new ArrayList<>();
    public static final List<ItemStack> UnequippedBardings = new ArrayList<>();
    public static final List<Boolean> ResearchIdentifyDesigner = new ArrayList<>();
    public static final List<ItemStack> penetratingRadioactive = new ArrayList<>();
    public static final Map<ItemStack[], ItemStack[]> electrolyzer_r = new HashMap<>();
    public static Plugin plug;
    public static FileConfiguration cfg;
    public static boolean Nar;
    public static RecipeType Non = RecipeType.NULL;
    public static RecipeType Mystery;
    public static List<String> Vanish = new ArrayList<>();
    public static List<UUID> Plasma = new ArrayList<>();
    public static int ResearchIdentify;
}
