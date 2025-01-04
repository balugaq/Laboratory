package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class RubberBoots implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onFalling(EntityDamageEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && e.getEntity() instanceof Player p) {

            if (SlimefunUtils.isItemSimilar(
                    p.getInventory().getBoots(),
                    SlimefunItem.getById("RUBBER_BOOTS").getItem(),
                    true))
                e.setDamage(
                        e.getDamage() * Variables.cfg.getDouble("items.hazmat-suit.rubber-boots.fall-damage-decrease"));
        }
    }
}
