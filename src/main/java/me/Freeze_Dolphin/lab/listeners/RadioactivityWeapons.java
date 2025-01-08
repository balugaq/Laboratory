package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ArmorWeapon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RadioactivityWeapons implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void ondamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player p) {

            if (e.getEntity() instanceof LivingEntity d) {

                ItemStack hand = p.getInventory().getItemInMainHand();

                if (SlimefunUtils.isItemSimilar(hand, ArmorWeapon.URANIUM_SWORD, true)
                        || SlimefunUtils.isItemSimilar(hand, ArmorWeapon.IRRADIANT_URANIUM_SWORD, true)) {

                    if (e.getEntity() instanceof Player dp) {
                        if (SlimefunUtils.isItemSimilar(
                                SlimefunItems.SCUBA_HELMET,
                                dp.getInventory().getHelmet(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                SlimefunItems.HAZMAT_CHESTPLATE,
                                dp.getInventory().getChestplate(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                SlimefunItems.HAZMAT_LEGGINGS,
                                dp.getInventory().getLeggings(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                SlimefunItems.HAZMAT_BOOTS,
                                dp.getInventory().getBoots(),
                                true)) {
                            return;
                        }
                    }

                    d.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 3));
                    d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1));
                    d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 1));

                    if (SlimefunUtils.isItemSimilar(hand, ArmorWeapon.IRRADIANT_URANIUM_SWORD, true)) {
                        d.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 3));
                        d.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 3));
                        d.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 3));
                        d.setFireTicks(400);
                    }

                } else if (SlimefunUtils.isItemSimilar(hand, ArmorWeapon.RADIUM_SWORD, true)
                        || SlimefunUtils.isItemSimilar(hand, ArmorWeapon.IRRADIANT_RADIUM_SWORD, true)) {

                    if (e.getEntity() instanceof Player dp) {
                        if (SlimefunUtils.isItemSimilar(
                                ArmorWeapon.REINFORCED_SCUBA_HELMET,
                                dp.getInventory().getHelmet(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                ArmorWeapon.REINFORCED_HAZMATSUIT_CHESTPLATE,
                                dp.getInventory().getChestplate(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                ArmorWeapon.REINFORCED_HAZMATSUIT_LEGGINGS,
                                dp.getInventory().getLeggings(),
                                true)
                                && SlimefunUtils.isItemSimilar(
                                ArmorWeapon.REINFORCED_HAZMATSUIT_BOOTS,
                                dp.getInventory().getBoots(),
                                true)) {
                            return;
                        }
                    }

                    d.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 10), true);
                    d.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 400, 15), true);
                    d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 3), true);
                    d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 3), true);

                    if (SlimefunUtils.isItemSimilar(hand, ArmorWeapon.IRRADIANT_RADIUM_SWORD, true)) {

                        d.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 3), true);
                        d.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 3), true);
                        d.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 3), true);
                        d.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, -10), true);
                        d.setFireTicks(400);
                    }
                }
            }
        }
    }
}
