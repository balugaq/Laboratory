package me.Freeze_Dolphin.lab.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.Freeze_Dolphin.lab.ItemEnergy;
import me.Freeze_Dolphin.lab.Lab;
import me.Freeze_Dolphin.lab.U;
import me.Freeze_Dolphin.lab.Variables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MimungBlaster implements Listener {
    public static final Set<UUID> a = new HashSet<>();
    @EventHandler
    public void onShoot(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack bow = p.getInventory().getItemInMainHand();
        SlimefunItem item = SlimefunItem.getByItem(bow);
        if (item == null) return;
        if (item.getId().equals(Lab.MIMUNG_BLASTER.getItemId())) {
            if (ItemEnergy.getStoredEnergy(bow) < 1024F) {
                event.setCancelled(true);
            }
            if (a.contains(p.getUniqueId())) {
                Fireball etyi = (Fireball) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREBALL);
                etyi.setCustomName("Mimung Blaster Bullet&*&%&###");
                etyi.setInvulnerable(true);
                if (Variables.cfg.getBoolean("items.mimung-blaster.fire")) {
                    etyi.setFireTicks(2147483647);
                }
                etyi.setGravity(false);
                etyi.setVelocity(p.getVelocity()
                        .multiply(Variables.cfg.getDouble("items.mimung-blaster.vector-multiplier")));
                U.newDelayedTask(
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (etyi != null && etyi.isValid()) etyi.remove();
                            }
                        },
                        Variables.cfg.getLong("items.mimung-blaster.clean-delay"));
                a.remove(p.getUniqueId());
            }
        }
    }
    @EventHandler
    public void onHit(EntityExplodeEvent e) {
        if ("Mimung Blaster Bullet&*&%&###".equals(e.getEntity().getCustomName())) {
            Collection<Entity> entities = e.getLocation().getNearbyEntities(5, 5, 5);
            for (Entity entity : entities) {
                entity.setFireTicks(20 * 100);
                if (entity instanceof LivingEntity le) {
                    le.damage(le.getHealth() / 10.0d);
                }
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Fireball fireball) {
            if ("Mimung Blaster Bullet&*&%&###".equals(fireball.getCustomName())) {
                Location location = fireball.getLocation();
                Collection<Entity> entities = location.getNearbyEntities(5, 5, 5);
                for (Entity entity : entities) {
                    entity.setFireTicks(20 * 100);
                    if (entity instanceof LivingEntity le) {
                        le.damage(le.getHealth() / 10.0d);
                    }
                }
            }
        }
    }
    private static int getCoolingPercent(ItemStack mb) {
        int line = 0;
        int statusLine = -1;
        ItemMeta im = mb.getItemMeta();
        List<String> l = im.getLore();
        for (String s : l) {
            line++;
            if (s.matches("(.*)核心状态(.*)")) {
                statusLine = line - 1;
                break;
            }
        }
        if (statusLine > 0) {
            String sl = l.get(statusLine);
            if (sl.matches(U.color("(.*)&a(.*)"))) {
                if (sl.split(U.color("&a"))[1].equals("正常")) {
                    return -1; // "核心状态: &a正常"
                } else return -5;
            } else if (sl.matches(U.color("(.*)&b冷却中(.*)"))) { // "核心状态: &b冷却中 (50 %)"
                return Integer.parseInt(sl.split(U.color("&b冷却中 &7("))[1].split(" %\\)")[0]);
            } else return -5;
        } else return -10;
    }

    private static void setCoolingPercent(ItemStack mb, int percent) {
        int line = 0;
        int statusLine = -1;
        ItemStack it = mb.clone();
        ItemMeta im = it.getItemMeta();
        List<String> l = im.getLore();
        for (String s : l) {
            line++;
            if (s.matches("(.*)核心状态(.*)")) {
                statusLine = line - 1;
                break;
            }
        }
        if (statusLine > 0) {
            String sl = l.get(statusLine);
            if (percent == -1) {
                l.set(statusLine, U.color("&f状态: &a正常"));
                im.setLore(l);
                mb.setItemMeta(im);
            }
            if (sl.matches(U.color("(.*)&b冷却中(.*)"))) {
                l.set(statusLine, U.color("&f状态: &b冷却中 &7(" + percent + " %)"));
                im.setLore(l);
                mb.setItemMeta(im);
            }
        }
    }

    private static boolean isMimungBlaster(ItemStack it) {
        return (SlimefunUtils.isItemSimilar(it, Lab.MIMUNG_BLASTER, false));
    }

    public static Object[] isBulletItem(Item i) {
        ItemStack it = i.getItemStack();
        if (!it.hasItemMeta()) return new Object[]{false, null};
        if (it.getItemMeta().hasDisplayName()) return new Object[]{false, null};
        if (!it.getItemMeta().hasLore()) return new Object[]{false, null};
        List<String> l = it.getItemMeta().getLore();
        if (l.size() == 3) {
            if (l.get(1).equals(U.color("&ftag: &o&m&l&rmimung-blaster-bullet"))) {
                return new Object[]{true, l.get(2)};
            }
        }
        return new Object[]{false, null};
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) return;
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!item.equals(U.getItemInMainHand(p))) return;
            if (isMimungBlaster(item)) {
                if (ItemEnergy.getStoredEnergy(item) >= 1024F) {
                    int line = 0;
                    int statusLine = -1;
                    ItemStack itclone = item.clone();
                    ItemMeta im = itclone.getItemMeta();
                    List<String> l = im.getLore();
                    for (String s : l) {
                        line++;
                        if (s.matches("(.*)核心状态(.*)")) {
                            statusLine = line - 1;
                            break;
                        }
                    }
                    if (statusLine > 0) {
                        int per = getCoolingPercent(itclone);
                        if (per == -1) { // status is 'normal'
                            ItemEnergy.chargeItem(item, -1024F);
                            // name: U.color("&ftag: &o&m&l&rmimung-blaster-bullet"))
                            ItemStack mmbb = new CustomItemStack(Material.PURPLE_STAINED_GLASS_PANE);
                            ItemMeta immb = mmbb.getItemMeta();
                            List<String> nl = new ArrayList<>();
                            nl.add("");
                            nl.add(U.color("&ftag: &o&m&l&rmimung-blaster-bullet"));
                            nl.add(p.getUniqueId().toString());
                            immb.setLore(nl);
                            mmbb.setItemMeta(immb);
                            a.add(p.getUniqueId());
                        } else if (per >= 0) { // status is 'cooling'
                            int level = per / 25;
                            while (level <= 4) {
                                level++;
                                if (getCoolingPercent(itclone) >= 100) break;
                                U.newDelayedTask(
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                if (U.getItemInMainHand(p).equals(itclone)) {
                                                    setCoolingPercent(
                                                            U.getItemInMainHand(p), getCoolingPercent(itclone) + 25);
                                                }
                                            }
                                        },
                                        20L);
                            }
                            if (getCoolingPercent(itclone) >= 100
                                    && (U.getItemInMainHand(p).equals(itclone))) {
                                setCoolingPercent(U.getItemInMainHand(p), -1);
                            }
                        }
                            // not mimung blaster
                    }
                } else {
                    p.sendMessage(U.getCfgMessage("messages.mimung-blaster.not-enough-power"));
                    a.remove(p.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        Object[] ib = isBulletItem(e.getItem());
        if ((boolean) ib[0]) {
            if (e.getEntity() instanceof Player p) {
                if (UUID.fromString((String) ib[1]).equals(p.getUniqueId())) {
                    e.setCancelled(true);
                    return;
                }
            }
            if (e.getItem().isValid())
                e.getItem().getWorld().createExplosion(e.getItem().getLocation(), 0);
            // e.getEntity().damage(Variables.cfg.getDouble("items.mimung-blaster.damage"),
            // Bukkit.getPlayer(UUID.fromString((String)ib[1])));
            if (e.getItem().isValid())
                e.getEntity()
                        .setLastDamageCause(new EntityDamageEvent(
                                Bukkit.getPlayer(UUID.fromString((String) ib[1])),
                                DamageCause.BLOCK_EXPLOSION,
                                Variables.cfg.getDouble("items.mimung-blaster.damage")));
            if (e.getItem().isValid()) e.getItem().remove();
            e.setCancelled(true);
        }
    }
}
