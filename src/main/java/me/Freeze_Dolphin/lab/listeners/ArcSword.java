package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.Laboratory;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ArcSword implements Listener {
    public static boolean isEnabled(ItemStack it) {
        boolean rt = false;

        if (!it.hasItemMeta()) return false;
        ItemMeta im = it.getItemMeta();
        if (!im.hasLore()) return false;
        List<String> l = it.getItemMeta().getLore();

        int line = 0;
        int modeLine = -1;
        for (String s : l) {
            line++;
            if (s.matches("(.*)启用")) {
                modeLine = line - 1;

                break;
            }
        }
        if (modeLine <= 0) return false;

        rt = true;

        return true;
    }

    public static int getModeLoreLine(ItemStack it) {
        if (!it.hasItemMeta()) return -1;
        ItemMeta im = it.getItemMeta();
        if (!im.hasLore()) return -1;
        List<String> l = it.getItemMeta().getLore();

        int line = 0;
        int modeLine = -1;
        for (String s : l) {
            line++;
            if (s.matches("(.*)电弧发生器状态(.*)")) {
                modeLine = line - 1;

                break;
            }
        }
        return modeLine;
    }

    public static void switchMode(ItemStack it, boolean sendMessage, Player p) {
        try {
            List<String> l = it.getItemMeta().getLore();
            if (isEnabled(it)) {
                l.set(getModeLoreLine(it), U.color("&c&o&8⇨ &f电弧发生器状态: &c禁用"));
                ItemMeta im = it.getItemMeta();
                im.setLore(l);
                it.setItemMeta(im);
                if (sendMessage) {
                    p.sendMessage(U.getCfgMessage("messages.electric-sword.disable"));
                }
            } else {
                l.set(getModeLoreLine(it), U.color("&c&o&8⇨ &f电弧发生器状态: &a启用"));
                ItemMeta im = it.getItemMeta();
                im.setLore(l);
                it.setItemMeta(im);
                if (sendMessage) {
                    p.sendMessage(U.getCfgMessage("messages.electric-sword.enable"));
                }
            }
        } catch (Exception ex) {
            Laboratory.debugException(ex);
        }
    }

    private static void damage(LivingEntity le, double forceDamage) {
        if (le.getHealth() < forceDamage) {
            le.setHealth(0.0D);
        } else {
            le.setHealth(le.getHealth() - forceDamage);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onHitting(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof LivingEntity dr) {
            ItemStack wp = dr.getEquipment().getItemInMainHand();

            if (!(e.getEntity() instanceof LivingEntity le)) return;

            try {
                if (SlimefunUtils.isItemSimilar(wp, Lab.ARC_SWORD, false) && isEnabled(wp)) {
                    if (ItemEnergy.getStoredEnergy(wp) >= 4.0F) {
                        ItemEnergy.chargeItem(wp, -4.0F);
                        boolean notSuit = false;
                        if ((le.getEquipment().getArmorContents()).length < 4) {
                            notSuit = true;

                        } else if (!le.getEquipment().getHelmet().getType().equals(Material.LEATHER_HELMET)) {
                            notSuit = true;

                        } else if (!le.getEquipment().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)) {
                            notSuit = true;

                        } else if (!le.getEquipment().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)) {
                            notSuit = true;

                        } else if (!le.getEquipment().getBoots().getType().equals(Material.LEATHER_BOOTS)) {
                            notSuit = true;

                        } else if (!le.getEquipment().getChestplate().getType().equals(Material.ELYTRA)) {
                            notSuit = true;
                        }

                        if (notSuit) {
                            damage(le, e.getDamage() * Variables.cfg.getDouble("items.electric-sword.damage"));
                        }
                    } else {

                        if (e.getDamager() instanceof Player p) {
                            p.sendMessage(U.getCfgMessage("messages.electric-sword.not-enough-power"));
                        }
                        e.setCancelled(true);
                    }
                }
            } catch (Exception ex) {
                Laboratory.debugException(ex);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChangingMode(PlayerInteractEvent e) {
        if (e.hasItem() && SlimefunUtils.isItemSimilar(e.getItem(), Lab.ARC_SWORD, false)) {

            ItemStack it = e.getItem();
            if (e.getPlayer().isSneaking()) {
                Laboratory.debug("change mode");
                switchMode(it, true, e.getPlayer());
            }
        }
    }
}
