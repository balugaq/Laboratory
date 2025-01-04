package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class ElectricAxe implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            try {
                Player p = (Player) e.getDamager();
                ItemStack it = p.getInventory().getItemInMainHand();
                LivingEntity tg = (LivingEntity) e.getEntity();

                if (SlimefunUtils.isItemSimilar(it, Lab.ELECTRIC_AXE_1, false)) {
                    if (ItemEnergy.getStoredEnergy(it) >= 2.0F) {
                        ItemEnergy.chargeItem(it, -2.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 0.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);

                        e.setDamage((e.getDamage() + 1.0D) * 1.25D);
                        tg.getWorld().playEffect(tg.getLocation(), Effect.STEP_SOUND, Material.IRON_BLOCK);
                        tg.getWorld().playEffect(tg.getEyeLocation(), Effect.STEP_SOUND, Material.IRON_BLOCK);
                        tg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 10));
                        tg.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, -10));
                        return;
                    }
                    p.sendMessage(U.getCfgMessage("messages.electric-axe.not-enough-power"));
                    e.setCancelled(true);
                } else if (SlimefunUtils.isItemSimilar(it, Lab.ELECTRIC_AXE_2, false)) {
                    if (ItemEnergy.getStoredEnergy(it) >= 6.0F) {
                        ItemEnergy.chargeItem(it, -6.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0F, 1.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0F, 0.0F);
                        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);

                        e.setDamage((e.getDamage() + 2.0D) * 1.5D);
                        tg.getWorld().playEffect(tg.getLocation(), Effect.STEP_SOUND, Material.IRON_BLOCK);
                        tg.getWorld().playEffect(tg.getEyeLocation(), Effect.STEP_SOUND, Material.IRON_BLOCK);
                        tg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 10, false, false));
                        tg.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, -10, false, false));
                        tg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 10, false, false));
                        return;
                    }
                    p.sendMessage(U.getCfgMessage("messages.electric-axe.reinforced"));
                    e.setCancelled(true);
                }

            } catch (Exception ignored) {
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            LivingEntity le = (LivingEntity) e.getDamager();
            if (le.hasPotionEffect(PotionEffectType.SLOW)
                    && le.hasPotionEffect(PotionEffectType.SLOW_DIGGING)
                    && le.hasPotionEffect(PotionEffectType.JUMP)) {
                PotionEffect slow = le.getPotionEffect(PotionEffectType.SLOW);
                PotionEffect slow_dig = le.getPotionEffect(PotionEffectType.SLOW_DIGGING);
                PotionEffect jump = le.getPotionEffect(PotionEffectType.JUMP);

                if (slow.getAmplifier() == 10
                        && slow_dig.getAmplifier() == 10
                        && jump.getAmplifier() == -10
                        && !slow.isAmbient()
                        && !slow_dig.isAmbient()
                        && !jump.isAmbient()
                        && !slow.hasParticles()
                        && !slow_dig.hasParticles()
                        && !jump.hasParticles()) {
                    e.setCancelled(true);
                }
            }

        } catch (Exception ignored) {
        }
    }
}
