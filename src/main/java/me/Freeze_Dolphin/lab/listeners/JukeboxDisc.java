package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Lab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class JukeboxDisc implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem() && e.hasBlock()) {
            if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.DRAGON_BREATH_GENERATOR, false)) {
                e.setCancelled(true);
            }
            if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.VECTOR_MANIPULATOR, false)) e.setCancelled(true);
        }
    }
}
