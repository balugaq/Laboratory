package me.Freeze_Dolphin.lab.bugrepair;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ElevatorBook implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem()) {
            if (!e.getItem().getType().equals(Material.WRITTEN_BOOK)) return;
            if (!e.getItem().hasItemMeta()) return;
            if (!e.getItem().getItemMeta().hasDisplayName()) return;
            if (e.getItem().getItemMeta().getDisplayName().equals("Elevator")) {
                e.getItem().setAmount(0);
                e.getItem().setType(Material.AIR);
                e.setCancelled(true);
            }
        }
    }
}
