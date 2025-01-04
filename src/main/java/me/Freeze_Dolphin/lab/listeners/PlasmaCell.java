package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Tech;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlasmaCell implements Listener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), Tech.PLASMA_CELL, true))
            e.getPlayer().setHealth(0.0D);
    }
}
