package me.Freeze_Dolphin.lab.listeners;

import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.Tech;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MagneticIron implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBreakingBlocks(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;

        if (e.getBlock().getType().equals(Material.IRON_ORE)) {
            if (BlockStorage.hasBlockInfo(e.getBlock())) return;
            if (U.random(1000, 0) < Variables.cfg.getInt("items.magnetic-iron.chance")) {
                e.getBlock().setType(Material.AIR);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Tech.MAGNETIC_IRON_INGOT));
            }
        }
    }
}
