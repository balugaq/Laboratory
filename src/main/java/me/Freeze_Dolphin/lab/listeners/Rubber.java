package me.Freeze_Dolphin.lab.listeners;

import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.inventory.ItemStack;

public class Rubber implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBreakingBlocks(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        if (true) {
            if ((e.getBlock().getType() == Material.OAK_LOG || e.getBlock().getType() == Material.DARK_OAK_LOG)
                    && !BlockStorage.hasBlockInfo(e.getBlock())) {
                if (U.random(100, 0) < Variables.cfg.getInt("items.rubber.normal-chance")) {
                    e.getBlock().setType(Material.AIR);
                    ItemStack raw4 = Tech.RAW_RUBBER.clone();
                    raw4.setAmount(U.random(Variables.cfg.getInt("items.rubber.amount"), 1));
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), raw4);
                    e.getBlock()
                            .getWorld()
                            .dropItemNaturally(
                                    e.getBlock().getLocation(),
                                    new ItemStack(
                                            e.getBlock().getType(),
                                            U.random(
                                                    Variables.cfg.getInt("items.rubber.plank-amount-highest"),
                                                    Variables.cfg.getInt("items.rubber.plank-amount-lowest"))));
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onExplodingBlocks(BlockExplodeEvent e) {
        if (true)
            if ((e.getBlock().getType() == Material.OAK_LOG || e.getBlock().getType() == Material.DARK_OAK_LOG)
                    && !BlockStorage.hasBlockInfo(e.getBlock())) {
                if (U.random(300, 0) < Variables.cfg.getInt("items.rubber.normal-chance")) {
                    e.getBlock().setType(Material.AIR);
                    ItemStack raw4 = Tech.RAW_RUBBER.clone();
                    raw4.setAmount(U.random(Variables.cfg.getInt("items.rubber.amount"), 1));
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), raw4);
                    e.getBlock()
                            .getWorld()
                            .dropItemNaturally(
                                    e.getBlock().getLocation(),
                                    new ItemStack(
                                            e.getBlock().getType(),
                                            U.random(
                                                    Variables.cfg.getInt("items.rubber.plank-amount-highest"),
                                                    Variables.cfg.getInt("items.rubber.plank-amount-lowest"))));
                }
            }
    }
}
