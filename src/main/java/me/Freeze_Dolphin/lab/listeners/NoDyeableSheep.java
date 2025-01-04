package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class NoDyeableSheep implements Listener {
    @EventHandler
    public void onDying(PlayerInteractEvent e) {
        for (ItemStack it : Variables.Dye) {

            if (e.hasItem() && SlimefunUtils.isItemSimilar(e.getItem(), it, true)) e.setCancelled(true);
        }
    }
}
