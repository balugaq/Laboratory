package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Vanisher implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        ItemStack cp = p.getInventory().getBoots();

        if (SlimefunUtils.isItemSimilar(cp, Lab.VANISHER, false)
                || SlimefunUtils.isItemSimilar(cp, Lab.WHITE_VANISHER, false)) {
            if (U.compareBlockLocation(e.getFrom(), e.getTo())) return;
            float cost = (float) Variables.cfg.getDouble("items.vanisher.energy-consumption");
            if (ItemEnergy.getStoredEnergy(cp) >= cost) {

                if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)
                        && p.getPotionEffect(PotionEffectType.INVISIBILITY).getAmplifier() != 10) {
                    p.sendMessage(U.getCfgMessage("messages.vanisher.enabled"));
                }

                if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    p.sendMessage(U.getCfgMessage("messages.vanisher.enabled"));
                }

                ItemEnergy.chargeItem(cp, -1.0F * cost);
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999990, 10, false, false), true);
            } else if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)
                    && p.getPotionEffect(PotionEffectType.INVISIBILITY).getAmplifier() == 10
                    && !p.getPotionEffect(PotionEffectType.INVISIBILITY).hasParticles()
                    && !p.getPotionEffect(PotionEffectType.INVISIBILITY).isAmbient()) {
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
                p.sendMessage(U.getCfgMessage("messages.vanisher.not-enough-power"));
            }

        } else if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)
                && p.getPotionEffect(PotionEffectType.INVISIBILITY).getAmplifier() == 10
                && !p.getPotionEffect(PotionEffectType.INVISIBILITY).hasParticles()
                && !p.getPotionEffect(PotionEffectType.INVISIBILITY).isAmbient()) {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.sendMessage(U.getCfgMessage("messages.vanisher.disabled"));
        }
    }
}
