package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Lab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public final class EmeraldCapacity implements Listener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.EMERALD_CAPACITY_1, false)) {
            e.setCancelled(true);
        }
        if (SlimefunUtils.isItemSimilar(e.getItem(), Lab.EMERALD_CAPACITY_2, false)) {
            e.setCancelled(true);
        }
    }
}
