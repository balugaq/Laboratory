package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class EggGuarder implements Listener {
    @EventHandler
    public void onRightClickEgg(PlayerInteractEvent e) {
        if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))
                && e.getClickedBlock().getType().equals(Material.DRAGON_EGG)) {

            Location loc = e.getClickedBlock().getLocation();
            Location locup = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() + 1), loc.getBlockZ());

            if (BlockStorage.check(
                    locup, SlimefunItem.getByItem(Lab.EGG_GUARDER).getId())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(U.color(Variables.cfg.getString("messages.egg-guarder.protecting")));
            }
        }
    }
}
