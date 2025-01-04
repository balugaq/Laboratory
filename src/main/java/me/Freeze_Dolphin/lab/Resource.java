package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Resource implements Listener {
    public static final SlimefunItemStack BERYLLIUM_INGOT =
            new SlimefunItemStack("LAB_BERYLLIUM_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b铍锭"));
    public static final SlimefunItemStack LITHIUM_INGOT =
            new SlimefunItemStack("LAB_LITHIUM_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b锂锭"));
    public static final SlimefunItemStack POTASSIUM_INGOT =
            new SlimefunItemStack("LAB_POTASSIUM_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b钾锭"));
    public static final SlimefunItemStack SODIUM_INGOT =
            new SlimefunItemStack("LAB_SODIUM_INGOT", new CustomItemStack(Material.IRON_INGOT, "&b钠锭"));
    public static final SlimefunItemStack BERYLLIUM_DUST =
            new SlimefunItemStack("LAB_BERYLLIUM_DUST", new CustomItemStack(Material.SUGAR, "&6铍粉"));
    public static final SlimefunItemStack LITHIUM_DUST =
            new SlimefunItemStack("LAB_LITHIUM_DUST", new CustomItemStack(Material.SUGAR, "&6锂粉"));
    public static final SlimefunItemStack POTASSIUM_DUST =
            new SlimefunItemStack("LAB_POTASSIUM_DUST", new CustomItemStack(Material.SUGAR, "&6钾粉"));
    public static final SlimefunItemStack SODIUM_DUST =
            new SlimefunItemStack("LAB_SODIUM_DUST", new CustomItemStack(Material.SUGAR, "&6钠粉"));
    private static final ItemGroup c = Tech.cr;
    public static SlimefunItemStack GRAPHITE;
    public static SlimefunItemStack CAROBBIITE;

    static {
        try {
            GRAPHITE = new SlimefunItemStack("LAB_GRAPHITE",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU5YTMxZDFiMmFlMDNmMWY5YzhlMGFlNWRlOGNiOGYxZDlmMDI2OTcxZWY3NDdlNGIyMzdhYmEwNWE4ZTUyOCJ9fX0="),
                            "&8石墨块"));
            CAROBBIITE = new SlimefunItemStack("LAB_CAROBBIITE",
                    new CustomItemStack(
                            SkullUtil.getByBase64(
                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzEyNTExZTllOWUxYWFkOTI3NTI1NmZkNDIyMDc0YzUyNzczMzkxZTM3YzNjNTU2OTY5NjY3ZjA5N2Y5OTczZSJ9fX0="),
                            "&e方氟钾石"));
        } catch (Exception e) {
            Main.debugException(e);
        }
    }

    public Resource() {
        (new SlimefunItem(c, GRAPHITE, Tech.DIG, U.midr(U.mat(Material.COAL_ORE)))).register(Main.instance);
        (new SlimefunItem(c, CAROBBIITE, Tech.DIG, U.midr(new CustomItemStack(Material.DIORITE))))
                .register(Main.instance);

        (new SlimefunItem(c, BERYLLIUM_DUST, Tech.DIG, U.midr(U.mat(Material.EMERALD_ORE)))).register(Main.instance);
        (new SlimefunItem(c, LITHIUM_DUST, Tech.DIG, U.midr(U.mat(Material.IRON_ORE)))).register(Main.instance);
        (new SlimefunItem(c, POTASSIUM_DUST, Tech.ELYZER, U.midr(new CustomItemStack(Material.ANDESITE))))
                .register(Main.instance);
        Variables.electrolyzer_r.put(
                new ItemStack[] {new CustomItemStack(Material.ANDESITE)}, new ItemStack[] {POTASSIUM_DUST});
        (new SlimefunItem(c, SODIUM_DUST, Tech.ELYZER, U.midr(new CustomItemStack(Material.DARK_PRISMARINE))))
                .register(Main.instance);
        Variables.electrolyzer_r.put(
                new ItemStack[] {(new CustomItemStack(Material.DARK_PRISMARINE))}, new ItemStack[] {SODIUM_DUST});
        (new SlimefunItem(c, BERYLLIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {BERYLLIUM_DUST}))
                .register(Main.instance);
        (new SlimefunItem(c, LITHIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {LITHIUM_DUST}))
                .register(Main.instance);
        (new SlimefunItem(c, POTASSIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {POTASSIUM_DUST}))
                .register(Main.instance);
        (new SlimefunItem(c, SODIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {SODIUM_DUST})).register(Main.instance);

        Variables.plug.getServer().getPluginManager().registerEvents(this, Variables.plug);
    }

    @EventHandler(ignoreCancelled = true)
    public static void carobbiite(BlockBreakEvent e) {
        if (!e.getBlock().getType().equals(Material.STONE)) return;
        if (e.getBlock().getData() != 3) return;
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        if (U.chance(100, Variables.cfg.getInt("items.carobbiite.chance"))) {
            e.getBlock().setType(Material.AIR);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), CAROBBIITE);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public static void beryllium_dust(BlockBreakEvent e) {
        if (!e.getBlock().getType().equals(Material.EMERALD_ORE)) return;
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        if (U.chance(100, Variables.cfg.getInt("items.beryllium.dust-chance"))) {
            e.getBlock().setType(Material.AIR);
            ItemStack bd = BERYLLIUM_DUST.clone();
            bd.setAmount(U.random(Variables.cfg.getInt("items.beryllium.dust-number"), 1));
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), bd);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public static void lithium_dust(BlockBreakEvent e) {
        if (!e.getBlock().getType().equals(Material.IRON_ORE)) return;
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        if (U.chance(100, Variables.cfg.getInt("items.lithium.dust-chance"))) {
            e.getBlock().setType(Material.AIR);
            ItemStack ld = LITHIUM_DUST.clone();
            ld.setAmount(U.random(Variables.cfg.getInt("items.lithium.dust-number"), 1));
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), ld);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreakingBlocks(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        if (e.getBlock().getType().equals(Material.COAL_ORE)) {
            if (BlockStorage.hasBlockInfo(e.getBlock())) return;
            if (U.random(100, 0) < Variables.cfg.getInt("items.graphite.chance")) {
                e.getBlock().setType(Material.AIR);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), GRAPHITE);
            }
        }
    }
}
