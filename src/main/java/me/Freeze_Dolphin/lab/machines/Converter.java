package me.Freeze_Dolphin.lab.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.Freeze_Dolphin.lab.AdvancedAContainer;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Converter extends AdvancedAContainer {
    public Converter(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public static ItemStack mat(Material mat) {
        return new ItemStack(mat);
    }

    public static ItemStack cfg2it(String cfg) {
        ItemStack rt = new ItemStack(Material.AIR);

        Laboratory.debug(cfg);

        if (cfg.contains("SF")) {
            String id = cfg.split("SF\\{")[1].split("\\}")[0];
            SlimefunItem item = SlimefunItem.getById(id);
            if (item != null) {
                rt = item.getItem();
            } else {
                Laboratory.debug("Slimefun Item not found: " + id);
            }
        } else {
            String mat = cfg.split("MC\\{")[1].split("\\}")[0];
            rt = new ItemStack(getMaterial(mat));
            rt.setAmount(str2int(cfg.split("\\=")[1]));
        }

        try {
            Laboratory.debug("=---------------------------=" + rt + "=---------------------------=");
        } catch (Exception ex) {
            Laboratory.debug("=---------------------------= An Error Happened Here=---------------------------=");
            U.scolor("&c&l=-E-R-R-O-R-_-H-E-R-E--------=");
            Laboratory.debug("");
        }

        return rt;
    }

    public static int str2int(String str) {
        return Integer.parseInt(str);
    }

    public static ItemStack air() {
        return new ItemStack(Material.AIR);
    }

    public static Material getMaterial(String string) {
        try {
            Material mat = Material.getMaterial(string);
            if (mat == null) {
                Laboratory.debug("Material not found: " + string);
                for (Material m : Material.values()) {
                    if (m.name().toLowerCase().contains(string.toLowerCase())) {
                        Laboratory.debug("Did you mean: " + m.name() + "?");
                        break;
                    }
                }

                for (Material m : Material.values()) {
                    if (m.name().toLowerCase().startsWith(string.toLowerCase())) {
                        Laboratory.debug("Did you mean: " + m.name() + "?");
                        break;
                    }
                }

                for (Material m : Material.values()) {
                    if (m.name().toLowerCase().endsWith(string.toLowerCase())) {
                        Laboratory.debug("Did you mean: " + m.name() + "?");
                        break;
                    }
                }

                int mostLike = 0;
                Material mostLiked = null;
                for (Material m : Material.values()) {
                    int like = getLike(m.name().toLowerCase(), string.toLowerCase());
                    if (like > mostLike) {
                        mostLike = like;
                        mostLiked = m;
                    }
                }

                if (mostLiked != null) {
                    Laboratory.debug("Did you mean: " + mostLiked.name() + "?");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Material.AIR;
    }

    public static int getLike(String str1, String str2) {
        int like = 0;
        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                like++;
            }
        }
        return like;
    }

    public String getInventoryTitle() {
        return getItemName();
    }

    public String getMachineIdentifier() {
        return getId();
    }

    public ItemStack getProgressBar() {
        return new ItemStack(Material.BEACON);
    }

    public void registerDefaultRecipes() {
        if (!Variables.Nar || Variables.plug.getConfig().getBoolean("items.mt.iridium-recipe")) {
            registerRecipe(
                    Variables.plug.getConfig().getInt("items.mt.recipe-time.iridium"),
                    new ItemStack[]{new ItemStack(Material.IRON_BLOCK)},
                    new ItemStack[]{Tech.IRIDIUM});
        }

        registerRecipe(
                Variables.cfg.getInt("items.mt.recipe-time.radium"),
                new ItemStack[]{SlimefunItems.PLUTONIUM},
                new ItemStack[]{Tech.RADIUM});

        for (List<String> ls : Tech.MT_RECIPES) {
            int time;

            if (ls.size() == 2 || ls.get(2) == null) {
                time = 240;
            } else {
                time = str2int(ls.get(2));
            }

            ItemStack ait1;
            ItemStack ait2 = null;
            ItemStack bit1;
            ItemStack bit2 = null;

            if (ls.get(0).contains(" \\| ")) {
                String[] as = ls.get(0).split(" \\| ");
                ait1 = cfg2it(as[0]);
                ait2 = cfg2it(as[1]);
            } else {
                ait1 = cfg2it(ls.get(0));
            }

            if (ls.get(1).contains(" \\| ")) {
                String[] bs = ls.get(1).split(" \\| ");
                bit1 = cfg2it(bs[0]);
                bit2 = cfg2it(bs[1]);
            } else {
                bit1 = cfg2it(ls.get(1));
            }

            if (ait2 == null) {
                if (bit2 == null) {
                    registerRecipe(time, new ItemStack[]{ait1}, new ItemStack[]{bit1});
                    continue;
                }
                registerRecipe(time, new ItemStack[]{ait1}, new ItemStack[]{bit1, bit2});
                continue;
            }
            if (bit2 == null) {
                registerRecipe(time, new ItemStack[]{ait1, ait2}, new ItemStack[]{bit1});
                continue;
            }
            registerRecipe(time, new ItemStack[]{ait1, ait2}, new ItemStack[]{bit1, bit2});
        }
    }
}
