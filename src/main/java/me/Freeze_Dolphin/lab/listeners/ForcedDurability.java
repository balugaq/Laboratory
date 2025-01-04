package me.Freeze_Dolphin.lab.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ForcedDurability implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        if (e instanceof Player) {
            Player p = (Player) e.getEntity();
            // todo: fix undefined variable
            /*
            if (SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), Lab.ADVANCED_SOLAR_HELMET, false)) {
                e.setDamage(e.getDamage() * Variables.cfg.getDouble("items.solar-helmet.armor-protection.advanced"));
                if (U.chance(100, Variables.cfg.getInt("items.solar-helmet.armor-durability.advanced"))) {
                    if (p.getInventory().getHelmet().getDurability() < Material.LEATHER_HELMET.getMaxDurability() - 1) {
                        p.getInventory().getHelmet().setDurability((short) (p.getInventory().getHelmet().getDurability() + 1));
                    }
                }
            } else if (SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), Lab.CARBONADO_SOLAR_HELMET, false)) {
                e.setDamage(e.getDamage() * Variables.cfg.getDouble("items.solar-helmet.armor-protection.carbonado"));
                if (U.chance(100, Variables.cfg.getInt("items.solar-helmet.armor-durability.carbonado"))) {
                    if (p.getInventory().getHelmet().getDurability() < Material.LEATHER_HELMET.getMaxDurability() - 1) {
                        p.getInventory().getHelmet().setDurability((short) (p.getInventory().getHelmet().getDurability() + 1));
                    }
                }
            } else if (SlimefunUtils.isItemSimilar(p.getInventory().getHelmet(), Lab.ENERGIZED_SOLAR_HELMET, false)) {
                e.setDamage(e.getDamage() * Variables.cfg.getDouble("items.solar-helmet.armor-protection.energized"));
                if (U.chance(100, Variables.cfg.getInt("items.solar-helmet.armor-durability.energized"))) {
                    if (p.getInventory().getHelmet().getDurability() < Material.LEATHER_HELMET.getMaxDurability() - 1) {
                        p.getInventory().getHelmet().setDurability((short) (p.getInventory().getHelmet().getDurability() + 1));
                    }
                }
            }

             */
        }
    }
}
