package me.Freeze_Dolphin.lab.bugrepair;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Me implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().matches("/minecraft:me (.*)") && !e.getPlayer().isOp()) e.setCancelled(true);
    }
}
