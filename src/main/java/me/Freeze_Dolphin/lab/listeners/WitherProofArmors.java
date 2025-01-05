package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ArmorWeapon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class WitherProofArmors implements Listener {
    @EventHandler
    public void onWither(EntityDamageEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.WITHER) && e.getEntity() instanceof Player p) {
            if (SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_GLASS_HELMET,
                    p.getInventory().getHelmet(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_GLASS_CHESTPLATE,
                    p.getInventory().getChestplate(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_GLASS_LEGGINGS,
                    p.getInventory().getLeggings(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_GLASS_BOOTS,
                    p.getInventory().getBoots(),
                    true)) {

                e.setCancelled(true);
            } else if (SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_OBSIDIAN_HELMET,
                    p.getInventory().getHelmet(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_OBSIDIAN_CHESTPLATE,
                    p.getInventory().getChestplate(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_OBSIDIAN_LEGGINGS,
                    p.getInventory().getLeggings(),
                    true)
                    && SlimefunUtils.isItemSimilar(
                    ArmorWeapon.WITHER_PROOF_OBSIDIAN_BOOTS,
                    p.getInventory().getBoots(),
                    true)) {

                e.setCancelled(true);
            }
        }
    }
}
