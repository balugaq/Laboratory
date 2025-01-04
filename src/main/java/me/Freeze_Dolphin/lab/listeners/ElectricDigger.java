package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class ElectricDigger implements Listener {
    private static final int amplifier = Variables.cfg.getInt("items.electric-digger.amplifier");

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (e.hasItem() && SlimefunUtils.isItemSimilar(e.getItem(), Lab.ELECTRIC_DIGGER, false)) {
                if (ItemEnergy.getStoredEnergy(e.getItem()) >= 0.1D) {
                    e.getPlayer()
                            .addPotionEffect(
                                    new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, amplifier, false, false));

                } else {

                    e.getPlayer().sendMessage(U.getCfgMessage("messages.electric-digger.not-enough-power"));
                    e.setCancelled(true);
                }

            } else if (e.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)
                    && !e.getPlayer()
                            .getPotionEffect(PotionEffectType.FAST_DIGGING)
                            .isAmbient()
                    && !e.getPlayer()
                            .getPotionEffect(PotionEffectType.FAST_DIGGING)
                            .hasParticles()
                    && e.getPlayer()
                                    .getPotionEffect(PotionEffectType.FAST_DIGGING)
                                    .getAmplifier()
                            == amplifier) {
                e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
            }
        }
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        if (!e.isCancelled()) {
            ItemStack it = e.getPlayer().getInventory().getItemInMainHand();
            if (SlimefunUtils.isItemSimilar(it, Lab.ELECTRIC_DIGGER, false) && ItemEnergy.getStoredEnergy(it) >= 0.1D) {
                ItemEnergy.chargeItem(it, -0.1F);

                return;
            }

            if (e.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)
                    && !e.getPlayer()
                            .getPotionEffect(PotionEffectType.FAST_DIGGING)
                            .isAmbient()
                    && !e.getPlayer()
                            .getPotionEffect(PotionEffectType.FAST_DIGGING)
                            .hasParticles()
                    && e.getPlayer()
                                    .getPotionEffect(PotionEffectType.FAST_DIGGING)
                                    .getAmplifier()
                            == amplifier) {
                if (SlimefunUtils.isItemSimilar(
                        e.getPlayer().getInventory().getItemInMainHand(), Lab.ELECTRIC_DIGGER, false)) {
                    return;
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        ItemStack it = e.getPlayer().getInventory().getItemInMainHand();
        if (!SlimefunUtils.isItemSimilar(it, Lab.ELECTRIC_DIGGER, false)
                && e.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)
                && !e.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING).isAmbient()
                && !e.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING).hasParticles()
                && e.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() == amplifier)
            e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
    }
}
