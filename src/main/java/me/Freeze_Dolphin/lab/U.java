package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class U {
    public static final String prefix = "&b[&9Laboratory&b]";
    public static final String cprefix = color("&b[&9Laboratory&b]");
    public static final String prefix_s = "&b[&9Laboratory&b] ";
    public static final String cprefix_s = color("&b[&9Laboratory&b] ");
    private static final Random rand = new Random();

    public static ItemStack bottle() {
        CustomItemStack cis = new CustomItemStack(Material.POTION);
        cis.editMeta(meta -> {
            if (meta instanceof PotionMeta pm) {
                pm.setBasePotionData(new PotionData(PotionType.WATER));
            }
        });
        return cis;
    }

    public static ItemStack mat(Material material) {
        return new ItemStack(material);
    }

    public static ItemStack sfi(String id) {
        SlimefunItem sfi = SlimefunItem.getById(id);
        if (sfi == null) return null;
        return sfi.getItem();
    }

    public static boolean chance(int integer, int chance) {
        return rand.nextInt(integer) < chance;
    }

    public static int lrandom(int integer, int chance, int highest) {
        int rt = 0;
        if (chance(integer, chance)) rt = random(highest, 0);
        return rt;
    }

    public static int random(int highest, int lowest) {
        return rand.nextInt(highest - lowest + 1) + lowest;
    }

    public static ItemStack[] midr(ItemStack item) {
        return new ItemStack[]{null, null, null, null, item};
    }

    public static ItemStack[] allr(ItemStack item) {
        return new ItemStack[]{item, item, item, item, item, item, item, item, item};
    }

    public static ItemStack[] halfr2(ItemStack item1, ItemStack item2) {
        return new ItemStack[]{item1, item2, item1, item2, item1, item2, item1, item2, item1};
    }

    public static ItemStack[] halfr3(ItemStack item1, ItemStack item2, ItemStack item3) {
        return new ItemStack[]{item1, item2, item1, item2, item3, item2, item1, item2, item1};
    }

    public static ItemStack[] firsts(ItemStack item) {
        return new ItemStack[]{item};
    }

    public static void scolor(String msg) {
        Variables.plug.getServer().getConsoleSender().sendMessage(cprefix_s + color(msg));
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void addDyeItem(ItemStack item) {
        Variables.Dye.add(item);
    }

    public static String getCfgMessage(String path) {
        return color(Variables.cfg.getString(path));
    }

    public static boolean compareBlockLocation(Location loc1, Location loc2) {
        return loc1.getBlockX() == loc2.getBlockX()
                && loc1.getBlockY() == loc2.getBlockY()
                && loc1.getBlockZ() == loc2.getBlockZ();
    }

    public static void addUnplaceableItem(ItemStack item) {
        Variables.UnplaceableItems.add(item);
    }

    public static Research newResearch(String name, int level) {
        int id = 0;

        while (true) {
            try {
                Variables.ResearchIdentifyDesigner.get(id);
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
            id++;
        }

        Laboratory.debug("registing research: " + name + "; level: " + level + "; id: " + Variables.ResearchIdentify + id);

        Variables.ResearchIdentifyDesigner.add(Boolean.TRUE);

        return new Research(new NamespacedKey(Laboratory.instance, String.valueOf(Variables.ResearchIdentify + id)), Variables.ResearchIdentify + id, name, level);
    }

    public static void newDelayedTask(Runnable br, Long tick) {
        Bukkit.getScheduler().runTaskLater(Variables.plug, br, tick);
    }

    public static ItemStack getItemInMainHand(Player p) {
        return p.getInventory().getItemInMainHand().clone();
    }

    public static void setItemInMainHand(Player p, ItemStack it) {
        p.getInventory().setItemInMainHand(it);
    }

    public static Location getHandLocation(Player p) {
        Location loc = p.getEyeLocation();

        return new Location(loc.getWorld(), loc.getX(), loc.getY() - 0.75D, loc.getZ());
    }

    public static Entity getCursorTarget(Player p, double range) {
        Location loc = p.getEyeLocation();
        Vector vec = loc.getDirection().multiply(0.15D);
        Block block;
        while ((range -= 0.1D) > 0.0D && ((block = loc.getWorld().getBlockAt(loc)).isLiquid() || block.isEmpty())) {
            for (Entity entity : loc.getWorld().getNearbyEntities(loc.add(vec), 0.001D, 0.001D, 0.001D)) {
                Entity target;
                if ((target = entity) != p) {
                    return target;
                }
            }
        }
        return null;
    }

    public static String[] listConvertToArray(List<String> strList) {
        return strList.toArray(new String[0]);
    }

    public static List<ItemStack> getShortCutBar(Player p) {
        PlayerInventory playerInventory = p.getInventory();

        List<ItemStack> l = new ArrayList<>();

        int i = 0;
        while (i < 9) {
            l.add(playerInventory.getItem(i));
            i++;
        }

        return l;
    }

    public static Location getBlockCenterLocation(Block b) {
        Location l = b.getLocation();
        return new Location(l.getWorld(), l.getBlockX() + 0.5D, l.getBlockY() + 0.5D, l.getBlockZ() + 0.5D);
    }

    public static int getResearchLevelCost(String item) {
        return Variables.cfg.getInt("researches." + item + ".level");
    }

    public static void setPenetratingRadioactive(ItemStack item) {
        Variables.penetratingRadioactive.add(item);
    }
}
