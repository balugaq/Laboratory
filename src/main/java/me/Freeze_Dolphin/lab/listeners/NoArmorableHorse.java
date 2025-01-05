package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NoArmorableHorse implements Listener {
    @EventHandler
    public void onArmor(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        Inventory invo = e.getWhoClicked().getOpenInventory().getTopInventory();
        if ((inv instanceof HorseInventory)
                // || (inv instanceof AbstractHorseInventory)
                || (invo instanceof HorseInventory)
            // || (invo instanceof AbstractHorseInventory)
        ) {
            for (ItemStack it : Variables.UnequippedBardings) {
                if (SlimefunUtils.isItemSimilar(e.getCurrentItem(), it, true)) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}
